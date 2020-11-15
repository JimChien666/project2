package com.iii.eeit124.adopt.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.iii.eeit124.adopt.service.AnimalsService;
import com.iii.eeit124.entity.Animals;
import com.iii.eeit124.entity.AnimalsFiles;

@Controller
public class AnimalsController {
	@Autowired
	ServletContext sc;
	@Autowired
	public AnimalsService animalsService;
	@Autowired
	HttpSession session;

	// 瀏覽全部動物
	@GetMapping("/readAnimal")
	public String processReadAnimal(Model m) {
		m.addAttribute("AnimalsList", animalsService.readAll());
		return "adopt/example/ReadAnimal";
	}

	// PreCreate
	@GetMapping("/preCreateAnimal.controller")
	public String processPreCreateAnimal(Model m) {
		System.out.println();//TODO
		Animals animals = new Animals();
		m.addAttribute("AnimalsList1", animals);
		return "adopt/example/CreateAnimal";
	}

	// Create
	@PostMapping("/CreateAnimal.controller")
	public String processCreateAnimal(@ModelAttribute("AnimalsList1") Animals entity, Model m) throws Exception {
		// 新增照片部分
		MultipartFile mFile = entity.getAnimalFiles();
		String filename = mFile.getOriginalFilename();// 取得檔名
		String fileTempDirPath = sc.getRealPath("/") + "uploadTempDir\\";// 存放的資料夾
		InputStream is = null;
		if (!mFile.isEmpty()) {
		
			File dirPath = new File(fileTempDirPath);
		
			//建資料夾
			if (!dirPath.exists()) {
				boolean status = dirPath.mkdirs();
				System.out.println("status:" + status);
			}

			//設儲存路徑
			String fileSavePath = fileTempDirPath + filename;
			File saveFile = new File(fileSavePath);
			mFile.transferTo(saveFile);
			System.out.println("fileSavePath:" + fileSavePath);// 檔案路徑

			//設定圖片格式
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
		}else {
			//新增沒圖片給預設圖片
			//設定圖片格式
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);
			
			filename = "NoImage.png" ; 
			is = sc.getResourceAsStream(//getResourceAsStream開啟一個檔案
					"/WEB-INF/resources/images/" + filename);// /images/為webapp/images/
			byte[] b = new byte[4*1024*1024];
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
		animalsService.create(entity);

		m.addAttribute("AnimalsList", animalsService.readAll());
//		return "adopt/example/ReadAnimal";
		return "adopt/MyPetsRead";
	}

	// Refresh
	@GetMapping({ "/CreateAnimal.controller", "/UpdateAnimal.controller", "/DeleteAnimal.controller" })
	public String processCreateAnimal(Model m) {
		m.addAttribute("AnimalsList", animalsService.readAll());
		return "adopt/example/ReadAnimal";
	}

	// PreUpdate
	@GetMapping("/preUpdateAnimal.controller")
	public String processPreUpdateAnimal(@RequestParam("animalId") Integer animalId, Model m) {
		Animals animals = animalsService.read(animalId);
		m.addAttribute("animals", animals);
		return "adopt/example/UpdateAnimal";
	}

	// Update
	@PostMapping("/UpdateAnimal.controller")
	public String processUpdateAnimal(@ModelAttribute("animals") Animals entity, Model m) throws Exception {
		AnimalsFiles content = new AnimalsFiles();
		// 更新照片部分
		MultipartFile mFile = entity.getAnimalFiles();
		if (!mFile.isEmpty()) {//mFile.isEmpty()為判斷是否有上傳圖片
			String filename = mFile.getOriginalFilename();// 取得檔名
			String fileTempDirPath = sc.getRealPath("/") + "uploadTempDir\\";// 存放的資料夾
		
			File dirPath = new File(fileTempDirPath);
		
			//建資料夾
			if (!dirPath.exists()) {
				boolean status = dirPath.mkdirs();
				System.out.println("status:" + status);
			}
	
			//設儲存路徑
			String fileSavePath = fileTempDirPath + filename;
			File saveFile = new File(fileSavePath);
			mFile.transferTo(saveFile);
			System.out.println("fileSavePath:" + fileSavePath);// 檔案路徑
	
			//設定圖片格式
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
					System.out.println("entity: "+entity.printAll());
					System.out.println("content"+content);
				}
			}
		}
		entity.setUpdatedAt(new Date());
		animalsService.update(entity);
			
		m.addAttribute("AnimalsList", animalsService.readAll());
		return "adopt/example/ReadAnimal";
	}

	// Delete
	@PostMapping("/DeleteAnimal.controller")
	public String processDeleteAnimal(@RequestParam("animalId") Integer animalId, Model m) {
		// TODO 要addAttribute刪除失敗訊息
		animalsService.delete(animalId);
		m.addAttribute("AnimalsList", animalsService.readAll());
		return "adopt/example/ReadAnimal";
	}
}
