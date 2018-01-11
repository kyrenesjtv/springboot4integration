package me.kyrene.springboot4integration.quartz.job;

import me.kyrene.springboot4integration.pojo.User;
import me.kyrene.springboot4integration.service.IUserService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * Created by wanglin on 2017/10/21.
 */
@Component //实例化到容器中
public class HelloJob implements Job {
	private static Logger LOGGER = LoggerFactory.getLogger(HelloJob.class);
	@Autowired
	IUserService userService;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		String name=context.getJobDetail().getJobDataMap().getString("name");
		User userByID = userService.getUserByID(4L);
		LOGGER.info(userByID.toString());
	}

}
