package com.iii.eeit124.adopt.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
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

	@GetMapping("/adopt")
	public String processAdopt(Model m) {
		m.addAttribute("AnimalsList", animalsService.readAll());
		m.addAttribute("source", "AdoptAnimal");
		return "adopt/AdoptAnimal";
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
}
