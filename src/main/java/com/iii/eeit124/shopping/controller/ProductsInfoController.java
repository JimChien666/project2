package com.iii.eeit124.shopping.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iii.eeit124.entity.ProductFiles;
import com.iii.eeit124.entity.Products;
import com.iii.eeit124.shopping.service.ProductFilesService;
import com.iii.eeit124.shopping.service.ProductListService;

@Controller
@RequestMapping("/product")
public class ProductsInfoController {
	@Autowired
	ProductListService service;
	
	@Autowired
	ProductFilesService productFilesService;
	
	@Autowired
	ServletContext ctx;
	
	@GetMapping("/productsInfo/{id}")
	public String queryProductsById(@PathVariable(value = "id") Integer id,Model model){
		model.addAttribute("ProductsInfo", service.getProduct(id));
		return "/products/ProductsInfo";
	}
	
	
	@GetMapping(value="/getProductFilesImage")
	public ResponseEntity<byte[]> getProductImage(@RequestParam("productFilesId") Integer productFilesId) throws IOException {
		ResponseEntity<byte[]> re = null;
		ProductFiles productFiles = productFilesService.getProductFiles(productFilesId);
		Blob blob = productFiles.getFileBlob();
		String mimeType = ctx.getMimeType("abc.jpg");
		MediaType mediaType = MediaType.valueOf(mimeType);
		HttpHeaders headers = new HttpHeaders();
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			InputStream is = blob.getBinaryStream();
			byte[] b = new byte[163840];
			int len = 0;
			while ((len = is.read(b)) != -1) {
				baos.write(b, 0, len);
			}
			headers.setContentType(mediaType);
			headers.setCacheControl(CacheControl.noCache().getHeaderValue());
			re = new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.OK);
		} catch (Exception e) {
			//載入失敗就給預設圖片
			System.out.println(ctx.getRealPath("/") + "images/NoImage.png");
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			File nFile = new File(ctx.getRealPath("/") + "images/NoImage.png");
			BufferedImage bi = ImageIO.read(nFile);
			ImageIO.write(bi, "jpg", baos);
			re = new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.OK);
		}
		return re;
	}
	
}
