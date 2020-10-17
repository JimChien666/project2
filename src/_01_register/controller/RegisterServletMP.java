package _01_register.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import _01_register.dao.InsertFileDao;
import _01_register.model.MemberBean;
import _01_register.model.MemberFileBean;
import _01_register.service.MemberService;
import _01_register.service.impl.MemberServiceImpl;
import nn.service.GlobalService;
import nn.service.SystemUtils2018;
import nn.vo.FileBean;

/*
 * 本程式在後端執行，它會
 * (1) 讀取使用者由前端網頁輸入資料，
 * (2) 進行必要的資料轉換，
 * (3) 檢查使用者輸入資料，
 * (4) 呼叫Business Service元件，進行Business Logic運算，
 * (5) 依照Business Logic
 *     運算結果來送回適當的畫面給前端的使用者。
 * 
 */
//
//啟動檔案上傳的功能：
//1. <form>標籤的 method屬性必須是"POST", 而且
//    enctype屬性必須是"multipart/form-data"
//    注意：enctype屬性的預設值為"application/x-www-form-urlencoded"
//2. 定義可以挑選上傳檔案的表單欄位：
//   <input type='file' name='user-defined_name' />
//
//所謂 HTTP multipart request是指由Http客戶端(如瀏覽器)所建構的ㄧ種請求，
//用來上傳一般的表單資料(form data)與檔案。
//參考網頁：http://stackoverflow.com/questions/913626/what-should-a-multipart-http-request-with-multiple-files-look-like
//
//Servlet規格書一直到Servlet 3.0才提出標準API將檔案上傳的功能標準化。
//
//在Servlet 3.0中，若要能夠處理瀏覽器送來的HTTP multipart request, 
//我們撰寫的Servlet程式必須以註釋
//   『javax.servlet.annotation.MultipartConfig』來加以說明。
//
//MultipartConfig的屬性說明:
//location: 上傳之表單資料與檔案暫時存放在Server端之路徑，此路徑必須存在，否則Web Container將丟出例外。
//
//fileSizeThreshold: 上傳檔案的大小臨界值，超過此臨界值，上傳檔案會用存放在硬碟，
//                   否則存放在主記憶體。
//
//maxFileSize: 上傳單一檔案之長度限制，如果超過此數值，Web Container會丟出例外
//
//maxRequestSize: 上傳所有檔案之總長度限制，如果超過此數值，Web Container會丟出例外
@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 1024 * 1024
		* 500, maxRequestSize = 1024 * 1024 * 500 * 5)

