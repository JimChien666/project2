package com.iii.eeit124.adopt.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.iii.eeit124.adopt.service.AnimalsService;
import com.iii.eeit124.adopt.service.BreedsService;
import com.iii.eeit124.adopt.service.EmailService;
import com.iii.eeit124.entity.Animals;
import com.iii.eeit124.entity.AnimalsFiles;
import com.iii.eeit124.entity.Breeds;
import com.iii.eeit124.entity.Members;

@Controller
public class SharedController {

	@Autowired
	HttpSession session;
	@Autowired
	ServletContext sc;
	@Autowired
	public AnimalsService animalsService;
	@Autowired
	public BreedsService breedsService;
	@Autowired
	public EmailService emailService;

	@GetMapping("/none")
	public String processNone() {
		return "public/None";
	}

	// 讀圖
	@GetMapping("/filuploadAction.contoller/{id}")
	@ResponseBody
	public ResponseEntity<byte[]> processFileUploadAction(@PathVariable(name = "id") Integer id) throws Exception {
		ResponseEntity<byte[]> re = null;

		Animals animals = animalsService.read(id);// 依主鍵找對應檔案
		Iterator<AnimalsFiles> iterator = animals.getFiles().iterator();// 將檔案從set中撈出
		while (iterator.hasNext()) {
			AnimalsFiles files = (AnimalsFiles) iterator.next();
			Blob fileBlob = files.getFileBlob();
			String mimeType = sc.getMimeType(files.getFileName());
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
		}
		return re;
	}

	// SelectBreeds
	@GetMapping("/getBreed.controller")
	public @ResponseBody List<Breeds> processGetBreed(@RequestParam("family") String family) {
		List<Breeds> breed = breedsService.readAllBreeds(family);
		return breed;
	}
	// SelectBreeds//可用
//	@GetMapping(value="/getBreed.controller")
//	public @ResponseBody List<String> processGetBreed(@RequestParam("family") String family) {
//		List<String> breed = breedsService.readAllBreeds(family);
//		System.out.println("breed123"+breed);
//		return breed;
//	}

	// ==============================================================================================

	// 查詢
	@GetMapping("/readKeyword.controller")
	public @ResponseBody List<List<Animals>> processReadKeyword(@RequestParam("factor1") String factor1, Model m) {
		List<List<Animals>> list = new ArrayList<List<Animals>>();
//		List<Animals> readAnimal2 = animalsService.readAnimals1("coat_color = '黑'");// 用毛色讀取
////		List<Animals> readAnimal2 = animalsService.readAnimals1("coat_color like '%" + factor1 + "%'");// 用毛色讀取
//		if (readAnimal2.size() > 0) {
//			System.out.println("inside2");
//			list.add(readAnimal2);
//			return list;
//		}
		if (null != breedsService.readAllBreeds(factor1)) {
			List<Breeds> readAllBreeds = breedsService.readAllBreeds(factor1);// 讀類別
			if (readAllBreeds.size() > 0) {
				System.out.println("inside0");
				for (int i = 0; i < readAllBreeds.size(); i++) {
					List<Animals> readAnimal = animalsService
							.readAnimals1("breed_id = " + readAllBreeds.get(i).getBreedId());
					list.add(readAnimal);
				}
				return list;
			}
		}
		if (null != breedsService.readBreed(factor1)) {
			List<Breeds> readBreed = breedsService.readBreed(factor1);// 讀品種取得id
			if (readBreed.size() > 0) {
				System.out.println("inside1");
				for (int i = 0; i < readBreed.size(); i++) {
					List<Animals> readAnimal = animalsService
							.readAnimals1("breed_id = " + readBreed.get(i).getBreedId());
					list.add(readAnimal);
				}
				return list;
			}
		}
//		if (null != animalsService.readAnimals1("acception_Id = '" + factor1.trim() +"'")) {
//			List<Animals> readAnimal3 = animalsService.readAnimals1("acception_Id = '" + factor1.trim() +"'");// 用收容編號讀取
////		List<Animals> readAnimal3 = animalsService.readAnimals1("acception_Id like '%" + factor1 + "%'");// 用收容編號讀取
//			if (readAnimal3.size() > 0) {
//				System.out.println("factor1:"+factor1);
//				System.out.println("inside3 readAnimal3.size():" + readAnimal3.size());
//				System.out.println(readAnimal3);
//				list.add(readAnimal3);
//				return list;
//			}
//		}
		return list;
	}

	@GetMapping("/adopt")
	public String processAdopt(Model m) {
		m.addAttribute("AnimalsList", animalsService.readAll());
		m.addAttribute("source", "AdoptAnimal");
		return "adopt/ReadAnimal";
	}

	// 瀏覽動物詳細頁
	@GetMapping("/AdoptAnimalDetails.controller")
	public String processAdoptAnimalDetail(@RequestParam(name = "id") Integer id,
//			@ModelAttribute("animal") Animals entity, 
			Model m) {
		Animals animals = animalsService.read(id);
		m.addAttribute("source", "AdoptAnimal");
		m.addAttribute("animal", animals);
		if ((Members) session.getAttribute("LoginOK") == null) {
			m.addAttribute("member", 0);
		} else {
			m.addAttribute("member", ((Members) session.getAttribute("LoginOK")).getId());
		}
		return "adopt/ReadAnimalDetails";
	}

	@GetMapping("/adoptApplyMail.controller")
	public String processMail(Model m) {
		emailService.sendSimpleMessage("weybrian@gmail.com", "串起來!!", "成功拉~~");
		System.out.println("寄信成功");
		m.addAttribute("AnimalsList", animalsService.readAll());
		m.addAttribute("source", "AdoptAnimal");
		return "adopt/ReadAnimal";
	}
}
