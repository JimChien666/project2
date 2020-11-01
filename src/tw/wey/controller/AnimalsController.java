package tw.wey.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tw.wey.entity.Animals;
import tw.wey.service.AnimalsService;

@Controller
public class AnimalsController {
	
	public AnimalsService animalsService;
	
	//讀取全部動物的頁面
	@RequestMapping(path = "/readAnimal", method = RequestMethod.GET)
	public String processReadAnimal(Model m) {
		List<Animals> list = animalsService.readAll();
		m.addAttribute("AnimalsList", list);
		return "ReadAnimal";
	}
}
