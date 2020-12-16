package com.iii.eeit124.member.controller;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.iii.eeit124.entity.Animals;
import com.iii.eeit124.entity.AnimalsFiles;
import com.iii.eeit124.entity.Breeds;
import com.iii.eeit124.entity.MemberFiles;
import com.iii.eeit124.entity.Members;
import com.iii.eeit124.member.service.MemberCenterService;
import com.iii.eeit124.shopping.service.ShoppingAanlysisService;

@Controller
@RequestMapping("/member")
public class MemberCenterController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	ShoppingAanlysisService service;
	
	@Autowired
	MemberCenterService memberCenterService;
	
	@GetMapping("/myAccount")
	public String getMemberCenterPage() {
		return "members/memberCenter";
	}
	@GetMapping("/updateMember")
	public String updateMember() {
		return "members/UpdateMember";
	}
	
////列表中按鈕選更新
	@RequestMapping(path = "/myAccount.controller", method = RequestMethod.GET)
	public String processAdminSelectPost(
//			@RequestParam(name = "id") Integer id, 
			Model m) {

//		Members aMember = memberCenterService.get(id);
//		m.addAttribute("id", id);
		m.addAttribute("aMember", (Members)session.getAttribute("LoginOK"));
		
		return "members/updateMember";
	}

	//// 更新好後取到資料庫內資料SET後UPDATE
	@RequestMapping(path = "/UpdateMember.controller", method = RequestMethod.POST)
	public String processUpdatePost(@RequestParam(name = "id") Integer id, @RequestParam(name = "name") String name,
			@RequestParam(name = "memberType") String memberType, @RequestParam(name = "sex") String sex,
			@RequestParam(name = "tel") String tel, @RequestParam(name = "address") String address,
			@RequestParam(name = "email") String email, @RequestParam(name = "account") String account) {

		Members post = (Members)session.getAttribute("LoginOK");

		post.setName(name);
		post.setMemberType(memberType);
		post.setSex(sex);
		post.setTel(tel);
		post.setAddress(address);
		post.setEmail(email);
		post.setAccount(account);
		memberCenterService.update(post);
		return "members/memberCenter";
	}
	
//	// Update
//		@PostMapping("/UpdateMember.controller")
//		public String processUpdateMember(@ModelAttribute("members") Members entity,Model m) throws Exception {
//			MemberFiles content = new MemberFiles();
//			// 更新照片部分
//			MultipartFile mFile = entity.getMemberFiles();
//			Integer animalId = entity.getId();
//			if (!mFile.isEmpty()) {// mFile.isEmpty()為判斷是否有上傳圖片
//				String filename = mFile.getOriginalFilename();// 取得檔名
//				String fileTempDirPath = sc.getRealPath("/") + "uploadTempDir\\";// 存放的資料夾
//
//				File dirPath = new File(fileTempDirPath);
//
//				// 建資料夾
//				if (!dirPath.exists()) {
//					boolean status = dirPath.mkdirs();
//					System.out.println("status:" + status);
//				}
//
//				// 設儲存路徑
//				String fileSavePath = fileTempDirPath + filename;
//				File saveFile = new File(fileSavePath);
//				mFile.transferTo(saveFile);
//				System.out.println("fileSavePath:" + fileSavePath);// 檔案路徑
//
//				// 設定圖片格式
//				HttpHeaders headers = new HttpHeaders();
//				headers.setContentType(MediaType.IMAGE_JPEG);
//
//				if (filename != null && filename.length() != 0) {
//					FileInputStream is1 = new FileInputStream(fileSavePath);
//					byte[] b = new byte[is1.available()];
//					is1.read(b);
//					is1.close();
//
//					Set<MemberFiles> MemberFiles = meberService.read(id).getFiles();
//					Blob blob = new SerialBlob(b);
//
//					Iterator<MemberFiles> iterator = MemberFiles.iterator();
//					while (iterator.hasNext()) {
//						content = (MemberFiles) iterator.next();
//						System.out.println("filename" + filename);
//						content.setFileType("image");
//						content.setFileName(filename);
//						content.setFileBlob(blob);
////						content.setAnimals(entity);//
//						entity.setFiles(AnimalsFiles);
//						System.out.println("content" + content);
//					}
//				}
//			}
//			
//			entity.setMember((Members) session.getAttribute("LoginOK"));
//
//			return "redirect:/MemberCenter/ReadAnimalDetails.controller?id="+animalId;//似乎不能用{}pathvariable傳值
//		}
}
