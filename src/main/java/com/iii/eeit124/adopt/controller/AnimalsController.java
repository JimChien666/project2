package com.iii.eeit124.adopt.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.iii.eeit124.adopt.ImgurAPI;
import com.iii.eeit124.adopt.service.AdoptionRecordsService;
import com.iii.eeit124.adopt.service.AnimalsService;
import com.iii.eeit124.adopt.service.BreedsService;
import com.iii.eeit124.entity.AdoptionRecords;
import com.iii.eeit124.entity.Animals;
import com.iii.eeit124.entity.AnimalsFiles;
import com.iii.eeit124.entity.Breeds;
import com.iii.eeit124.entity.ImageResponse;
import com.iii.eeit124.entity.Members;
//import okhttp3.MediaType;
import okhttp3.RequestBody;
import oracle.net.aso.m;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Controller
@RequestMapping("/MemberCenter")
public class AnimalsController {
	@Autowired
	ServletContext sc;
	@Autowired
	HttpSession session;
	@Autowired
	public AnimalsService animalsService;
	@Autowired
	public BreedsService breedsService;
	@Autowired
	public AdoptionRecordsService adoptionRecordsService;
	static final ImgurAPI imgurApi = createImgurAPI();
	
	//瀏覽全部的動物
//	@GetMapping("/ReadAllAnimal")
//	public String processAllPetsRead(Model m) {
//		m.addAttribute("AnimalsList", animalsService.readAll(((Members)session.getAttribute("LoginOK")).getId()));
//		return "adopt/ReadAnimal";
//	}

	// 瀏覽會員的動物
	@GetMapping("/ReadAnimal")
	public String processMyPetsRead(Model m) {
		m.addAttribute("AnimalsList", animalsService.readMyAnimals(((Members)session.getAttribute("LoginOK")).getId()));
		m.addAttribute("source", "MyAnimals");
		return "adopt/ReadAnimal";
	}
	
	//瀏覽動物詳細頁
	@GetMapping("/ReadAnimalDetails.controller")
	public String processReadAnimalDetail(@RequestParam(name = "id") Integer id, 
//			@ModelAttribute("animal") Animals entity, 
			Model m) {
		System.out.println("inside processReadAnimalDetail");
		Animals animals = animalsService.read(id);
		m.addAttribute("source", "ReadAnimal");
		m.addAttribute("animal", animals);
		return "adopt/ReadAnimalDetails";
	}

//==============================================================================================	

	// PreCreate
	@GetMapping("/preCreateAnimal.controller")
	public String processPreCreateAnimal(
			Model m) {
		Animals animals = new Animals();
		m.addAttribute("AnimalsList1", animals);
		m.addAttribute("Families", breedsService.readAllFamilies());
		m.addAttribute("breed", breedsService.readAllBreeds("狗"));
		m.addAttribute("source", "create");
		return "adopt/CreateAnimal";
	}

	// Create
	@PostMapping("/CreateAnimal.controller")
	public String processCreateAnimal(@ModelAttribute("AnimalsList1") Animals entity,
			@RequestParam("memberId") Integer memberId, @RequestParam("breedText") String breedText, Model m)
			throws Exception {
		// 新增照片部分
		MultipartFile mFile = entity.getAnimalFiles();
		String filename = mFile.getOriginalFilename();// 取得檔名
//		System.out.println("filename"+filename);
		String fileTempDirPath = sc.getRealPath("/") + "uploadTempDir\\";// 存放的資料夾
		InputStream is = null;
		String fileSavePath = "";
		FileInputStream is1 = null;
		byte[] b1 = null;
		if (!mFile.isEmpty()) {

			File dirPath = new File(fileTempDirPath);

			// 建資料夾
			if (!dirPath.exists()) {
				boolean status = dirPath.mkdirs();
				System.out.println("status:" + status);
			}

			// 設儲存路徑
			fileSavePath = fileTempDirPath + filename;
			File saveFile = new File(fileSavePath);
			mFile.transferTo(saveFile);
			System.out.println("fileSavePath:" + fileSavePath);// 檔案路徑

			// 設定圖片格式
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);

			if (filename != null && filename.length() != 0) {
				is1 = new FileInputStream(fileSavePath);
				b1 = new byte[is1.available()];
				is1.read(b1);
				is1.close();

				Set<AnimalsFiles> files = new HashSet<AnimalsFiles>();
				Blob blob = new SerialBlob(b1);
				AnimalsFiles file = new AnimalsFiles("image", filename, blob);
				file.setAnimals(entity);
				files.add(file);
				entity.setFiles(files);
			}
		} else {
			// 新增沒圖片給預設圖片
			// 設定圖片格式
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);