@WebServlet("/_01_register/register.do")
public class RegisterServletMP extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 
	// 設定密碼欄位必須由大寫字母、小寫字母、數字與 !@#$%!^'" 等四組資料組合而成，且長度不能小於八個字元
	// 
	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%!^'\"]).{8,})";
	private Pattern pattern = null;
	private Matcher matcher = null;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8"); // 文字資料轉內碼
		// 準備存放錯誤訊息的Map物件
		Map<String, String> errorMsg = new HashMap<String, String>();
		// 準備存放註冊成功之訊息的Map物件
		Map<String, String> msgOK = new HashMap<String, String>();
		// 註冊成功後將用response.sendRedirect()導向新的畫面，所以需要
		// session物件來存放共用資料。
		HttpSession session = request.getSession();
		request.setAttribute("MsgMap", errorMsg); // 顯示錯誤訊息
		session.setAttribute("MsgOK", msgOK); // 顯示正常訊息

		String memberID = "";
		String password = "";
		String password2 = "";
		String type = "";
		String income = "";
		String name = "";
		String email = "";
		String addr = "";
		String tel = "";
		String fileName = "";
		String sex = "";
		long sizeInBytes = 0;
		InputStream is = null;
		// 取出HTTP multipart request內所有的parts
		Collection<Part> parts = request.getParts();
		GlobalService.exploreParts(parts, request);
		// 由parts != null來判斷此上傳資料是否為HTTP multipart request
		if (parts != null) { // 如果這是一個上傳資料的表單
			for (Part p : parts) {
				String fldName = p.getName();
				String value = request.getParameter(fldName);

				// 1. 讀取使用者輸入資料
				if (p.getContentType() == null) {
					if (fldName.equals("mid")) {
						memberID = value;
					} else if (fldName.equals("password")) {
						password = value;
					} else if (fldName.equalsIgnoreCase("password2")) {
						password2 = value;
					} else if (fldName.equalsIgnoreCase("name")) {
						name = value;
					} else if (fldName.equalsIgnoreCase("eMail")) {
						email = value;
					} else if (fldName.equalsIgnoreCase("address")) {
						addr = value;
					} else if (fldName.equalsIgnoreCase("tel")) {
						tel = value;
					} else if (fldName.equalsIgnoreCase("income")) {
						income = value;
					} else if (fldName.equalsIgnoreCase("type")) {
						type = value;
					} else if (fldName.equalsIgnoreCase("sex")) {
						sex = value;
					}
				} else {
					// 取出圖片檔的檔名
					fileName = GlobalService.getFileName(p);
					// 調整圖片檔檔名的長度，需要檔名中的附檔名，所以調整主檔名以免檔名太長無法寫入表格
					fileName = GlobalService.adjustFileName(fileName, GlobalService.IMAGE_FILENAME_LENGTH);
					if (fileName != null && fileName.trim().length() > 0) {
						sizeInBytes = p.getSize();
						is = p.getInputStream();
					} else {
						errorMsg.put("errPicture", "必須挑選圖片檔");
					}
				}
			}
			// 2. 進行必要的資料轉換
			// (無)
			// 3. 檢查使用者輸入資料
			if (memberID == null || memberID.trim().length() == 0) {
				errorMsg.put("errorIDEmpty", "帳號欄必須輸入");
			}
			if (password == null || password.trim().length() == 0) {
				errorMsg.put("errorPasswordEmpty", "密碼欄必須輸入");
			}
			if (password2 == null || password2.trim().length() == 0) {
				errorMsg.put("errorPassword2Empty", "密碼確認欄必須輸入");
			}
			if (password.trim().length() > 0 && password2.trim().length() > 0) {
				if (!password.trim().equals(password2.trim())) {
					errorMsg.put("errorPassword2Empty", "密碼欄必須與確認欄一致");
					errorMsg.put("errorPasswordEmpty", "*");
				}
			}

			if (name == null || name.trim().length() == 0) {
				errorMsg.put("errorName", "姓名欄必須輸入");
			}
			if (addr == null || addr.trim().length() == 0) {
				errorMsg.put("errorAddr", "地址欄必須輸入");
			}
			if (email == null || email.trim().length() == 0) {
				errorMsg.put("errorEmail", "電子郵件欄必須輸入");
			}
			if (tel == null || tel.trim().length() == 0) {
				errorMsg.put("errorTel", "電話號碼欄必須輸入");
			}
			if (income == null || tel.trim().length() == 0) {
				errorMsg.put("errorIncome", "收入欄必須輸入");
			}

		} else {
			errorMsg.put("errTitle", "此表單不是上傳檔案的表單");
		}
		// 如果有錯誤
		if (errorMsg.isEmpty()) {
			pattern = Pattern.compile(PASSWORD_PATTERN);
			matcher = pattern.matcher(password);
			if (!matcher.matches()) {
				errorMsg.put("passwordError", "密碼至少含有一個大寫字母、小寫字母、數字與!@#$%!^'\"等四組資料組合而成，且長度不能小於八個字元");
			}
		}
		// 如果有錯誤
		if (!errorMsg.isEmpty()) {
			// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
			RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
			rd.forward(request, response);
			return;
		}
		try {
			// 4. 產生MemberDao物件，以便進行Business Logic運算
			// MemberDaoImpl_Jdbc類別的功能：
			// 1.檢查帳號是否已經存在，已存在的帳號不能使用，回傳相關訊息通知使用者修改
			// 2.若無問題，儲存會員的資料
			MemberService service = new MemberServiceImpl();
			if (service.idExists(memberID)) {
				errorMsg.put("errorIDDup", "此帳號已存在，請換新帳號");
			} else {
				// 為了配合Hibernate的版本。
				// 要在此加密，不要在 dao.saveMember(mem)進行加密
				password = GlobalService.getMD5Endocing(
						GlobalService.encryptString(password));
				Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
				Blob blob = SystemUtils2018.fileToBlob(is, sizeInBytes);
				// 將所有會員資料封裝到MemberBean(類別的)物件

				MemberBean mem = new MemberBean(type, name, sex, tel, Integer.parseInt(income), memberID, 
						password, email, addr);
				// 呼叫MemberDao的saveMember方法
				int id = service.saveMember(mem);
				mem.setId(id);
				System.out.println(blob);
				InsertFileDao fileDao = new InsertFileDao();
				fileDao.insertFile(id, blob);
				if (id != 0) {
					msgOK.put("InsertOK", "<Font color='red'>新增成功，請開始使用本系統</Font>");
					response.sendRedirect("/Project2/nn/controler/ShowIndexServlet");
					return;
				} else {
					errorMsg.put("errorIDDup", "新增此筆資料有誤(RegisterServlet)");
				}
			}
			// 5.依照 Business Logic 運算結果來挑選適當的畫面
			if (!errorMsg.isEmpty()) {
				// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
				RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
				rd.forward(request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorMsg.put("errorIDDup", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
			rd.forward(request, response);
		}
	}
}