package com.chenwenxing.springsecurityjwt.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAccessDeniedHandle implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse,
                       AccessDeniedException e) throws IOException, ServletException {

        httpServletResponse.setContentType("application/json;charset=UTF-8");
        Map<String,Object> respMap=new HashMap<>();
        respMap.put("error",e.getMessage());
        respMap.put("status","403");
        PrintWriter printWriter = httpServletResponse.getWriter();
        printWriter.write(JSONObject.toJSONString(respMap));
        printWriter.flush();
        printWriter.close();
    }
}
