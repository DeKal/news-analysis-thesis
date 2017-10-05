package com.jat.web.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jat.persistence.entity.Press;
import com.jat.service.PressService;

import common.io.FileIO;
import jat.algo.api.AlgoAnalyzeAPI;

@Controller
public class WebController {

	private static List<Press> allPress;
	private static PageHolder pageHolder;

	@Autowired
	PressService pressService;

	@Autowired
	AlgoAnalyzeAPI algoAPI;

	private void initPress() {
		if (allPress == null) {
			allPress = pressService.listPress();
			pageHolder = new PageHolder(allPress);
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getHomePage(@RequestParam(value = "page", required = true, defaultValue = "0") int page,
			ModelMap model) {
		initPress();
		model.put("listPress", pageHolder.getPressPage(page));
		model.put("page", page);
		return new ModelAndView("home", model);
	}

	@RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
	public ModelAndView foo(@PathVariable("page") int page, ModelMap model) {
		initPress();
		model.put("listPress", pageHolder.getPressPage(page));
		model.put("page", page);
		return new ModelAndView("home", model);
	}

	@RequestMapping(value = "/getLinkInfo", method = RequestMethod.GET)
	public ModelAndView getLinkInfo(@RequestParam(value = "url", required = true) String url, ModelMap model) {

		Press press = pressService.findPress(url);
		model.addAttribute("press", press);
		return new ModelAndView("display-link", model);

	}

	@RequestMapping(value = "/testAlgo", method = RequestMethod.GET)
	public ModelAndView testAlgo(ModelMap model) throws Exception {

		int label = algoAPI.getCommentSentiVNWord("haha");
		label = algoAPI.getCommentSentiSVM("haha");
		algoAPI.getCommentSentiSVM("haha");
		algoAPI.getCommentSentiSVM("haha");
		algoAPI.getCommentSentiSVM("haha");
		algoAPI.getCommentSentiSVM("haha");
		algoAPI.getCommentSentiSVM("haha");
		algoAPI.getCommentSentiSVM("haha");
		algoAPI.getCommentSentiSVM("haha");
		algoAPI.getCommentSentiSVM("haha");
		algoAPI.getCommentSentiSVM("haha");
		algoAPI.getCommentSentiSVM("haha");
		algoAPI.getCommentSentiSVM("haha");
		algoAPI.getCommentSentiSVM("haha");
		algoAPI.getCommentSentiSVM("haha");
		algoAPI.getCommentSentiSVM("haha");
		algoAPI.getCommentSentiSVM("haha");
		algoAPI.getCommentSentiSVM("haha");
		algoAPI.getCommentSentiSVM("haha");
		algoAPI.getCommentSentiSVM("haha");
		algoAPI.getCommentSentiSVM("haha");
		algoAPI.getCommentSentiSVM("haha");
		algoAPI.getCommentSentiSVM("haha");
		String data;

		/*
		FileIO.createReader(root);
		data = FileIO.readLine();
		FileIO.closeReader();
		*/

		model.addAttribute("data", String.valueOf(label));
		return new ModelAndView("testAlgo", model);
	}

	class PageHolder {
		private List<Press> lPress;

		public PageHolder(List<Press> presses) {
			lPress = presses;
		}

		public List<Press> getPressPage(int page) {
			List<Press> lCriPress = new ArrayList<Press>();
			if (page * 20 < lPress.size()) {
				for (int p = 0; p < 20; ++p) {

					lCriPress.add(lPress.get(page * 20 + p));
				}
			}
			return lCriPress;
		}
	}

}