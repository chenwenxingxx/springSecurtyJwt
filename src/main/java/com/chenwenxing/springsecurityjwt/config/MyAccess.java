package com.chenwenxing.springsecurityjwt.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class MyAccess implements AccessDecisionManager {

    /**
     * 最终判断是否有权限,登录的对象的权限跟请求信息的权限相比较,如果一致就放行
     *
     * @param authentication  登录之后的用户信息,里面封装了角色信息
     * @param object  当前请求的对象
     * @param collection  当前请求需要的角色,(权限字段值)
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {

        for (ConfigAttribute configAttribute : collection) {
            String needRole = configAttribute.getAttribute();
            if("ROLE_login".equals(needRole)){
               if(authentication instanceof AnonymousAuthenticationToken){
                    throw new  AccessDeniedException("用户未登录");
               }else {
                   //登录了直接放行,但没权限
                   return;
               }
            }
            //获取登录对象的权限信息
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if(authority.getAuthority().equals(needRole)){
                    return;
                }
            }

        }
        throw new AccessDeniedException("权限不足");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
