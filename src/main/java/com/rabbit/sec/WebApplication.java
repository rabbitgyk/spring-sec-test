package com.rabbit.sec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import com.rabbit.sec.model.SUser;
import com.rabbit.sec.service.SUserService;

@EnableAutoConfiguration
@ComponentScan
@Configuration
public class WebApplication {
	
	

    public static void main(final String[] args) throws Exception {
    	ApplicationContext ctx = SpringApplication.run(WebApplication.class, args);
        
    	SUserService suserService = (SUserService) ctx.getBean("suserService");
        SUser su= suserService.findUserById(1);
        BCryptPasswordEncoder bc=new BCryptPasswordEncoder(4);
        su.setPassword(bc.encode("111111"));
        suserService.update(su);

    }
    
//	@Bean
//	public DataSource dataSource1() {
//
//		DriverManagerDataSource ds = new DriverManagerDataSource();
//		ds.setDriverClassName("com.mysql.jdbc.Driver");
//		ds.setUrl("jdbc:mysql://localhost:3306/jingjing");
//		ds.setUsername("root");
//		ds.setPassword("12120415");
//		return ds;
//	}
	
	@Bean
	public ServletContextTemplateResolver object (){
		ServletContextTemplateResolver viewResolver = new ServletContextTemplateResolver();
	    viewResolver.setPrefix("/");
	    viewResolver.setSuffix(".html");
	    viewResolver.setTemplateMode("HTML5");
	    return viewResolver;
	}

}
