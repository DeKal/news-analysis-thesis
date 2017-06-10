package com.jat.web.controller; 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jat.persistence.entity.Press;
import com.jat.persistence.entity.VNExpressRootLink;
import com.jat.service.PressService;
import com.jat.service.VnExpressRootLinkService;

import crawler.VnExpressBreadCrumbCrawlerController;

@Controller
public class WebController {
	
	@Autowired
	PressService pressService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView homePage(ModelMap model) {
		List<Press> lPress = pressService.listPress();
		model.put("listPress", lPress );
		return new ModelAndView("home", model);
	}
    @RequestMapping(value = "/getLinkInfo", method = RequestMethod.GET)
    public ModelAndView greeting(@RequestParam(value="url", required=true) String url, ModelMap model) {
       
        Press press = pressService.findPress(url);
        model.addAttribute("press", press);
        return new ModelAndView("display-link", model);
        
    }

}