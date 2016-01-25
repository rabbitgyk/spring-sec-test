package com.rabbit.sec;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * tomcat部署所需要的初始化类
 * @author rabbit
 * @date 2016年1月6日 下午1:49:44
 */
public class ServletInitializer extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		
		return application.sources(WebApplication.class);
	}

}
