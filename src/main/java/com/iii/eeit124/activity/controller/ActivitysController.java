package com.iii.eeit124.activity.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iii.eeit124.activity.service.ActivityApplyService;
import com.iii.eeit124.activity.service.ActivitysService;
import com.iii.eeit124.activity.service.ActivitysVoService;
import com.iii.eeit124.activity.vo.ActivitysVo;
import com.iii.eeit124.entity.Activitys;
import com.iii.eeit124.entity.Members;

@Controller
@RequestMapping("/activitys")
public class ActivitysController {

	@Autowired
	HttpSession session;

	@Autowired
	ActivitysService activitysService;

	@Autowired
	ActivitysVoService activitysVoService;

	@Autowired
	ActivityApplyService activityApplyService;

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

		validate(activitysVo, result);
		if (result.hasErrors()) {
			return "activitys/add";
		}

		Activitys activitys = activitysVo.toEntity();
		activitysService.createActivitys(activitys, getMember());

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

	/**
	 * 更新活動內容
	 * 
	 * @param activitysVo
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping("updateActivitys")
	public String updateActivitys(@ModelAttribute("activitysVo") ActivitysVo activitysVo, BindingResult result,
			Model model) {

		validate(activitysVo, result);
		if (result.hasErrors()) {
			return "activitys/update";
		}

		Activitys dbActivitys = null;

		if (activitysVo != null && activitysVo.getId() != null) {
			dbActivitys = activitysService.findById(activitysVo.getId());
		}

		if (dbActivitys == null) {
			System.out.println("查無活動");
			return "redirect:/";
		}

		Activitys updateActivitys = activitysVo.toEntity();
		activitysService.update(updateActivitys);

		return returnToList(model);
	}

	/**
	 * 前往刪除活動頁面
	 * 
	 * @param id
	 * @param locale
	 * @param model
	 * @return
	 */
	@GetMapping("delete/{id}")
	public String goDeletePage(@PathVariable(value = "id") Integer id, Locale locale, Model model) {
		model.addAttribute("activitys", activitysService.findById(id));
		return "activitys/delete";
	}

	/**
	 * 刪除活動
	 * 
	 * @param activitysVo
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping("deleteActivitys")
	public String deleteActivitys(@ModelAttribute("activitysVo") @Valid ActivitysVo activitysVo, BindingResult result,
			Model model) {
		System.out.println("activitysVo:" + activitysVo);

		if (activitysVo != null && activitysVo.getId() != null) {
			activitysService.deleteById(activitysVo.getId());
			activityApplyService.deleteByActivitysId(activitysVo.getId());
		}

		return returnToList(model);
	}

	private String returnToList(Model model) {
		model.addAttribute("activitysVoList", activitysVoService.list());
		return "activitys/list";
	}

	private Members getMember() {
		Object object = session.getAttribute("LoginOK");
		if (object != null) {
			return (Members) object;
		} else {
			return new Members();
		}
//		return (Members) session.getAttribute("LoginOK");
	}

	private void validate(ActivitysVo activitysVo, BindingResult result) {
		if (StringUtils.isEmpty(activitysVo.getName())) {
			result.rejectValue("name", null, "活動名稱不得為空");
		}

		if (StringUtils.isEmpty(activitysVo.getActivityDate())) {
			result.rejectValue("activityDate", null, "活動時間不得為空");
		}

		if (StringUtils.isEmpty(activitysVo.getTopic())) {
			result.rejectValue("topic", null, "活動主題不得為空");
		}

		if (StringUtils.isEmpty(activitysVo.getContent())) {
			result.rejectValue("content", null, "活動內容不得為空");
		}

		if (StringUtils.isEmpty(activitysVo.getLocation())) {
			result.rejectValue("location", null, "活動位置不得為空");
		}
	}

}
