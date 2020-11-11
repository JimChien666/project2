package com.iii.eeit124.shopping.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iii.eeit124.entity.Products;
import com.iii.eeit124.shopping.service.ProductListService;



@Controller
@RequestMapping("/product")
public class ProdcutListController {
	@Autowired
	ProductListService service;
	
	@Autowired
	ServletContext ctx;
	
	@GetMapping("/ProductList")
	public String goProductListPage(Model model) {
		return "products/ProductList";
	}
	@GetMapping("/getProducts")
	public @ResponseBody List<Products> queryAllProducts(Model model){
		List<Products> prodcuts = service.findAllProducts();
		return prodcuts;
	}
	
	@GetMapping(value = "/pagingProducts.json", produces = { "application/json; charset=UTF-8" })
	public @ResponseBody Map<String, Object> getPageProducts(
		@RequestParam(value="pageNo", required = false, defaultValue = "1") Integer pageNo,
		@RequestParam(value="totalPage", required = false) Integer totalPage		
			) {
		if (totalPage == null) {     		
	       	totalPage = service.getTotalPages();
	    }
		
		Map<String, Object> map = new HashMap<>();

		List<Products> list = service.getPageProducts(pageNo);
		map.put("list", list);
		map.put("totalPage", totalPage);
		map.put("currPage", pageNo);

		return map;
	}
	
	@GetMapping(value="/getProductImage")
	public ResponseEntity<byte[]> getProductImage(@RequestParam("productId") Integer productId) {
		ResponseEntity<byte[]> re = null;
		Products product = service.getProduct(productId);
		Blob blob = product.getCoverImg();
		String mimeType = ctx.getMimeType("abc.jpg");
		MediaType mediaType = MediaType.valueOf(mimeType);
		HttpHeaders headers = new HttpHeaders();
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			InputStream is = blob.getBinaryStream();
			byte[] b = new byte[81920];
			int len = 0;
			while ((len = is.read(b)) != -1) {
				baos.write(b, 0, len);
			}
			headers.setContentType(mediaType);
			headers.setCacheControl(CacheControl.noCache().getHeaderValue());
			re = new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}
}
