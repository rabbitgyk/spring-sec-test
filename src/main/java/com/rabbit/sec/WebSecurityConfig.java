package com.rabbit.sec;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import com.rabbit.sec.handler.LoginSuccessHandler;
import com.rabbit.sec.service.CustomUserDetailsService;

/**
 *
 * @author rabbit
 * @date 2016年1月5日 上午11:34:29
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private DataSource dataSource;


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//允许所有用户访问”/”和”/home”
		http.authorizeRequests().antMatchers("/", "/home").permitAll()
        //其他地址的访问均需验证权限
		.anyRequest().authenticated().and().formLogin()
		//指定登录页是”/login”
		.loginPage("/login").permitAll()
		//登录成功后可使用loginSuccessHandler()存储用户信息，可选。
		.successHandler(loginSuccessHandler())
		.and().logout()
		//退出登录后的默认网址是”/home”
		.logoutSuccessUrl("/home").permitAll()
		.invalidateHttpSession(true)
		.and()
		//登录后记住用户，下次自动登录
        //数据库中必须存在名为persistent_logins的表
        //建表语句见code15
		.rememberMe()
		.tokenValiditySeconds(1209600)
		//指定记住登录信息所使用的数据源
		.tokenRepository(tokenRepository());//code4
	
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {	
//指定密码加密所使用的加密器为passwordEncoder()
//需要将密码加密后写入数据库 //code13
	auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());//code5
//不删除凭据，以便记住用户
		auth.eraseCredentials(false);		
	}
	
	// Code5----------------------------------------------
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(4);
	}

	// Code4----------------------------------------------
	@Bean
	public JdbcTokenRepositoryImpl tokenRepository(){		
		JdbcTokenRepositoryImpl j=new JdbcTokenRepositoryImpl();
		j.setDataSource(dataSource);
		return j;
	}

	// Code3----------------------------------------------
	@Bean
	public LoginSuccessHandler loginSuccessHandler(){
		return new LoginSuccessHandler();//code6
	}
}
