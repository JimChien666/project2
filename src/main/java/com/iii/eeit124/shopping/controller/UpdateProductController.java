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
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.iii.eeit124.entity.AnimalTypes;
import com.iii.eeit124.entity.Categories;
import com.iii.eeit124.entity.Colors;
import com.iii.eeit124.entity.Members;
import com.iii.eeit124.entity.ProductFiles;
import com.iii.eeit124.entity.Products;
import com.iii.eeit124.shopping.service.CreateProductService;
import com.iii.eeit124.shopping.service.ProductFilesService;



@Controller
@RequestMapping("/product")
public class UpdateProductController {
	@Autowired
	private ServletContext ctx;
	@Autowired
	CreateProductService service;
	@Autowired
	ProductFilesService productFilesService;
	@Autowired
	HttpSession session;
	
	
//	有@ModelAttribute的方法，會被SpringMVC在每個方法前被調用
	@ModelAttribute("products")
//	public void getProduct(@PathVariable(value="id",required=false)Integer id,Map<String,Object> map,Model model) {
	public void getProduct(@PathVariable(value="id",required=false)Integer id,Map<String,Object> map,Model model) {
		
		if(id != null) {
			Products products = service.selectById(id);	
			map.put("products",products); //要跟Bean 的名子一樣(開頭小寫)
			
			map.put("Colors",service.findAllColors());
			map.put("Categories",service.findAllCatrgories());
			map.put("AnimalTypes",service.findAllAnimalTypes());		
		}	
	}
	
	@GetMapping("/preUpdateProduct/{id}")
	public  String getQueryPage(@PathVariable(value = "id")Integer id,Model model) {
		
		return "products/UpdateProduct";
	}

	@RequestMapping(value = "/processUpdateProduct.controller", method = RequestMethod.POST)
	public String processUpdateProduct(
			@ModelAttribute("products")Products products,
			@RequestParam(name="animalTypeId", required = false) Integer animalTypeId,
			@RequestParam(name="colorId", required = false) Integer colorId,
			@RequestParam(name="categoryId", required = false) Integer categoryId,
			@RequestParam(name="contentImage", required = false) MultipartFile[] multipartfiles,
			BindingResult result, Model model)
			throws SQLException, IOException {
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("errors", errors);
		System.out.println(products);
		if("".equals(products.getName())||products.getName()==null) {
			errors.put("name", "請填入商品名稱");
		}
		if(products.getPrice()==null) {
			errors.put("price", "請填入價格");
		}
		if("".equals(products.getDescription())||products.getDescription()==null) {
			errors.put("description", "請填入商品介紹");
		}
		if("".equals(products.getStatus())||products.getStatus()==null) {
			errors.put("status", "請選擇商品狀態");
		}
		if(products.getQuantity()==null) {
			errors.put("quantity", "請填入數量");
		}
		if(products.getPrice()==null) {
			errors.put("price", "請填入價格");
		}
		if(products.getDiscount()==null) {
			errors.put("discount", "請填入折扣");
		}		
		if(products.getAnimalTypeId()==null) {
			errors.put("animalType", "請選擇寵物類別");
		}
		if(products.getColorId()==null) {
			errors.put("color", "請選擇商品顏色");
		}
		if(products.getCategoryId()==null) {
			errors.put("category", "請選擇商品分類");
		}
		
		if (errors!=null&&!errors.isEmpty()) {
			return "products/UpdateProduct/";
		}
		
		//抓form:form的商品封面圖
		MultipartFile multipartFile = products.getMultipartFile();
		
		AnimalTypes animalType = service.findOneAnimalType(products.getAnimalTypeId());
		Colors color = service.findOneColor(products.getColorId());
		Categories category = service.findOneCatrgory(products.getCategoryId());
		products.setColor(color);
		products.setAnimalType(animalType);
		products.setCategory(category);
		//用ID查詢原本的Product BEAN
		Products product = service.selectById(products.getId());

		//new HashSet<ProductFiles>()
//		內容圖片
		Set<ProductFiles> productFilesSet = new HashSet<ProductFiles>();
			//遍歷並儲存檔案
			try {
				int contentImages=0 ;

				
				for(MultipartFile file : multipartfiles){
					if((file != null) && (file.getBytes().length > 0)){

					String fileName = file.getOriginalFilename(); //得到原始檔名
					
					String fileTempDirPath = ctx.getRealPath("/") + "UploadTempDir\\"; //創一個臨時資料夾
					File dirPath = new File(fileTempDirPath);
					if(!dirPath.exists()) {
					    boolean status = dirPath.mkdirs();
					    System.out.println("status" + status);
					}
					String fileSavePath = fileTempDirPath + fileName;
						
				    File saveFile = new File(fileSavePath); 				    
				    file.transferTo(saveFile);
				    
				    HttpHeaders headers = new HttpHeaders();
				    headers.setContentType(MediaType.IMAGE_JPEG);
				    FileInputStream is1 = new FileInputStream(fileSavePath); 
				    byte[] b = new byte[is1.available()];
				    is1.read(b);
				    is1.close();
				    Blob blob = new SerialBlob(b);

				    //查詢ProductFiles，會有兩個ProductFiles Bean，回傳List
				    List<ProductFiles> productFiles = productFilesService.getProductFilesList(products.getId());
				    
				    //取出productFiles，第一次contentImages=0，第二次contentImages=1
				    ProductFiles productFile = productFiles.get(contentImages);

					// 把使用者上傳圖片裝到productFiles的fileBlob
					productFile.setFileBlob(blob);
					//
					products.setContentImgs(productFilesSet);

					// 把Product裝到productFiles，
					productFile.setProduct(products);

					// 把productFiles,add到HashSet裡
					productFilesSet.add(productFile);
					
					//為了讀產品照片
					contentImages += 1;
					}
				}				

			}catch (IOException e) {
				System.out.println("UpdateProductController.java，Line 190");

				errors.put("errorAccountDup", "修改此筆資料有誤(RegisterServlet)");
				return "products/UpdateProduct";
			}			
		
//		封面圖片
		if(multipartFile != null || (!multipartFile.isEmpty()) ) {
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
			    
			    System.out.println(fileSavePath);
			    HttpHeaders headers = new HttpHeaders();
			    headers.setContentType(MediaType.IMAGE_JPEG);
			    
			    if (fileName != null && fileName.length() != 0) {
				    products.getMultipartFile().transferTo(saveFile);

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
	//				Files file = new Files("image", blob);
	//			    product.setCoverImg(blob);
				    products.setCoverImg(blob);
				}else {
					
					products.setCoverImg(product.getCoverImg());

				}    
			}catch (IOException e) {
				System.out.println("UpdateProductController.java，Line 235");

				errors.put("errorAccountDup", "修改此筆資料有誤(RegisterServlet)");
				return "products/UpdateProduct";
			}	
		}
//		products.setName(products.getName());
//		products.setStatus(products.getStatus());
//		products.setQuantity(products.getQuantity());
//		products.setDescription(products.getDescription());
		products.setCreatedAt(product.getCreatedAt());		
		products.setUpdatedAt(new Date());
		products.setMember((Members)session.getAttribute("LoginOK"));
	    service.updateProduct(products);
	    
		return "redirect:/";
	}
	
}
