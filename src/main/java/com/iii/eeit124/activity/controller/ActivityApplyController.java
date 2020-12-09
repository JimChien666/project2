package com.iii.eeit124.activity.controller;

import java.util.Date;
import java.util.Locale;

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
import com.iii.eeit124.activity.service.ActivityApplyVoService;
import com.iii.eeit124.activity.vo.ActivityApplyVo;
import com.iii.eeit124.entity.ActivityApply;
import com.iii.eeit124.util.DateUtils;

@Controller
@RequestMapping("/activityApply")
public class ActivityApplyController {

	@Autowired
	ActivityApplyService activityApplyService;

	@Autowired
	ActivityApplyVoService activityApplyVoService;

	@ModelAttribute("activityApplyVo")
	public ActivityApplyVo formBackingObject() {
		return new ActivityApplyVo();
	}

	/**
	 * 報名者列表
	 * 
	 * @param locale
	 * @param model
	 * @return
	 */
	@GetMapping("list")
	public String list(Locale locale, Model model) {
		return returnToList(model);
	}

	/**
	 * 報名活動頁面
	 * 
	 * @param activityApplyVo
	 * @param result
	 * @param model
	 * @return
	 */
	@GetMapping("add")
	public String goAddPage(@ModelAttribute("activityApplyVo") ActivityApplyVo activityApplyVo, BindingResult result,
			Model model) {
		return "activityApply/add";
	}

	/**
	 * 儲存活動報名
	 * 
	 * @param activityApplyVo
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping("saveActivityApply")
	public String saveActivityApply(@ModelAttribute("activityApplyVo") ActivityApplyVo activityApplyVo,
			BindingResult result, Model model) {

		validate(activityApplyVo, result);
		if (result.hasErrors()) {
			return "activityApply/add";
		}

		activityApplyVo.setApplyDate(DateUtils.format(new Date()));
		ActivityApply entity = activityApplyVo.toEntity();
		activityApplyService.save(entity);

		return returnToList(model);
	}

	/**
	 * 前往活動報名頁面
	 * 
	 * @param id
	 * @param locale
	 * @param model
	 * @return
	 */
	@GetMapping("update/{id}")
	public String goUpdatePage(@PathVariable(value = "id") Integer id, Locale locale, Model model) {
		ActivityApply entity = activityApplyService.findById(id);
		ActivityApplyVo activityApplyVo = ActivityApplyVo.convert(entity);
		model.addAttribute("activityApplyVo", activityApplyVo);
		return "activityApply/update";
	}

	/**
	 * 更新活動報名資訊
	 * 
	 * @param activityApplyVo
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping("updateActivityApply")
	public String updateActivityApply(@ModelAttribute("activityApplyVo") ActivityApplyVo activityApplyVo,
			BindingResult result, Model model) {
		System.out.println("activityApplyVo:" + activityApplyVo);

		ActivityApply entity = null;

		if (activityApplyVo != null && activityApplyVo.getApplyId() != null) {
			entity = activityApplyService.findById(activityApplyVo.getApplyId());
		}

		if (entity == null) {
			System.out.println("查無活動");
			return "redirect:/";
		}

		ActivityApply updateActivityApply = activityApplyVo.toEntity();
		activityApplyService.update(updateActivityApply);

		return returnToList(model);
	}

	/**
	 * 前往刪除活動報名頁面
	 * 
	 * @param id
	 * @param locale
	 * @param model
	 * @return
	 */
	@GetMapping("delete/{id}")
	public String goDeletePage(@PathVariable(value = "id") Integer id, Locale locale, Model model) {
		model.addAttribute("activityApplyVo", activityApplyService.findById(id));
		return "activityApply/delete";
	}

	/**
	 * 刪除活動報名
	 * 
	 * @param activityApplyVo
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping("deleteActivityApply")
	public String deleteActivityApply(@ModelAttribute("activitysVo") ActivityApplyVo activityApplyVo,
			BindingResult result, Model model) {
		System.out.println("activityApplyVo:" + activityApplyVo);

		if (activityApplyVo != null && activityApplyVo.getApplyId() != null) {
			activityApplyService.deleteById(activityApplyVo.getApplyId());
		}

		return returnToList(model);
	}

	private String returnToList(Model model) {
		model.addAttribute("activityApplyVoList", activityApplyVoService.list());
		return "activityApply/list";
	}

	private void validate(ActivityApplyVo activityApplyVo, BindingResult result) {
		if (StringUtils.isEmpty(activityApplyVo.getActivitysId())) {
			result.rejectValue("activitysId", null, "活動ID不得為空");
		}
	}
}
