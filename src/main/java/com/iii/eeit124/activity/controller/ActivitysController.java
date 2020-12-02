package com.iii.eeit124.activity.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.iii.eeit124.activity.service.ActivitysService;
import com.iii.eeit124.activity.vo.ActivitysVo;
import com.iii.eeit124.entity.Activitys;

@Controller
public class ActivitysController {

	@Autowired
	ActivitysService activitysService;

	@ModelAttribute("activitys")
	public Activitys formBackingObject() {
		return new Activitys();
	}

	/**
	 * 活動總覽
	 * 
	 * @param locale
	 * @param model
	 * @return
	 */
	@GetMapping("/")
	public String list(Locale locale, Model model) {
		List<Activitys> list = activitysService.list();
		
		List<ActivitysVo> result = new ArrayList<>();
		if (!CollectionUtils.isEmpty(list)) {
			result = list.stream().map(ActivitysVo::convert).collect(Collectors.toList());
		}
		
		model.addAttribute("activitysVoList", result);
		return "activitys/list";
	}

	@GetMapping("update/activitys/{id}")
	public String getUpdatePage(@PathVariable(value = "id") Integer id, Locale locale, Model model) {
		model.addAttribute("activitys", activitysService.findById(id));
		return "activitys/update";
	}

	@GetMapping("delete/activitys/{id}")
	public String getDeletePage(@PathVariable(value = "id") Integer id, Locale locale, Model model) {
		model.addAttribute("activitys", activitysService.findById(id));
		return "activitys/delete";
	}

	@PostMapping("addActivitys")
	public String saveActivitys(@ModelAttribute("activitys") @Valid Activitys entity, BindingResult result,
			Model model) {

		if (result.hasErrors()) {
			model.addAttribute("activitysList", activitysService.list());
			return "activitys/list";
		}

		entity.setCreateDate(new Date());

		activitysService.save(entity);
		return "redirect:/";
	}

	@PostMapping("updateActivitys")
	public String updateActivitys(@ModelAttribute("activitys") @Valid Activitys entity, BindingResult result,
			Model model) {
		System.out.println("entity:" + entity);

		if (result.hasErrors()) {
			model.addAttribute("activitysList", activitysService.list());
			return "redirect:/";
		}

		Activitys dbActivitys = null;

		if (entity != null && entity.getId() != null) {
			dbActivitys = activitysService.findById(entity.getId());
		} else {
			System.out.println("沒帶活動ID");
			return "redirect:/";
		}

		if (dbActivitys == null) {
			System.out.println("查無活動");
			return "redirect:/";
		}

		activitysService.update(entity);

		model.addAttribute("activitys", entity);
		return "activitys/update";
	}

	@PostMapping("deleteActivitys")
	public String deleteActivitys(@ModelAttribute("activitys") @Valid Activitys entity, BindingResult result,
			Model model) {
		System.out.println("entity:" + entity);

		if (result.hasErrors()) {
			model.addAttribute("activitysList", activitysService.list());
			return "redirect:/";
		}

		if (entity != null && entity.getId() != null) {
			activitysService.delete(entity);
		}

		return "redirect:/";
	}

}
