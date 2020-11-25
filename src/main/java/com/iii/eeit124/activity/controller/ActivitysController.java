package com.iii.eeit124.activity.controller;

import java.util.Date;
import java.util.Locale;

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
import com.iii.eeit124.entity.Activitys;


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
import com.iii.eeit124.entity.Activitys;

@Controller
public class ActivitysController {

    @Autowired
    ActivitysService activitysService;

    @ModelAttribute("activitys")
    public Activitys formBackingObject() {
        return new Activitys();
    }

//    @GetMapping("/")
//    public String list(Locale locale, Model model) {
//        model.addAttribute("activitysList", activitysService.list());
//        return "activitys/list";
//    }
    
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
    public String saveActivitys(@ModelAttribute("activitys") @Valid Activitys entity, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("activitysList", activitysService.list());
            return "activitys/list";
        }

        entity.setCreateDate(new Date());

        activitysService.save(entity);
        return "redirect:/";
    }

    @PostMapping("updateActivitys")
    public String updateActivitys(@ModelAttribute("activitys") @Valid Activitys entity, BindingResult result, Model model) {
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
    public String deleteActivitys(@ModelAttribute("activitys") @Valid Activitys entity, BindingResult result, Model model) {
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

