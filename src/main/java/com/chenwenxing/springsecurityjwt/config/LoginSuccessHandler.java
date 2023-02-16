package com.chenwenxing.springsecurityjwt.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    JwtUtil jwtUtil;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        String token = jwtUtil.generateToken(authentication.getName());
        httpServletResponse.setHeader("token",token);
        Map<String,Object> respMap=new HashMap<>();
        respMap.put("msg","登录成功");
        respMap.put("status",200);
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(JSONObject.toJSONString(respMap));
        writer.flush();
        writer.close();

    }
}
