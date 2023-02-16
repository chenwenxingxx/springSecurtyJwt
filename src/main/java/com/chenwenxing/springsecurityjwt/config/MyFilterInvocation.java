package com.chenwenxing.springsecurityjwt.config;



import com.chenwenxing.springsecurityjwt.entity.SysParmission;
import com.chenwenxing.springsecurityjwt.entity.SysRole;
import com.chenwenxing.springsecurityjwt.service.SysParmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 能截取请求的路径
 * 根据什么请求的url确定访问该url需要什么角色
 */

@Component
public class MyFilterInvocation implements FilterInvocationSecurityMetadataSource {

    @Autowired
    SysParmissionService sysParmissionService;

    AntPathMatcher antPathMatcher=new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //获取请求的路径
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        //查询全部的菜单路径和以及菜单对应的角色信息
        List<SysParmission> parmissions = sysParmissionService.getParmissions();
        for (SysParmission parmission : parmissions) {
            //判断请求的路径是否和数据库的菜单路径是否一致
            if(antPathMatcher.match(requestUrl,parmission.getUrl())){
                //获取该菜单的对应角色的所有权限字段值
                String[] toArray = parmission.getRoles().stream().
                        map(SysRole::getRolenameEn).toArray(String[]::new);
                return SecurityConfig.createList(toArray);
            }
        }
        //所有权限都进不去,一般用于登录能访问
        return SecurityConfig.createList("ROLE_login");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
