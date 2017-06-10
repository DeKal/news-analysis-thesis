package com.jat.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jat.persistence.entity.Press;
import com.jat.persistence.entity.Users;
import com.jat.persistence.entity.VNExpressRootLink;
import com.jat.persistence.repo.UsersRepository;
import com.jat.service.PressService;
import com.jat.service.VnExpressRootLinkService;

@Controller
public class HelloMongoController {
	@Autowired
	private UsersRepository repository;
	
	@Autowired
	PressService pressService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String helloWorld() {
		
//		List<Press> lPress = pressService.listNoContentPress();
//		Press press = pressService.findPress("http://vnexpress.net/tin-tuc/cuoi/anh/muon-kieu-chong-nong-ba-dao-cua-cho-meo-3401078.html");
//		press.setContent("new content");
//		
//		pressService.addPress(press);
		
		return "greeting";
	}
}