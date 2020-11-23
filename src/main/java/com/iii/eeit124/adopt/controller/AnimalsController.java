package com.iii.eeit124.adopt.controller;

import java.io.ByteArrayOutputStream;
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
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.iii.eeit124.adopt.service.AnimalsService;
import com.iii.eeit124.adopt.service.BreedsService;
import com.iii.eeit124.entity.Animals;
import com.iii.eeit124.entity.AnimalsFiles;
import com.iii.eeit124.entity.Breeds;
import com.iii.eeit124.entity.Members;

@Controller
public class AnimalsController {
	@Autowired
	ServletContext sc;
	@Autowired
	public AnimalsService animalsService;
	@Autowired
	public BreedsService breedsService;
	@Autowired
	HttpSession session;
	
	@GetMapping("/none")
	public String processNone() {
		return "public/None";
	}

	// 轉至分派器
	@GetMapping("/adoptDispatcher")
	public String processAdoptDispatcher() {
		return "adopt/AdoptDispatcher";
	}

	// 讀圖
	@GetMapping("/filuploadAction.contoller/{id}")
	@ResponseBody
	public ResponseEntity<byte[]> processFileUploadAction(@PathVariable(name = "id") Integer id) throws Exception {
		ResponseEntity<byte[]> re = null;

		Animals animals = animalsService.read(id);// 依主鍵找對應檔案
		Iterator<AnimalsFiles> iterator = animals.getFiles().iterator();// 將檔案從set中撈出
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
	
	//瀏覽全部的動物
	@GetMapping("/ReadAllAnimal")
	public String processAllPetsRead(Model m) {
		m.addAttribute("AnimalsList", animalsService.readAll());
		return "adopt/ReadAnimal";
	}

	// 瀏覽會員的動物
	@GetMapping("/ReadAnimal")
	public String processMyPetsRead(Model m) {
		m.addAttribute("AnimalsList", animalsService.readMyAnimals(((Members)session.getAttribute("LoginOK")).getId()));
		return "adopt/ReadAnimal";
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
		return "adopt/CreateAnimal";
	}

	// SelectBreeds
	@GetMapping(value = "/getBreed.controller")
	public @ResponseBody List<Breeds> processGetBreed(@RequestParam("family") String family) {
		List<Breeds> breed = breedsService.readAllBreeds(family);
		return breed;
	}
	// SelectBreeds//可用
//	@GetMapping(value="/getBreed.controller")
//	public @ResponseBody List<String> processGetBreed(@RequestParam("family") String family) {
//		List<String> breed = breedsService.readAllBreeds(family);
//		System.out.println("breed123"+breed);
//		return breed;
//	}

	// Create
	@PostMapping("/CreateAnimal.controller")
	public String processCreateAnimal(@ModelAttribute("AnimalsList1") Animals entity,
			@RequestParam("memberId") Integer memberId, @RequestParam("breedText") String breedText, Model m)
			throws Exception {
		// 新增照片部分
		MultipartFile mFile = entity.getAnimalFiles();
		String filename = mFile.getOriginalFilename();// 取得檔名
		String fileTempDirPath = sc.getRealPath("/") + "uploadTempDir\\";// 存放的資料夾
		InputStream is = null;
		if (!mFile.isEmpty()) {

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

				Set<AnimalsFiles> files = new HashSet<AnimalsFiles>();
				Blob blob = new SerialBlob(b);
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

		// 新增文字部分
		entity.setCreatedAt(new Date());
		entity.setMember((Members) session.getAttribute("LoginOK"));// 這邊才有存會員編號，跟會員做關聯
		List<Breeds> readBreed = breedsService.readBreed(breedText);
		entity.setBreeds(readBreed.get(0));// 用family找到該筆bean，再set到breeds，修改也是?
		animalsService.create(entity);

		return "redirect:/ReadAnimal";
	}

	// Refresh
	@GetMapping({ "/CreateAnimal.controller", "/UpdateAnimal.controller", "/DeleteAnimal.controller" })
	public String processCreateAnimal(Model m) {
		return "redirect:/ReadAnimal";
	}

//==============================================================================================

	// PreUpdate
	@GetMapping("/preUpdateAnimal.controller")
	public String processPreUpdateAnimal(@RequestParam("animalId") Integer animalsId, Model m) {
		Animals animals = animalsService.read(animalsId);
		m.addAttribute("animals", animals);
		m.addAttribute("Families", breedsService.readAllFamilies());
		m.addAttribute("breed", breedsService.readAllBreeds(animals.getBreeds().getFamily()));
		return "adopt/UpdateAnimal";
	}

	// Update
	@PostMapping("/UpdateAnimal.controller")
	public String processUpdateAnimal(@ModelAttribute("animals") Animals entity,
			@RequestParam("breedText") String breedText, Model m) throws Exception {
		AnimalsFiles content = new AnimalsFiles();
		// 更新照片部分
		MultipartFile mFile = entity.getAnimalFiles();
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

				Integer animalId = entity.getAnimalId();
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

		return "redirect:/ReadAnimal";
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
		
		return "redirect:/ReadAnimal";
	}
}
