package com.weeds.aoi.api.quartz;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.weeds.aoi.dict.domain.Dict;
import com.weeds.aoi.dict.service.DictComponent;


/***
 * 
 * Quartz设置项目全局的定时任务
 * @Component注解的意义        泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注。一般公共的方法我会用上这个注解
 * @author xuanxy
 *
 */
@Component
public class QuartzDemo {
	
	@Value("${app.collect.host:'127.0.0.1:8181'}") 
	private String collectIp;
	
	@Resource
	private DictComponent dict;
    
    /*@Scheduled(cron = "0 0/1 * * * ?") // 每分钟执行一次
    public void work() throws Exception {
    	List<Dict> dictList = dict.getDictByTypeId(24);
        System.out.println("执行调度任务："+new Date()+"---"+dictList.size());
    }
    
    
    @Scheduled(fixedRate = 5000)//每5秒执行一次
    public void play() throws Exception {
        System.out.println("执行Quartz定时器任务："+new Date()+"--collectIp="+collectIp);
    }

    
    
    @Scheduled(cron = "0 10 14 ? * *") //定时某点执行（14:10分）
    public void doSomething() throws Exception {
        System.out.println("定时任务执行："+new Date());
    }*/
}
