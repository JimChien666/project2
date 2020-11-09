package com.iii.eeit124.member.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.Blob;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.iii.eeit124.entity.MemberFiles;
import com.iii.eeit124.entity.Members;
import com.iii.eeit124.member.service.LoginService;
import com.iii.eeit124.util.GlobalService;

@Controller
@RequestMapping("/member")
public class LoginController {
	@Autowired
	ServletContext ctx;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private LoginService loginService;
	@Autowired
	private HttpSession session;


	@GetMapping("/login")
	public String getLoginPage(Model m) {
		return "members/login";
	}
	@GetMapping("/logout")
	public String getLogoutPage(Model m) {
		return "members/logout";
	}

	@PostMapping("/processLogin.controller")
	public String processLogin(@RequestParam(name = "account") String account,
			@RequestParam(name = "password") String password,
			@RequestParam(name = "rememberMe", required = false) String rememberMe, Model m) {
		Map<String, String> errors = new HashMap<String, String>();
		m.addAttribute("errors", errors);
		account = account.trim();
		password = password.trim();
		System.out.println(rememberMe);

		if (account == null || account.trim().length() == 0) {
			errors.put("AccountEmptyError", "帳號欄必須輸入");
		}
		if (password == null || password.trim().length() == 0) {
			errors.put("PasswordEmptyError", "密碼欄必須輸入");
		}

		// 如果 errors 不是空的，表示有錯誤，交給login.jsp
		if (!errors.isEmpty()) {
			return "members/login";
		}
		// **********Remember Me****************************
		Cookie cookieUser = null;
		Cookie cookiePassword = null;
		Cookie cookieRememberMe = null;
		// rememberMe存放瀏覽器送來之勾選的選項，如果使用者對RememberMe打勾，rememberMe就不會是null
		if (rememberMe != null) {
			cookieUser = new Cookie("user", account);
			cookieUser.setMaxAge(7 * 24 * 60 * 60); // Cookie的存活期: 七天
			cookieUser.setPath(request.getContextPath());

			String encodePassword = GlobalService.encryptString(password);
			cookiePassword = new Cookie("password", encodePassword);
			cookiePassword.setMaxAge(7 * 24 * 60 * 60);
			cookiePassword.setPath(request.getContextPath());

			cookieRememberMe = new Cookie("rm", "true");
			cookieRememberMe.setMaxAge(7 * 24 * 60 * 60);
			cookieRememberMe.setPath(request.getContextPath());
		} else { // 使用者沒有對 RememberMe 打勾
			cookieUser = new Cookie("user", account);
			cookieUser.setMaxAge(0); // MaxAge==0 表示要請瀏覽器刪除此Cookie
			cookieUser.setPath(request.getContextPath());

			String encodePassword = GlobalService.encryptString(password);
			cookiePassword = new Cookie("password", encodePassword);
			cookiePassword.setMaxAge(0);
			cookiePassword.setPath(request.getContextPath());

			cookieRememberMe = new Cookie("rm", "true");
			cookieRememberMe.setMaxAge(0);
			cookieRememberMe.setPath(request.getContextPath());
		}
		response.addCookie(cookieUser);
		response.addCookie(cookiePassword);
		response.addCookie(cookieRememberMe);
		// ********************************************
		password = GlobalService.getMD5Endocing(GlobalService.encryptString(password));
		System.out.println(password);
		Members member = null;
		try {
			// 呼叫 loginService物件的 checkIDPassword()，傳入userid與password兩個參數
			member = loginService.checkAccountPassword(account, password);
			if (member != null) {
				// OK, 登入成功, 將mb物件放入Session範圍內，識別字串為"LoginOK"
				session.setAttribute("LoginOK", member);
			} else {
				// NG, 登入失敗, userid與密碼的組合錯誤，放相關的錯誤訊息到 errorMsgMap 之內
				errors.put("LoginError", "該帳號不存在或密碼錯誤");
			}
		} catch (RuntimeException ex) {
			errors.put("LoginError", ex.getMessage());
		}
		// 5.依照 Business Logic 運算結果來挑選適當的畫面
		// 如果 errorMsgMap 是空的，表示沒有任何錯誤，交棒給下一棒
		if (errors.isEmpty()) {
			//回到登入前畫面
			String requestURI = (String)session.getAttribute("requestURI");
			if (requestURI != null) {
//						System.out.println("requestURI=" + requestURI + "*");
				requestURI = (requestURI.length() == 0 ? request.getContextPath() : requestURI);
				return "redirect:" + requestURI;
			}
			//回首頁
			return "redirect:/";
		} else {
			return "members/login";
		}
	}
	
	
	
	@GetMapping("/processFileReadAction.contoller")
	@ResponseBody
	public ResponseEntity<byte[]> processFileReadAction(@RequestParam(name = "fileId") Integer fileId) throws Exception{
		ResponseEntity<byte[]> re = null;
		MemberFiles file = loginService.getFileById(fileId);
		Blob fileBlob = file.getFileBlob();
		String mimeType = ctx.getMimeType("test.jpg");
		MediaType mediaType = MediaType.valueOf(mimeType);
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			InputStream is = fileBlob.getBinaryStream();
			byte[] b = new byte[81920];
			int len = 0;
			while ((len = is.read(b)) != -1) {
				baos.write(b, 0, len);
			}
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(mediaType);
			headers.setCacheControl(CacheControl.noCache().getHeaderValue());
			re = new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.OK);
			return re;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}
}
