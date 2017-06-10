package com.jat.web.cronjob;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jat.cron.service.CrawlerCronJobService;

public class QuartzJob implements Job {
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ApplicationContext springContext = 
			    WebApplicationContextUtils.getWebApplicationContext(
			        ContextLoaderListener.getCurrentWebApplicationContext().getServletContext()
			    );
		CrawlerCronJobService crawlerCronJobSevice = (CrawlerCronJobService) springContext.getBean("CrawlerCronJobService");
		crawlerCronJobSevice.run();
		
	}
}