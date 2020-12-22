package com.iii.eeit124.adopt.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder.Case;
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
import org.springframework.web.multipart.MultipartFile;

import com.iii.eeit124.adopt.others.ImgurAPI;
import com.iii.eeit124.adopt.service.AdoptionRecordsService;
import com.iii.eeit124.adopt.service.AnimalsService;
import com.iii.eeit124.adopt.service.BreedsService;
import com.iii.eeit124.adopt.service.EmailService;
import com.iii.eeit124.entity.AdoptionRecords;
import com.iii.eeit124.entity.Animals;
import com.iii.eeit124.entity.AnimalsFiles;
import com.iii.eeit124.entity.Breeds;
import com.iii.eeit124.entity.Members;
//import okhttp3.MediaType;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//會員中心的controller
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
	public EmailService emailService;
	@Autowired
	public AdoptionRecordsService adoptionRecordsService;
	static final ImgurAPI imgurApi = createImgurAPI();

	// 瀏覽全部的動物
//	@GetMapping("/ReadAllAnimal")
//	public String processAllPetsRead(Model m) {
//		m.addAttribute("AnimalsList", animalsService.readAll(((Members)session.getAttribute("LoginOK")).getId()));
//		return "adopt/ReadAnimal";
//	}

	// 瀏覽會員的動物
	@GetMapping("/ReadAnimal")
	public String processMyPetsRead(Model m) {
		m.addAttribute("AnimalsList",
				animalsService.readMyAnimals(((Members) session.getAttribute("LoginOK")).getId()));
		m.addAttribute("source", "MyAnimals");
		return "adopt/ReadAnimal";
	}

	// 瀏覽動物詳細頁
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
	public String processPreCreateAnimal(Model m) {
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
		System.out.println("processUpdateAnimal finish, animalId:" + animalId);

		return "redirect:/MemberCenter/ReadAnimalDetails.controller?id=" + animalId;// 似乎不能用{}pathvariable傳值
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

	static ImgurAPI createImgurAPI() {
		Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
				.baseUrl(ImgurAPI.SERVER).build();
		return retrofit.create(ImgurAPI.class);
	}

	// ==============================================================================================

	// 領養申請列表
	@GetMapping("/adoptionRequestList.controller")
	public String processAdoptionRequestList(@RequestParam("source") String source, Model m) {
		Integer memberId = ((Members) session.getAttribute("LoginOK")).getId();
//		Integer reviewStatus = 1;//若某隻動物還沒被核准過，則為1
//		Integer animalId = 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		if ("AdoptionRequest".equals(source)) {
			//排序
			List<AdoptionRecords> readAdoptionRecords2 = adoptionRecordsService
					.readAdoptionRecords2("OWNERMEMBERID = " + memberId, "REVIEW_STATUS >= 0", "APPLY_TIME");
			for (AdoptionRecords adoptionRecords : readAdoptionRecords2) {
				switch (adoptionRecords.getReviewStatus()) {
				case 0:
					adoptionRecords.setAdoptionRequestOrder(8);
					break;
				case 1:
					adoptionRecords.setAdoptionRequestOrder(1);
					break;
				case 2:
					adoptionRecords.setAdoptionRequestOrder(2);
					break;
				case 3:
					adoptionRecords.setAdoptionRequestOrder(4);
					break;
				case 4:
					adoptionRecords.setAdoptionRequestOrder(5);
					break;
				case 41:
					adoptionRecords.setAdoptionRequestOrder(6);
					break;
				case 5:
					adoptionRecords.setAdoptionRequestOrder(3);
					break;
				case 6:
					adoptionRecords.setAdoptionRequestOrder(7);
					break;
				default:
					break;
				}
			}

			// 領養請求
			// 找出該會員擁有的寵物，但被其他領養人提出領養申請的寵物
			List<AdoptionRecords> readAdoptionRecords22 = adoptionRecordsService
					.readAdoptionRecords2("OWNERMEMBERID = " + memberId, "REVIEW_STATUS >= 0", "Adoption_Request_Order, Animal_Id desc");
			for (AdoptionRecords adoptionRecords : readAdoptionRecords22) {
				// 狀態為送養者已核准，等待領養者確認或放棄
				int now = (int) (new Date().getTime() / 1000);
				if (adoptionRecords.getReviewStatus() == 2) {
					int applyApprovedAt = (int) (adoptionRecords.getApplyApprovedAt().getTime() / 1000);
					// 領養者超過一天未確認領養的處置
					if ((now - applyApprovedAt) > 86400) {
						adoptionRecords.setReviewStatus(6);
						adoptionRecordsService.update(adoptionRecords);
					}
				}
				if (adoptionRecords.getReviewStatus() == 6) {
					int applyApprovedAt = (int) (adoptionRecords.getApplyApprovedAt().getTime() / 1000);
					m.addAttribute("confirmedTimeOutAt", new Date(((long) (applyApprovedAt + 86400)) * 1000L));
				}

				// 判斷同時多人申請時，其他同寵物申請人的顯示按鈕
				int status = 0;
				//該寵物在眾多領養紀錄中，最高的狀態
				for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
					if (entry.getKey() == adoptionRecords.getAnimal().getAnimalId()) {
						status = entry.getValue();
					}
				}
				if ((adoptionRecords.getReviewStatus() == 3 || adoptionRecords.getReviewStatus() == 5) && (status == 0 || status == 1 || status == 2)) {
					map.put(adoptionRecords.getAnimal().getAnimalId(), 3);
					continue;
				} else if (adoptionRecords.getReviewStatus() == 2 && (status == 0 || status == 1)) {
					map.put(adoptionRecords.getAnimal().getAnimalId(), 2);
					continue;
				} else if ((adoptionRecords.getReviewStatus() == 1 || adoptionRecords.getReviewStatus() == 0
						|| adoptionRecords.getReviewStatus() == 4 || adoptionRecords.getReviewStatus() == 6) && (status == 0)) {
					map.put(adoptionRecords.getAnimal().getAnimalId(), 1);
				}
			}
			m.addAttribute("AdoptionRequestList", readAdoptionRecords22);
			m.addAttribute("source", "AdoptionRequest");
			m.addAttribute("reviewStatusMap", map);
		} else if ("MyAdoptionProgress".equals(source)) {
			//排序
			List<AdoptionRecords> readMyAdoptionRecords = adoptionRecordsService.readMyAdoptionRecords(memberId);
			for (AdoptionRecords adoptionRecords : readMyAdoptionRecords) {
				switch (adoptionRecords.getReviewStatus()) {
				case 0:
					adoptionRecords.setMyAdoptionProgressOrder(5);
					break;
				case 1:
					adoptionRecords.setMyAdoptionProgressOrder(3);
					break;
				case 2:
					adoptionRecords.setMyAdoptionProgressOrder(1);
					break;
				case 3:
					adoptionRecords.setMyAdoptionProgressOrder(4);
					break;
				case 4:
					adoptionRecords.setMyAdoptionProgressOrder(7);
					break;
				case 41:
					adoptionRecords.setMyAdoptionProgressOrder(8);
					break;
				case 5:
					adoptionRecords.setMyAdoptionProgressOrder(2);
					break;
				case 6:
					adoptionRecords.setMyAdoptionProgressOrder(6);
					break;
				default:
					break;
				}
				adoptionRecordsService.update(adoptionRecords);
			}
			
			// 我的領養進度
			// 找出該會員申請領養的紀錄
			List<AdoptionRecords> readMyAdoptionRecordsOrder = adoptionRecordsService.readMyAdoptionRecords(memberId);
			for (AdoptionRecords adoptionRecords : readMyAdoptionRecordsOrder) {
				
				// 狀態為送養者已核准，等待領養者確認或放棄
				int now = (int) (new Date().getTime() / 1000);
				if (adoptionRecords.getReviewStatus() == 2) {
					int applyApprovedAt = (int) (adoptionRecords.getApplyApprovedAt().getTime() / 1000);
					if ((now - applyApprovedAt) > 86400) {
						adoptionRecords.setReviewStatus(6);
						adoptionRecordsService.update(adoptionRecords);
					}
				}
				if (adoptionRecords.getReviewStatus() == 6) {
					int applyApprovedAt = (int) (adoptionRecords.getApplyApprovedAt().getTime() / 1000);
					m.addAttribute("confirmedTimeOutAt", new Date(((long) (applyApprovedAt + 86400)) * 1000L));
				}
			}
			m.addAttribute("AdoptionRequestList", readMyAdoptionRecordsOrder);
			m.addAttribute("source", "MyAdoptionProgress");
		}
		return "adopt/AdoptionRequestList";
	}

	// 0送養者退回申請
	// 1申請者提出申請
	// 2送養者核准申請
	// 4申請者放棄領養
	// 41申請者取消申請
	// 5申請者確認領養
	// 3申請者完成領養
	// 6領養者逾期確認
	@RequestMapping("/adoptionRequestList.controller.0") // 退回申請
	public String processAdoptionRequestList0(@RequestParam("animalId") Integer animalId,
			@RequestParam("memberId") Integer memberId, @RequestParam("rejectedReason") String rejectedReason) {
		AdoptionRecords adoptionRecord = adoptionRecordsService.read(memberId, animalId).get(0);
		adoptionRecord.setReviewStatus(0);
		adoptionRecord.setApplyRejectedAt(new Date());
		adoptionRecord.setRejectedReason(rejectedReason);
		adoptionRecordsService.update(adoptionRecord);

		// 信件內容
		String content = null;
		content = "很抱歉您未被送養者選中為寵物的新領養者，可透過下列網址查看申請退回原因。\r\n"
				+ "我的領養進度連結：http://localhost:8080/team6/MemberCenter/adoptionRequestList.controller?source=MyAdoptionProgress\r\n"
				+ "繼續領養：http://localhost:8080/team6/adopt";

		emailService.sendSimpleMessage(adoptionRecord.getEmail(), "寵物領養申請_退回申請", content);
		return "redirect:/MemberCenter/adoptionRequestList.controller?source=AdoptionRequest";
	}

	@RequestMapping("/adoptionRequestList.controller.2") // 核准申請
	public String processAdoptionRequestList2(@RequestParam("animalId") Integer animalId,
			@RequestParam("memberId") Integer memberId, @RequestParam("approvedReason") String approvedReason) {
		AdoptionRecords adoptionRecord = adoptionRecordsService.read(memberId, animalId).get(0);
		adoptionRecord.setReviewStatus(2);
		adoptionRecord.setApplyApprovedAt(new Date());
		adoptionRecord.setApprovedReason(approvedReason);
		adoptionRecordsService.update(adoptionRecord);

		// 信件內容
		String content = null;
		content = "您的領養申請已被送養者核准，請於送養者指定的時間或地點領取寵物，或另外跟送養者約時間領取，謝謝您的愛心。\r\n"
				+ "並於領取寵物後，至「我的領養進度」連結點選「完成領養」，以完成領養手續。\r\n"
				+ "我的領養進度連結：http://localhost:8080/team6/MemberCenter/adoptionRequestList.controller?source=MyAdoptionProgress\r\n"
				+ "繼續領養：http://localhost:8080/team6/adopt";

		emailService.sendSimpleMessage(adoptionRecord.getEmail(), "寵物領養申請_成功申請", content);
		return "redirect:/MemberCenter/adoptionRequestList.controller?source=AdoptionRequest";
	}

	@RequestMapping("/adoptionRequestList.controller.4") // 放棄領養
	public String processAdoptionRequestList4(@RequestParam("animalId") Integer animalId,
			@RequestParam("memberId") Integer memberId) {
		AdoptionRecords adoptionRecord = adoptionRecordsService.read(memberId, animalId).get(0);
		adoptionRecord.setReviewStatus(4);
		adoptionRecord.setAbandonedAdoptionAt(new Date());
		adoptionRecordsService.update(adoptionRecord);

		// 信件內容
		String content = null;
		content = "因會員放棄領養申請，請按下下列連結回網站，或至網站->會員中心->寵物管理->領養申請，再次核准/退回申請，謝謝。\r\n"
				+ "領養申請審核連結：http://localhost:8080/team6/MemberCenter/adoptionRequestList.controller?source=AdoptionRequest";

		// 寄mail給owner
		emailService.sendSimpleMessage(adoptionRecord.getOwnerMember().getEmail(), "寵物領養申請_需再次核准", content);
		return "redirect:/MemberCenter/adoptionRequestList.controller?source=MyAdoptionProgress";
	}

	@RequestMapping("/adoptionRequestList.controller.41") // 取消申請
	public String processAdoptionRequestList41(@RequestParam("animalId") Integer animalId,
			@RequestParam("memberId") Integer memberId) {
		AdoptionRecords adoptionRecord = adoptionRecordsService.read(memberId, animalId).get(0);
		adoptionRecord.setReviewStatus(41);
		adoptionRecord.setCancelApplyAt(new Date());
		adoptionRecordsService.update(adoptionRecord);
		return "redirect:/MemberCenter/adoptionRequestList.controller?source=MyAdoptionProgress";
	}

	@RequestMapping("/adoptionRequestList.controller.5") // 確認領養
	public String processAdoptionRequestList5(@RequestParam("animalId") Integer animalId,
			@RequestParam("memberId") Integer memberId,
			@RequestParam("confirmedAdoptionMessage") String confirmedAdoptionMessage) {
		AdoptionRecords adoptionRecord = adoptionRecordsService.read(memberId, animalId).get(0);
		adoptionRecord.setReviewStatus(5);
		adoptionRecord.setConfirmedAdoptionAt(new Date());
		adoptionRecord.setConfirmedAdoptionMessage(confirmedAdoptionMessage);
		adoptionRecordsService.update(adoptionRecord);
		return "redirect:/MemberCenter/adoptionRequestList.controller?source=MyAdoptionProgress";
	}

	@RequestMapping("/adoptionRequestList.controller.3") // 完成領養
	public String processAdoptionRequestList3(@RequestParam("animalId") Integer animalId,
			@RequestParam("memberId") Integer memberId, @RequestParam("adopterMessage") String adopterMessage) {
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

	// 讓送養者核准日期倒回一天
	@RequestMapping("/setConfirmedTimeOut.controller")
	public String processSetConfirmedTimeOut(@RequestParam("source") String source,
			@RequestParam("adoptionId") Integer adoptionId, Model m) {
		List<AdoptionRecords> readAdoptionRecords1 = adoptionRecordsService
				.readAdoptionRecords1("adoption_Id = " + adoptionId);
		int applyApprovedAtOrigin = (int) (readAdoptionRecords1.get(0).getApplyApprovedAt().getTime() / 1000);
		readAdoptionRecords1.get(0).setApplyApprovedAt(new Date(((long) (applyApprovedAtOrigin - 86400)) * 1000L));
		return "redirect:/MemberCenter/adoptionRequestList.controller?source=" + source;
	}
	
	//寵物領養分析
	@RequestMapping("/animalsAdoptionAnalysis.controller")
	public String processAnimalsAdoptionAnalysis(Model m) {
		Integer memberId = ((Members) session.getAttribute("LoginOK")).getId();
		
		//動物總數
		List<Animals> readMyAnimals = animalsService.readMyAnimals(memberId);
		Integer myAnimalsNum = 0;
		for (Animals animals : readMyAnimals) {
			myAnimalsNum = myAnimalsNum+1;
		}
		m.addAttribute("myAnimalsNum", myAnimalsNum);
		m.addAttribute("readVarietyAnimalsNums", animalsService.readVarietyAnimalsNums(memberId));
		
		//領養申請總數
		List<AdoptionRecords> readMyAdoptionRecords = adoptionRecordsService.readAdoptionRecords1(" ownerMemberId = "+memberId);
		Integer adoptionApplyNum = 0;
		for (AdoptionRecords adoptionRecords : readMyAdoptionRecords) {
			adoptionApplyNum = adoptionApplyNum+1;
		}
		m.addAttribute("adoptionApplyNum", adoptionApplyNum);
		m.addAttribute("readVarietyAdoptionAppliesNums", adoptionRecordsService.readVarietyAdoptionAppliesNums(memberId));

		//領養成功總數
		List<AdoptionRecords> readMyAdoptionRecords1 = adoptionRecordsService.readAdoptionRecords1(" ownerMemberId = "+memberId+" and Review_Status = 3");
		Integer adoptionSussessedNum = 0;
		for (AdoptionRecords adoptionRecords : readMyAdoptionRecords1) {
			adoptionSussessedNum = adoptionSussessedNum+1;
		}
		m.addAttribute("adoptionSussessedNum", adoptionSussessedNum);
		m.addAttribute("readVarietyAdoptionSuccessedAppliesNums", adoptionRecordsService.readVarietyAdoptionSuccessedAppliesNums(memberId));
		
		return "adopt/AnimalsStatisticAnalysis";
	}
}
