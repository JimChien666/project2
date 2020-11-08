package com.iii.eeit124.member.controller;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Blob;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.iii.eeit124.entity.Files;
import com.iii.eeit124.entity.Members;
import com.iii.eeit124.member.service.RegisterService;
import com.iii.eeit124.util.GlobalService;


@Controller
@SessionAttributes(names={"member"})
public class RegisterController {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	RegisterService registerService;
	@Autowired
	private HttpSession session;

	
//	@GetMapping("/testInsert")
//	public void testInsert(Model m) {
////		System.out.println(memberService.accountExists("s1875678"));
//		Members member=new Members("d", "d", "d", "d", "d", "d", "d", 2, "d");
//		member.getId();
////		m.addAttribute("member", member);
//		sessionFactory.getCurrentSession().save(member);
////		memberService.saveMember(member);
//		
////		return "members/register";
//	}
	
	@GetMapping("/register")
	public String getRegisterPage(Model m) {
		Members member=new Members();
		m.addAttribute("member", member);
		
		return "members/register";
	}
	
	@PostMapping("/processRegister.controller")
 	public String processRegister(@ModelAttribute("member") Members member, @RequestParam(name = "checkPassword")String checkPassword, @RequestParam(name = "myFiles") MultipartFile mFile, Model m) {
		//檢查資料正確性
		System.out.println(member.getName());
		System.out.println(mFile);
		Map<String, String> errors = new HashMap<String, String>();
		Map<String, Members> msgOK = new HashMap<String, Members>();
		m.addAttribute("errors", errors);
		
		if("".equals(member.getName())||member.getName()==null) {
			errors.put("name", "請填入名稱");
		}
		if("".equals(member.getSex())||member.getSex()==null) {
			errors.put("sex", "請選擇性別");
		}
		if("".equals(member.getTel())||member.getTel()==null) {
			errors.put("tel", "請填入電話");
		}
		if("".equals(member.getAccount())||member.getAccount()==null) {
			errors.put("account", "請填入帳號");
		}
		if("".equals(member.getPassword())||member.getPassword()==null) {
			errors.put("password", "請填入密碼");
		}
		if("".equals(member.getEmail())||member.getEmail()==null) {
			errors.put("email", "請填入email");
		}
		if("".equals(member.getAddress())||member.getAddress()==null) {
			errors.put("address", "請填入地址");
		}
		if("".equals(member.getMemberType())||member.getMemberType()==null) {
			errors.put("memberType", "請選擇身份");
		}
		if(checkPassword=="" || checkPassword.length()==0) {
			errors.put("checkPassword", "請確認密碼");
		}
		if(mFile==null) {
			errors.put("file", "請選擇照片");
		}
		
//		if(errors!=null&&!errors.isEmpty()) {
//			return "members/register";
//		}
		if ((!checkPassword.equals(member.getPassword()))||(errors!=null&&!errors.isEmpty())) {
			errors.put("errorCheck", "確認密碼不正確");
			return "members/register";
		}
		try {
			if (registerService.accountExists(member.getAccount())){
				errors.put("errorAccountDup", "此帳號已存在，請換新帳號");
			} else {
				member.setPassword(GlobalService.getMD5Endocing(GlobalService.encryptString(member.getPassword())));
			}
			member.setAdoptedLevelId(0);
			System.out.println(member);
			//新增照片
			
			String fileName = mFile.getOriginalFilename();
			String fileTempDirPath = request.getSession().getServletContext().getRealPath("/") + "UploadTempDir\\";
			File dirPath = new File(fileTempDirPath);
			if(!dirPath.exists()) {
				boolean status = dirPath.mkdirs();
				System.out.println("status" + status);
			}
			
			String fileSavePath = fileTempDirPath + fileName;
			File saveFile = new File(fileSavePath);
			mFile.transferTo(saveFile);
			System.out.println(fileSavePath);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);
			FileInputStream is1 = new FileInputStream(fileSavePath);
			byte[] b = new byte[is1.available()];
			
			is1.read(b);
			is1.close();
			Set<Files> files = new HashSet<Files>();
			Blob blob = new SerialBlob(b);
			Files file = new Files("image", blob);
			
			files.add(file);
			member.setFiles(files);
			file.setMember(member);
//			System.out.println(member);
			member.setCreatedAt(new Date());
//			int n = registerService.saveRegister(member, files);
			int n = registerService.saveMember(member);
			if (n == 1) {
				session.setAttribute("LoginOK", member);
				return "redirect:/";
			} else {
				errors.put("errorAccountDup", "新增此筆資料有誤(RegisterServlet)");
			}
			if (!errors.isEmpty()) {
				// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
				return "members/register";
			}
		}catch (Exception e) {
			e.printStackTrace();
			errors.put("errorIDDup", e.getMessage());
			return "members/register";
		}

		return "members/register";
	}
	
}
