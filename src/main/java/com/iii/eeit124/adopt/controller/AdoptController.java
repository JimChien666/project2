package com.iii.eeit124.adopt.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.iii.eeit124.adopt.service.AdoptionRecordsService;
import com.iii.eeit124.adopt.service.AnimalsService;
import com.iii.eeit124.entity.AdoptionRecords;
import com.iii.eeit124.entity.Animals;
import com.iii.eeit124.entity.Members;

@Controller
public class AdoptController {

	@Autowired
	HttpSession session;
	@Autowired
	public AnimalsService animalsService;
	@Autowired
	public AdoptionRecordsService adoptionRecordsService;

	@GetMapping("/adopt")
	public String processAdopt(Model m) {
		m.addAttribute("AnimalsList", animalsService.readAll(((Members)session.getAttribute("LoginOK")).getId()));
		m.addAttribute("source", "AdoptAnimal");
		return "adopt/AdoptAnimal";
	}
	
	//瀏覽動物詳細頁
	@GetMapping("/AdoptAnimalDetails.controller")
	public String processAdoptAnimalDetail(@RequestParam(name = "id") Integer id, 
//			@ModelAttribute("animal") Animals entity, 
			Model m) {
		Animals animals = animalsService.read(id);
		m.addAttribute("source", "AdoptAnimal");
		m.addAttribute("animal", animals);
		return "adopt/ReadAnimalDetails";
	}
	
	@GetMapping("/adoptNotice")
	public String processAdoptNotice(
			@RequestParam("animalId") Integer animalsId,
			Model m) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		m.addAttribute("Today", sdf.format(new Date()));
		m.addAttribute("animal", animalsService.read(animalsId));
		m.addAttribute("adoptionRecord", new AdoptionRecords());
		m.addAttribute("member", (Members)session.getAttribute("LoginOK"));
		return "adopt/AdoptNotice";
	}
	
	@PostMapping("/adoptApply")
	public String processAdoptApply(
			@ModelAttribute("adoptionRecord") AdoptionRecords adoptionRecords,
			Model m
			, @RequestParam("notice1") Integer notice1
			, @RequestParam("notice2") Integer notice2
			, @RequestParam("notice3") Integer notice3
			, @RequestParam("notice4") Integer notice4
			, @RequestParam("notice5") Integer notice5
			, @RequestParam("notice6") Integer notice6
			, @RequestParam("notice7") Integer notice7
			, @RequestParam("notice8") Integer notice8
			, @RequestParam("notice9") Integer notice9
			, @RequestParam("notice10") Integer notice10
			) {
		int[] notice = {notice1, notice2, notice3, notice4, notice5, notice6, notice7, notice8, notice9, notice10};
		Integer sum = 0;
		for (int i = 0; i < notice.length; i++) {
			sum = (int) (sum + notice[i] * Math.pow(2, i));
		}
		
		Members member = (Members)session.getAttribute("LoginOK");
		adoptionRecords.setMember(member);
		Animals animals = animalsService.read(adoptionRecords.getAnimalId());
		animals.setIsAdoptionAvailable(0);
		animals.setMember(member);
		animalsService.update(animals);
		adoptionRecords.setAnimal(animalsService.read(adoptionRecords.getAnimalId()));
		adoptionRecords.setNoticeOptions(sum);
		adoptionRecords.setAdoptionStatus(1);
		
		System.out.println("adoptionRecords"+adoptionRecords);
		if (adoptionRecords.getMember().getId()>0) {
			System.out.println("inside adoptionRecords.getMemberId()>0");
			adoptionRecordsService.create(adoptionRecords);
		}
		
		m.addAttribute("animal", animals);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		m.addAttribute("Today", sdf.format(new Date()));
//		System.out.println(notice1*2^0+notice2*2^1);
		return "adopt/AdoptApply";
	}
}
