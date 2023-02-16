package com.chenwenxing.springsecurityjwt.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTLogoutSuccessHandle implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        if(authentication!=null){
            //将项目中用户信息清空
            new SecurityContextLogoutHandler().logout(httpServletRequest,httpServletResponse,authentication);
        }
        httpServletResponse.setContentType("application/json;charset=utf-8");
        //清空请求头的token,下次请求不会携带token
        httpServletResponse.setHeader("token","");
        PrintWriter writer = httpServletResponse.getWriter();
        Map<String,Object> respMap=new HashMap<>();
        respMap.put("msg","注销成功");
        respMap.put("status",200);
        writer.write(JSONObject.toJSONString(respMap));
        writer.flush();
        writer.close();
    }
}
