package com.iii.eeit124.shopping.controller;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iii.eeit124.entity.FollowProducts;
import com.iii.eeit124.entity.Members;
import com.iii.eeit124.entity.Products;
import com.iii.eeit124.shopping.dao.MyProductsDao;
import com.iii.eeit124.shopping.dao.MyProductsDaoImpl;
import com.iii.eeit124.shopping.dao.ProductListDaoImpl;
import com.iii.eeit124.shopping.service.MyProductsService;
import com.iii.eeit124.shopping.service.ProductListService;
import com.iii.eeit124.shopping.service.ShoppingAanlysisService;

@Controller
@RequestMapping("/product")
public class MyProductsController {

	@Autowired
	MyProductsService service;

	@Autowired
	ServletContext ctx;
	
	@Autowired
	HttpSession session;
	
	//用@ResponseBody 回傳特定會員商品的json格式給前端
	@GetMapping("/getMyProducts")
	public @ResponseBody List<Products> queryMyProducts(@RequestParam(value = "keywordSearch", required = false)String keywordSearch,Model model){
		List<Products> products =null;
		Integer memberId = ((Members)session.getAttribute("LoginOK")).getId();
		
		if(keywordSearch==null || keywordSearch.equals("")) {			
			products = service.findMyProducts(memberId);
		}else {
			products = service.findMyProducts(memberId,keywordSearch);
		}
		return products;
	}
	
	@GetMapping("/goMyProductPage")
	public String goMyProductPage(Model model) {
		return "products/MyProducts";
	}	
	
	@GetMapping(value = "/myPagingProducts.json", produces = { "application/json; charset=UTF-8" })
	public @ResponseBody Map<String, Object> getPageProducts(
		@RequestParam(value="pageNo", required = false, defaultValue = "1") Integer pageNo,
		@RequestParam(value="totalPage", required = false) Integer totalPage,
		@RequestParam(value="colorId", required = false) Integer colorId,
		@RequestParam(value="categoryId", required = false) Integer categoryId,
		@RequestParam(value="animalTypeId", required = false) Integer animalTypeId,
		@RequestParam(value="recordsPerPage", required = false,defaultValue = "6") Integer recordsPerPage,
		@RequestParam(value ="keywordSearch", required = false) String keywordSearch,
		@RequestParam(value ="orderBy", required = false, defaultValue = "0") Integer orderBy //"0"用color_Id排序
//		@RequestParam(value ="memberId", required = false) Integer memberId 		
			) {
		Integer memberId = ((Members)session.getAttribute("LoginOK")).getId();

		Long recordCounts= (long) 0;
		List<Products> list = new ArrayList<Products>();
		Map<String, Object> map = new HashMap<>();
		
		//抓ProductList.jsp，orderBy的值到ProductListDaoImpl，做switch
		MyProductsDaoImpl.getPageOrderBy(orderBy);
		
		//如果分類、顏色、動物類型有值
		if (colorId != null||categoryId != null||animalTypeId != null ||keywordSearch != null) {
//			if(keywordSearch != null || !(keywordSearch.equals("")) ) {
//				//如果分類、顏色、動物類型有值 + 如果關鍵字有值，就不重算頁數(搜尋全部商品)
//				list = service.getPageProducts(pageNo,recordsPerPage);
//				recordCounts = service.getRecordCounts();
//				totalPage = (int) (Math.ceil(recordCounts / (double) recordsPerPage));
//			}else{	
				//如果分類、顏色、動物類型有值+關鍵字沒值				
				list = service.getPageProducts(pageNo, colorId, categoryId, animalTypeId,recordsPerPage,keywordSearch,memberId);
				
				recordCounts = service.getRecordCounts(colorId, categoryId, animalTypeId,keywordSearch,memberId);
				totalPage = (int) (Math.ceil(recordCounts / (double) recordsPerPage));
//			}
		}else {
			//如果分類、顏色、動物類型沒有值+不管關鍵字有沒有值都不重算
			list = service.getPageProducts(pageNo,recordsPerPage,memberId);
			recordCounts = service.getRecordCounts(memberId);
			totalPage = (int) (Math.ceil(recordCounts / (double) recordsPerPage));
		}
		Members member = (Members)session.getAttribute("LoginOK");
		List<FollowProducts> likeList = new ArrayList();
		if (member != null) {
			likeList = service.getLikeProduct(member.getId());
		}
		map.put("list", list);
		map.put("likeList", likeList);
		map.put("totalPage", totalPage);
		map.put("currPage", pageNo);
		map.put("recordCounts", recordCounts);
		map.put("recordsPerPage", recordsPerPage);
		map.put("keywordSearch",keywordSearch);
		
		return map;
	}
	
	
}
