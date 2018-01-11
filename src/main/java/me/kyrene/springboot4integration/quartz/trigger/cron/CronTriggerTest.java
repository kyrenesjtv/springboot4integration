//package me.kyrene.springboot4integration.quartz.trigger.cron;
//
//import me.kyrene.springboot4integration.quartz.job.HelloJob;
//import org.quartz.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.Resource;
//
//
///**
// * Created by wanglin on 2017/10/21.
// */
//@Component//加载到spring容器中 作为一个bean
//public class CronTriggerTest {
//	private final static Logger LOGGER = LoggerFactory.getLogger(CronTriggerTest.class);
//
//
//	@Resource(name = "scheduler")
//	private Scheduler scheduler;
//
//	@PostConstruct
//	public void init() {//项目启动启动
//		LOGGER.info("加载定时任务开始");
//		try {
//			//Scheduler scheduler = schedulerBean.getScheduler();
//			TriggerKey triggerKey = TriggerKey.triggerKey("quartz-lxh", Scheduler.DEFAULT_GROUP);
//			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("*/10 * * * * ?");//每隔10S执行,项目启动时候也会立马执行一次
//			CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
//			JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withDescription("Hello定时器").withIdentity("hello-job", Scheduler.DEFAULT_GROUP).build();
//			jobDetail.getJobDataMap().put("name", "quartz-lxh");
//
//			scheduler.scheduleJob(jobDetail, trigger);
//			LOGGER.info("task 'quartz-lxh' schedulerRule :'*/10 * * * * ?' reload succeed");
//		} catch (Exception e) {
//			LOGGER.info("定时任务加载失败，请手动加载！\n");
//			LOGGER.info("定时任务加载异常:", e);
//		}
//		LOGGER.info("定时任务加载完成");
//	}
//}
