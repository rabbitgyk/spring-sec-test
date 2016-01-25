package com.rabbit.sec.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.rabbit.sec.model.SUser;

/**
 *
 * @author rabbit
 * @date 2016年1月5日 下午12:15:31
 */

//可以在这里将用户登录信息存入数据库。
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{  
  
  @Override  
  public void onAuthenticationSuccess(HttpServletRequest request,  
          HttpServletResponse response, Authentication authentication) throws IOException,  
          ServletException {  
      //获得授权后可得到用户信息   可使用SUserService进行数据库操作
      SUser userDetails = (SUser)authentication.getPrincipal();  
        
      //输出登录提示信息  
      System.out.println("管理员 " + userDetails.getEmail() + " 登录");  
  	
      System.out.println("IP :"+getIpAddress(request));
            
      super.onAuthenticationSuccess(request, response, authentication);  
  }  
  
  public String getIpAddress(HttpServletRequest request){    
      String ip = request.getHeader("x-forwarded-for");    
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
          ip = request.getHeader("Proxy-Client-IP");    
      }    
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
          ip = request.getHeader("WL-Proxy-Client-IP");    
      }    
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
          ip = request.getHeader("HTTP_CLIENT_IP");    
      }    
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
          ip = request.getHeader("HTTP_X_FORWARDED_FOR");    
      }    
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
          ip = request.getRemoteAddr();    
      }    
      return ip;    
  }  
    
}

