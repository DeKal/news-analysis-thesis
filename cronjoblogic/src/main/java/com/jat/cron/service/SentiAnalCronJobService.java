package com.jat.cron.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.jat.persistence.entity.Comment;
import com.jat.persistence.entity.Press;
import com.jat.service.PressService;

import jat.algo.api.AlgoAnalyzeAPI;

@Component("SentiAnalCronJobService")
public class SentiAnalCronJobService {
	@Autowired(required = true)
	private PressService pressService;
	@Autowired(required = true)
	private AlgoAnalyzeAPI agoAPI;

	public void sentimentProcess() {
		List<Press> lPress = pressService.listPress();
		for (Press p : lPress) {
			List<Comment> lc = p.getComment();
			if (lc != null){
				for (Comment c : lc) {
					try {
						c.setSentiSVM(agoAPI.getCommentSentiSVM(c.getMessage()));
						c.setSentiVNWord(agoAPI.getCommentSentiVNWord(c.getMessage()));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			pressService.addPress(p);
		}
		System.out.println("Sentiment Process Done");
	}

}
