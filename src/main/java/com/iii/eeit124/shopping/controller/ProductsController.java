package com.iii.eeit124.shopping.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.iii.eeit124.entity.MemberFiles;
import com.iii.eeit124.entity.Products;
import com.iii.eeit124.shopping.service.ProductsService;

@Controller

public class ProductsController {
	@Autowired
	private ServletContext ctx;
	
	@Autowired
	private ProductsService productsService;
	
	@Autowired
	private HttpServletRequest request;
	
	@ModelAttribute("products.controller")
	public Products formBackingObject() {
		return new Products();
	}
	
	//顯示商品首頁
	@GetMapping(path="/products")
	public String getAllList(Model model) {
		if (model.getAttribute("ProductList") == null) {
		model.addAttribute("ProductList", productsService.selectAll());
		}		
		return "products/ProductList";
	}	
	
	//新增商品
	@RequestMapping(value = "/insert/products", method = RequestMethod.GET)
	public String insertProduct(Model model) {
		Products products = new Products();
		model.addAttribute("products", products); // form:form表單的 modelAttribute
		return "products/InsertProduct";
	}

	@RequestMapping(value = "/insert/addProducts", method = RequestMethod.POST)
	public String submit(@ModelAttribute("products") Products products, BindingResult result, Model model)
			throws SQLException {

//		if (result.hasErrors()) {// IF有錯，就會導到首頁
//			model.addAttribute("ProductList", productsService.selectAll());
//			return "products/ProductList";
//		}
		//上傳圖片
		MultipartFile multipartFile = products.getProfileImage();
		if(multipartFile != null || !multipartFile.isEmpty()) {
//			String fileName = ctx.getRealPath("/")+"resources/images/"+multipartFile.getOriginalFilename();
			try {
				String fileName = multipartFile.getOriginalFilename();

				String fileTempDirPath = ctx.getRealPath("/") + "UploadTempDir\\";
				   File dirPath = new File(fileTempDirPath);
				   if(!dirPath.exists()) {
				    boolean status = dirPath.mkdirs();
				    System.out.println("status" + status);
				   }
				   
				   String fileSavePath = fileTempDirPath + fileName;
				   File saveFile = new File(fileSavePath);
				   products.getProfileImage().transferTo(saveFile);
				   System.out.println(fileSavePath);
				   
				   HttpHeaders headers = new HttpHeaders();
				   headers.setContentType(MediaType.IMAGE_JPEG);
				   FileInputStream is1 = new FileInputStream(fileSavePath);
				   byte[] b = new byte[is1.available()];
				   
				   is1.read(b);
				   is1.close();
				   Set<MemberFiles> files = new HashSet<MemberFiles>();
				   Blob blob = new SerialBlob(b);
//				   Files file = new Files("image", blob);
				   products.setImg(blob);
				   productsService.insert(products);
				   return "redirect:/products";

			}catch (IOException e) {
				e.printStackTrace();
			}
		}
//		productsService.insert(products);
		return "redirect:/products";
	}
		
	//用名子與描述模糊查詢
	@PostMapping("/select/productsByName")
	public String getQueryPage(@RequestParam(value = "keyword") String keyword, Locale locale, Model model) {
		model.addAttribute("ProductList", productsService.selectByName(keyword));
		return getAllList(model);
		
	}
	
	//更新
	@GetMapping("/postSelectById/products/{id}")
	public String getUpdatePage(@PathVariable(value = "id") Integer id, Locale locale, Model model) {
		model.addAttribute("ProductList", productsService.select(id));
		System.out.println(id);
		return "products/UpdateProduct";		
	}
	@PostMapping("/updateProduct")
	public String updateProducts(@ModelAttribute("products")Products products, BindingResult result, Model model) throws SQLException {
		model.addAttribute("products", products);
		productsService.update(products);
		return "redirect:/products";
	}
	
	//刪除(先查詢，後進入確認頁面)
	@GetMapping("/getSelectById/products/{id}")
	public String getSelectPage(@PathVariable(value = "id") Integer id, Locale locale, Model model) {
		model.addAttribute("ProductList", productsService.selectById(id));
		return "products/DeleteCheck";
	}	
	@GetMapping("/delete/products/{id}")
	public String getDeletePage(@PathVariable(value = "id") Integer id, Locale locale, Model model) {		
		model.addAttribute("ProductList", productsService.delete(id));
		return "redirect:/products";
	}
			

	
	//顯示圖片
	@RequestMapping("/image/{id}")
	@ResponseBody
	public ResponseEntity<byte[]> getImage(@PathVariable("id")Integer id) throws IOException, SQLException {
//		String fileName = ctx.getRealPath("/")+"/images/NoImage.png";
		byte[] img=null;

//				/project3v1/WebContent/WEB-INF/views/products/images/NoImage.png;
		Products products = productsService.select(id);
		try {
			img =  products.getImg().getBytes(1, (int) products.getImg().length());
		} catch (Exception e) {
			System.out.println("fuck");
			System.out.println(ctx.getRealPath("/") + "images/NoImage.png");
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			File nFile = new File(ctx.getRealPath("/") + "images/NoImage.png");
			BufferedImage bi = ImageIO.read(nFile);
			ImageIO.write(bi, "jpg", baos);
			img = baos.toByteArray();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		ResponseEntity<byte[]> entity = new ResponseEntity(img, headers, HttpStatus.OK);
		return entity;
	}

}