			filename = "NoImage.png";
			is = sc.getResourceAsStream(// getResourceAsStream開啟一個檔案
					"/WEB-INF/resources/images/" + filename);// /images/為webapp/images/
			byte[] b = new byte[4 * 1024 * 1024];
			is.read(b);
			is.close();

			Set<AnimalsFiles> files = new HashSet<AnimalsFiles>();
			Blob blob = new SerialBlob(b);
			AnimalsFiles file = new AnimalsFiles("image", filename, blob);
			file.setAnimals(entity);
			files.add(file);
			entity.setFiles(files);
		}
		
		
//		RequestBody request = RequestBody.create(b1);
//		System.out.println("request"+request);
//		Call<ImageResponse> call =  imgurApi.postImage(request);
//		System.out.println("call"+call);
//		Response<ImageResponse> res = call.execute();
//		System.out.println("res"+res);
//		
//		System.out.println("是否成功: " + res.isSuccessful());
//		System.out.println("imgur"+res.body().data.link);
		
		

		// 新增文字部分
		entity.setCreatedAt(new Date());
		entity.setMember((Members) session.getAttribute("LoginOK"));// 這邊才有存會員編號，跟會員做關聯
		List<Breeds> readBreed = breedsService.readBreed(breedText);
		entity.setBreeds(readBreed.get(0));// 用family找到該筆bean，再set到breeds，修改也是?
		animalsService.create(entity);

		return "redirect:/MemberCenter/ReadAnimal";
	}

	// Refresh
	@GetMapping({ "/CreateAnimal.controller", "/UpdateAnimal.controller", "/DeleteAnimal.controller" })
	public String processCreateAnimal(Model m) {
		return "redirect:/MemberCenter/ReadAnimal";
	}

//==============================================================================================

	//查詢
	@GetMapping("/readKeyword.controller")
	public @ResponseBody List<Animals> processReadKeyword(@RequestParam("factor1") String factor1, @RequestParam("orderBy") String orderBy) {
		List<Animals> list = null;
		animalsService.readAnimals1("", orderBy);//先查貓狗種類，再將全部list合起來
		return list;
	}
	
//==============================================================================================

	// PreUpdate
	@GetMapping("/preUpdateAnimal.controller")
	public String processPreUpdateAnimal(@RequestParam("animalId") Integer animalsId, Model m) {
		Animals animals = animalsService.read(animalsId);
		m.addAttribute("animals", animals);
		m.addAttribute("Families", breedsService.readAllFamilies());
		m.addAttribute("breed", breedsService.readAllBreeds(animals.getBreeds().getFamily()));
		m.addAttribute("source", "update");
		return "adopt/CreateAnimal";
	}

	// Update
	@PostMapping("/UpdateAnimal.controller")
	public String processUpdateAnimal(@ModelAttribute("animals") Animals entity,
			@RequestParam("breedText") String breedText, Model m) throws Exception {
		AnimalsFiles content = new AnimalsFiles();
		// 更新照片部分
		MultipartFile mFile = entity.getAnimalFiles();
		Integer animalId = entity.getAnimalId();
		if (!mFile.isEmpty()) {// mFile.isEmpty()為判斷是否有上傳圖片
			String filename = mFile.getOriginalFilename();// 取得檔名
			String fileTempDirPath = sc.getRealPath("/") + "uploadTempDir\\";// 存放的資料夾

			File dirPath = new File(fileTempDirPath);

			// 建資料夾
			if (!dirPath.exists()) {
				boolean status = dirPath.mkdirs();
				System.out.println("status:" + status);
			}

			// 設儲存路徑
			String fileSavePath = fileTempDirPath + filename;
			File saveFile = new File(fileSavePath);
			mFile.transferTo(saveFile);
			System.out.println("fileSavePath:" + fileSavePath);// 檔案路徑

			// 設定圖片格式
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);

			if (filename != null && filename.length() != 0) {
				FileInputStream is1 = new FileInputStream(fileSavePath);
				byte[] b = new byte[is1.available()];
				is1.read(b);
				is1.close();

				Set<AnimalsFiles> AnimalsFiles = animalsService.read(animalId).getFiles();
				Blob blob = new SerialBlob(b);

				Iterator<AnimalsFiles> iterator = AnimalsFiles.iterator();
				while (iterator.hasNext()) {
					content = (AnimalsFiles) iterator.next();
					System.out.println("filename" + filename);
					content.setFileType("image");
					content.setFileName(filename);
					content.setFileBlob(blob);
//					content.setAnimals(entity);//
					entity.setFiles(AnimalsFiles);
					System.out.println("content" + content);
				}
			}
		}
		entity.setUpdatedAt(new Date());
		entity.setMember((Members) session.getAttribute("LoginOK"));
		List<Breeds> readBreed = breedsService.readBreed(breedText);
		entity.setBreeds(readBreed.get(0));// 用family找到該筆bean，再set到breeds，修改也是?
		animalsService.update(entity);
