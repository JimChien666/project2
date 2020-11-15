package com.iii.eeit124.adopt.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Iterator;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iii.eeit124.adopt.service.AnimalsService;
import com.iii.eeit124.entity.Animals;
import com.iii.eeit124.entity.AnimalsFiles;

@Controller
public class AdoptController {
	@Autowired
	ServletContext sc;
	@Autowired
	AnimalsService animalsService;
	
	@GetMapping("/adoptDispatcher")
	public String processAdoptDispatcher() {
		return "adopt/AdoptDispatcher";
	}
	
	//讀圖
	@GetMapping("/filuploadAction.contoller/{id}")
	@ResponseBody
	public ResponseEntity<byte[]> processFileUploadAction(@PathVariable(name = "id") Integer id) throws Exception{
		ResponseEntity<byte[]> re = null;
		
		Animals animals = animalsService.read(id);//依主鍵找對應檔案
		Iterator<AnimalsFiles> iterator = animals.getFiles().iterator();//將檔案從set中撈出
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
}
