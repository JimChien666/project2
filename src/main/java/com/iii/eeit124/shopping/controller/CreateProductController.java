package com.iii.eeit124.shopping.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
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
import com.iii.eeit124.entity.Members;
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
	@Autowired
	HttpSession session;
	
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
	
	//CreateProduct.jsp輸入後回來
	@RequestMapping(value = "/processCreateProduct.controller", method = RequestMethod.POST)
	public String processCreateProduct(
			@ModelAttribute("products") Products product,
			@RequestParam(name="animalTypeId", required = false) Integer animalTypeId,
			@RequestParam(name="colorId", required = false) Integer colorId,
			@RequestParam(name="categoryId", required = false) Integer categoryId,
			@RequestParam(name="contentImage") MultipartFile[] multipartfiles,
			BindingResult result, Model model)
			throws SQLException, IOException {
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("errors", errors);
		System.out.println(product);
		if("".equals(product.getName())||product.getName()==null) {
			errors.put("name", "請填入商品名稱");
		}
		if(product.getPrice()==null) {
			errors.put("price", "請填入價格");
		}
		if("".equals(product.getDescription())||product.getDescription()==null) {
			errors.put("description", "請填入商品介紹");
		}
		if("".equals(product.getStatus())||product.getStatus()==null) {
			errors.put("status", "請選擇商品狀態");
		}
		if(product.getQuantity()==null) {
			errors.put("quantity", "請填入數量");
		}
		if(product.getPrice()==null) {
			errors.put("price", "請填入價格");
		}
		if(product.getDiscount()==null) {
			errors.put("discount", "請填入折扣");
		}
		
		if(animalTypeId==null) {
			errors.put("animalType", "請選擇寵物類別");
		}
		if(colorId==null) {
			errors.put("color", "請選擇商品顏色");
		}
		if(categoryId==null) {
			errors.put("category", "請選擇商品分類");
		}
		
		if (errors!=null&&!errors.isEmpty()) {
			return "products/CreateProduct";
		}
		

		MultipartFile multipartFile = product.getMultipartFile();

		AnimalTypes animalType = service.findOneAnimalType(animalTypeId);
		Colors color = service.findOneColor(colorId);
		Categories category = service.findOneCatrgory(categoryId);
		product.setCategory(category);
		product.setColor(color);
		product.setAnimalType(animalType);
		
//		內容圖片
		if(multipartfiles != null && multipartfiles.length > 0){
			//遍歷並儲存檔案
			try {
				for(MultipartFile file : multipartfiles){
					String fileName = multipartFile.getOriginalFilename();
	
					String fileTempDirPath = ctx.getRealPath("/") + "UploadTempDir\\";
					File dirPath = new File(fileTempDirPath);
					if(!dirPath.exists()) {
					    boolean status = dirPath.mkdirs();
					    System.out.println("status" + status);
					}
					String fileSavePath = fileTempDirPath + fileName;
					
					
				    File saveFile = new File(fileSavePath);
				    ProductFiles productFiles = new ProductFiles();
				    
				    productFiles.getMultipartFile().transferTo(saveFile);
				    
				    HttpHeaders headers = new HttpHeaders();
				    headers.setContentType(MediaType.IMAGE_JPEG);
				    FileInputStream is1 = new FileInputStream(fileSavePath); 
				    byte[] b = new byte[is1.available()];
				    is1.read(b);
				    is1.close();
				    Blob blob = new SerialBlob(b);
				    Set<ProductFiles> files = new HashSet<ProductFiles>();
				    productFiles.setFileBlob(blob);
				    
				    
//				    Set<ProductFiles> files = new HashSet<ProductFiles>();
//				    ProductFiles productFiles = new ProductFiles("image", blob);
				    productFiles.setProduct(product);
				    files.add(productFiles);
				    product.setContentImgs(files);
				}	

			}catch (IOException e) {
				errors.put("errorAccountDup", "新增此筆資料有誤(RegisterServlet)");
				return "products/CreateProduct";
			}
		}
		
		if(multipartFile != null || !(multipartFile.isEmpty()) ) {
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
			    Blob blob = new SerialBlob(b);
//			    Set<ProductFiles> files = new HashSet<ProductFiles>();
//			    ProductFiles file = new ProductFiles("image", blob);
//			    file.setProduct(product);
//			    files.add(file);
//			    product.setContentImgs(files);
//			    
			    product.setCoverImg(blob);
//				   Files file = new Files("image", blob);
//			    product.setCoverImg(blob);
			}catch (IOException e) {
				errors.put("errorAccountDup", "新增此筆資料有誤(RegisterServlet)");
				return "products/CreateProduct";
			}	
		}
		product.setCreatedAt(new Date());
		product.setMember((Members)session.getAttribute("LoginOK"));
	    service.insertProduct(product);
//	    service.insertProduct(product);
	    
		return "redirect:/";
	}
	
}