//		m.addAttribute("source", "ReadAnimal");
		System.out.println("processUpdateAnimal finish, animalId:"+animalId);

		return "redirect:/MemberCenter/ReadAnimalDetails.controller?id="+animalId;//似乎不能用{}pathvariable傳值
	}

//==============================================================================================

	// Delete
	@GetMapping("/DeleteAnimal.controller/{animalId}")
	public String processDeleteAnimal(@PathVariable(name = "animalId") Integer animalId, Model m) {
		// TODO 要addAttribute刪除失敗訊息
		// 軟刪除
//		Animals entity = animalsService.read(animalId);
//		entity.setDeletedAt(new Date());
//		entity.setMember((Members)session.getAttribute("LoginOK"));
//		System.out.println("inside DeleteAnimal.controller"+entity);
//		animalsService.update(entity);
		animalsService.delete(animalId);
		
		return "redirect:/MemberCenter/ReadAnimal";
	}
	
	static ImgurAPI createImgurAPI(){
		Retrofit retrofit = new Retrofit.Builder()
				.addConverterFactory(GsonConverterFactory.create())
			    .baseUrl(ImgurAPI.SERVER)
			    .build();
		return retrofit.create(ImgurAPI.class);
	}

	//==============================================================================================
	
	//領養申請列表
	@GetMapping("/adoptionRequestList.controller")
	public String processAdoptionRequestList(@RequestParam("source") String source, Model m) {
		Integer memberId = ((Members) session.getAttribute("LoginOK")).getId();
		if ("AdoptionRequest".equals(source)) {
			//找出該會員擁有的寵物，但被其他領養人提出領養申請的寵物
			List<AdoptionRecords> readAdoptionRecords2 = adoptionRecordsService.readAdoptionRecords2("OWNERMEMBERID = "+ memberId, "REVIEW_STATUS >= 0");
			m.addAttribute("AdoptionRequestList", readAdoptionRecords2);
			m.addAttribute("source", "AdoptionRequest");
		}else if ("MyAdoptionProgress".equals(source)) {
			//找出該會員申請領養的紀錄
			List<AdoptionRecords> readMyAdoptionRecords = adoptionRecordsService.readMyAdoptionRecords(memberId);
			m.addAttribute("AdoptionRequestList", readMyAdoptionRecords);
			m.addAttribute("source", "MyAdoptionProgress");
		}
		return "adopt/AdoptionRequestList";
	}
	
	@RequestMapping("/adoptionRequestList.controller.0")//退回申請
	public String processAdoptionRequestList0(@RequestParam("animalId") Integer animalId, @RequestParam("memberId") Integer memberId, @RequestParam("rejectedReason") String rejectedReason) {
		AdoptionRecords adoptionRecord = adoptionRecordsService.read(memberId, animalId).get(0);
		adoptionRecord.setReviewStatus(0);
		adoptionRecord.setApplyRejectedAt(new Date());
		adoptionRecord.setRejectedReason(rejectedReason);
		adoptionRecordsService.update(adoptionRecord);
		return "redirect:/MemberCenter/adoptionRequestList.controller?source=AdoptionRequest";
	}
	
	@RequestMapping("/adoptionRequestList.controller.2")//核准申請
	public String processAdoptionRequestList2(@RequestParam("animalId") Integer animalId, @RequestParam("memberId") Integer memberId, @RequestParam("approvedReason") String approvedReason) {
		AdoptionRecords adoptionRecord = adoptionRecordsService.read(memberId, animalId).get(0);
		adoptionRecord.setReviewStatus(2);
		adoptionRecord.setApplyApprovedAt(new Date());
		adoptionRecord.setApprovedReason(approvedReason);
		adoptionRecordsService.update(adoptionRecord);
		return "redirect:/MemberCenter/adoptionRequestList.controller?source=AdoptionRequest";
	}
	
	@RequestMapping("/adoptionRequestList.controller.3")//完成領養
	public String processAdoptionRequestList3(@RequestParam("animalId") Integer animalId, @RequestParam("memberId") Integer memberId, @RequestParam("adopterMessage") String adopterMessage) {
		AdoptionRecords adoptionRecord = adoptionRecordsService.read(memberId, animalId).get(0);
		adoptionRecord.setReviewStatus(3);
		adoptionRecord.setAdoptionDate(new Date());
		adoptionRecord.setAdopterMessage(adopterMessage);
		adoptionRecordsService.update(adoptionRecord);

		Members member = (Members) session.getAttribute("LoginOK");
		Animals animals = animalsService.read(adoptionRecord.getAnimal().getAnimalId());
		animals.setMember(member);
		animals.setIsAdoptionAvailable(0);
		animalsService.update(animals);
		return "redirect:/MemberCenter/adoptionRequestList.controller?source=MyAdoptionProgress";
	}
}
