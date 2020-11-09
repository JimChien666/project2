package com.iii.eeit124.shopping.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.iii.eeit124.entity.AnimalTypes;
import com.iii.eeit124.entity.Categories;
import com.iii.eeit124.entity.Colors;
import com.iii.eeit124.entity.ProductFiles;
import com.iii.eeit124.entity.Products;
import com.iii.eeit124.shopping.service.CreateProductService;



@Controller
@RequestMapping("/product")
public class CreateProductController {
	@Autowired
	private ServletContext ctx;
	@Autowired
	CreateProductService service;
	
	@GetMapping("/CreateProduct")
	public String goCreateProductPage(Model model) {
		Products products = new Products();
		model.addAttribute("products", products); // form:form表單的 modelAttribute
		return "products/CreateProduct";
	}
	
	@GetMapping("/getColors")
	public @ResponseBody List<Colors> queryAllColors(Model model) {
		List<Colors> colors = service.findAllColors();
		return colors;
	}
	
	@GetMapping("/getCategories")
	public @ResponseBody List<Categories> queryAllCategories(Model model) {
		List<Categories> categories = service.findAllCatrgories();
		return categories;
	}
	
	@GetMapping("/getAnimalTypes")
	public @ResponseBody List<AnimalTypes> queryAllAnimalTypes(Model model) {
		List<AnimalTypes> animalTypes = service.findAllAnimalTypes();
		return animalTypes;
	}
	
//	@SuppressWarnings("null")
	@RequestMapping(value = "/processCreateProduct.controller", method = RequestMethod.POST)
	public String processCreateProduct(
			@ModelAttribute("products") Products product,
			@RequestParam(name="animalTypeId") Integer animalTypeId,
			@RequestParam(name="colorId") Integer colorId,
			@RequestParam(name="categoryId") Integer categoryId,
			
			BindingResult result, Model model)
			throws SQLException {
		MultipartFile multipartFile = product.getMultipartFile();
		System.out.println(product);
		System.out.println("animalTypeId" + animalTypeId);
		System.out.println("colorId" + colorId);
		System.out.println("categoryId" + categoryId);
		AnimalTypes animalType = service.findOneAnimalType(animalTypeId);
		Colors color = service.findOneColor(colorId);
		Categories category = service.findOneCatrgory(categoryId);
		product.setCategory(category);
		product.setColor(color);
		product.setAnimalType(animalType);
		if(multipartFile != null || !multipartFile.isEmpty()) {
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
			    product.getMultipartFile().transferTo(saveFile);
			    System.out.println(fileSavePath);
			    HttpHeaders headers = new HttpHeaders();
			    headers.setContentType(MediaType.IMAGE_JPEG);
			    FileInputStream is1 = new FileInputStream(fileSavePath); 
			    byte[] b = new byte[is1.available()];
			    is1.read(b);
			    is1.close();
			    Set<ProductFiles> files = new HashSet<ProductFiles>();
			    Blob blob = new SerialBlob(b);
			    ProductFiles file = new ProductFiles("image", blob);
			    file.setProduct(product);
			    files.add(file);
			    product.setContentImgs(files);
			    
//				   Files file = new Files("image", blob);
//			    product.setCoverImg(blob);
			}catch (IOException e) {
				e.printStackTrace();
			}	
		}
		product.setCreatedAt(new Date());
	    service.insertProduct(product);
		return "redirect:/";
	}
	
}
