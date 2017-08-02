package com.jat.web.controller; 

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jat.persistence.entity.Press;
import com.jat.service.PressService;

import jat.algo.api.AlgoAnalyzeAPI;

@Controller
public class WebController {
	
	@Autowired
	PressService pressService;
	
	@Autowired
	AlgoAnalyzeAPI algoAPI;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getHomePage(ModelMap model) {
		List<Press> lPress = pressService.listPress();
		model.put("listPress", lPress );
		return new ModelAndView("home", model);
	}
	
    @RequestMapping(value = "/getLinkInfo", method = RequestMethod.GET)
    public ModelAndView getLinkInfo(@RequestParam(value="url", required=true) String url, ModelMap model) {
       
        Press press = pressService.findPress(url);
        model.addAttribute("press", press);
        return new ModelAndView("display-link", model);
        
    }
    @RequestMapping(value = "/testAlgo", method = RequestMethod.GET)
    public ModelAndView testAlgo( ModelMap model) throws Exception {
    	
    	int inputStream = algoAPI.getCommentSentiSVM("haha");
    

    	model.addAttribute("data", inputStream);
    	return new ModelAndView("testAlgo", model);
    }


}