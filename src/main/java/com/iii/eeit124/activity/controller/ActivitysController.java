package com.iii.eeit124.activity.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iii.eeit124.activity.service.ActivitysService;
import com.iii.eeit124.activity.service.ActivitysVoService;
import com.iii.eeit124.activity.vo.ActivitysVo;
import com.iii.eeit124.entity.Activitys;

@Controller
@RequestMapping("/activitys")
public class ActivitysController {

	@Autowired
	ActivitysService activitysService;

	@Autowired
	ActivitysVoService activitysVoService;

	@ModelAttribute("activitysVo")
	public ActivitysVo formBackingObject() {
		return new ActivitysVo();
	}

	/**
	 * 活動總覽
	 * 
	 * @param locale
	 * @param model
	 * @return
	 */
	@GetMapping("list")
	public String list(Locale locale, Model model) {
		model.addAttribute("activitysVoList", activitysVoService.list());
		return "activitys/list";
	}

	/**
	 * 前往新增頁面
	 * 
	 * @param activitysVo
	 * @param result
	 * @param model
	 * @return
	 */
	@GetMapping("add")
	public String goAddPage(@ModelAttribute("activitysVo") ActivitysVo activitysVo, BindingResult result, Model model) {
		return "activitys/add";
	}

	/**
	 * 新增活動
	 * 
	 * @param entity
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping("saveActivitys")
	public String saveActivitys(@ModelAttribute("activitysVo") ActivitysVo activitysVo, BindingResult result,
			Model model) {

		activitysVo.validate();
		Activitys activitys = activitysVo.toEntity();
		activitysService.save(activitys);

		return returnToList(model);
	}

	/**
	 * 前往更新活動頁面
	 * 
	 * @param id
	 * @param locale
	 * @param model
	 * @return
	 */
	@GetMapping("update/{id}")
	public String goUpdatePage(@PathVariable(value = "id") Integer id, Locale locale, Model model) {
		Activitys entity = activitysService.findById(id);
		ActivitysVo activityVo = ActivitysVo.convert(entity);
		model.addAttribute("activitysVo", activityVo);
		return "activitys/update";
	}

	@PostMapping("updateActivitys")
	public String updateActivitys(@ModelAttribute("activitysVo") ActivitysVo activitysVo, BindingResult result,
			Model model) {
		System.out.println("activitysVo:" + activitysVo);

		Activitys dbActivitys = null;

		if (activitysVo != null && activitysVo.getId() != null) {
			dbActivitys = activitysService.findById(activitysVo.getId());
		} else {
			System.out.println("沒帶活動ID");
			return "redirect:/";
		}

		if (dbActivitys == null) {
			System.out.println("查無活動");
			return "redirect:/";
		}

		Activitys updateActivitys = activitysVo.toEntity();
		activitysService.update(updateActivitys);

		return returnToList(model);
	}

	@GetMapping("delete/activitys/{id}")
	public String getDeletePage(@PathVariable(value = "id") Integer id, Locale locale, Model model) {
		model.addAttribute("activitys", activitysService.findById(id));
		return "activitys/delete";
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

	private String returnToList(Model model) {
		model.addAttribute("activitysVoList", activitysVoService.list());
		return "activitys/list";
	}

}
