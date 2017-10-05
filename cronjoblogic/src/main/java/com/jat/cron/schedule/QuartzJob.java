package com.jat.cron.schedule;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jat.cron.service.CrawlerCronJobService;
import com.jat.cron.service.SentiAnalCronJobService;

public class QuartzJob implements Job {
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ApplicationContext springContext = 
			    WebApplicationContextUtils.getWebApplicationContext(
			        ContextLoaderListener.getCurrentWebApplicationContext().getServletContext()
			    );
		//CrawlerCronJobService crawlerCronJobSevice = (CrawlerCronJobService) springContext.getBean("CrawlerCronJobService");
		//crawlerCronJobSevice.run();
		SentiAnalCronJobService sentiJob = (SentiAnalCronJobService) springContext.getBean("SentiAnalCronJobService");
		sentiJob.sentimentProcess();
		
	}
}