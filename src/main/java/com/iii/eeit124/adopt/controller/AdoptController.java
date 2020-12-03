package com.iii.eeit124.adopt.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.iii.eeit124.adopt.service.AdoptionRecordsService;
import com.iii.eeit124.adopt.service.AnimalsService;
import com.iii.eeit124.entity.AdoptionRecords;
import com.iii.eeit124.entity.Animals;
import com.iii.eeit124.entity.Members;

@Controller
@RequestMapping("/adopt")
public class AdoptController {

	@Autowired
	HttpSession session;
	@Autowired
	public AnimalsService animalsService;
	@Autowired
	public AdoptionRecordsService adoptionRecordsService;

	@GetMapping("/adoptNotice/{animalId}")
	public String processAdoptNotice(@PathVariable("animalId") Integer animalsId,
//			@RequestParam("animalId") Integer animalsId,
			Model m) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		m.addAttribute("Today", sdf.format(new Date()));
		m.addAttribute("animal", animalsService.read(animalsId));
		m.addAttribute("adoptionRecord", new AdoptionRecords());
		m.addAttribute("member", (Members) session.getAttribute("LoginOK"));
		return "adopt/AdoptNotice";
	}

	@PostMapping("/adoptApply")
	public String processAdoptApply(@ModelAttribute("adoptionRecord") AdoptionRecords adoptionRecords, Model m,
			@RequestParam("notice1") Integer notice1, @RequestParam("notice2") Integer notice2,
			@RequestParam("notice3") Integer notice3, @RequestParam("notice4") Integer notice4,
			@RequestParam("notice5") Integer notice5, @RequestParam("notice6") Integer notice6,
			@RequestParam("notice7") Integer notice7, @RequestParam("notice8") Integer notice8,
			@RequestParam("notice9") Integer notice9, @RequestParam("notice10") Integer notice10) {
		int[] notice = { notice1, notice2, notice3, notice4, notice5, notice6, notice7, notice8, notice9, notice10 };
		Integer sum = 0;
		for (int i = 0; i < notice.length; i++) {
			sum = (int) (sum + notice[i] * Math.pow(2, i));
		}

		Members member = (Members) session.getAttribute("LoginOK");
		adoptionRecords.setMember(member);
		Animals animal = animalsService.read(adoptionRecords.getAnimalId());
		adoptionRecords.setAnimal(animalsService.read(adoptionRecords.getAnimalId()));
		adoptionRecords.setNoticeOptions(sum);

		System.out.println("adoptionRecords" + adoptionRecords);
		if (adoptionRecords.getMember().getId() > 0) {
			adoptionRecords.setCreatedAt(new Date());
			adoptionRecordsService.create(adoptionRecords);
			System.out.println("inside adoptionRecords.getMemberId()>0");
		}

		m.addAttribute("adoptionRecord", adoptionRecordsService.read(member.getId(), animal.getAnimalId()));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		m.addAttribute("Today", sdf.format(new Date()));
//		System.out.println(notice1*2^0+notice2*2^1);
		return "adopt/AdoptApply";
	}

	@GetMapping("/apply")
	public String processApply(@RequestParam("animalId") Integer animalId, Model m) {
		Members member = (Members) session.getAttribute("LoginOK");
		Animals animals = animalsService.read(animalId);
		animals.setMember(member);
		animals.setIsAdoptionAvailable(0);
		animalsService.update(animals);
//		adoptionRecords.setAdoptionStatus(1);
		m.addAttribute("AnimalsList", animalsService.readAll());
		m.addAttribute("source", "AdoptAnimal");
		return "adopt/AdoptAnimal";
	}
}
