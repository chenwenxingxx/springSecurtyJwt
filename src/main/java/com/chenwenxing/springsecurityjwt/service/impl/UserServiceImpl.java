package com.chenwenxing.springsecurityjwt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chenwenxing.springsecurityjwt.dao.SysRoleDao;
import com.chenwenxing.springsecurityjwt.dao.SysUserDao;
import com.chenwenxing.springsecurityjwt.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    SysUserDao sysUserDao;

    @Autowired
    SysRoleDao sysRoleDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<SysUser> queryWrapper=new LambdaQueryWrapper<>();
        SysUser sysUser=null;
        if(!StringUtils.isEmpty(username)){
            queryWrapper.eq(SysUser::getUsername,username);
            sysUser = sysUserDao.selectOne(queryWrapper);
            //通过用户id查询角色信息,给后面请求的菜单角色做比较
            sysUser.setSysRoles(sysRoleDao.getRoleByUserId(sysUser.getId()));

//            UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(
//                    sysUser.getUsername(),null,sysUser.getAuthorities());
//
//            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        return sysUser;
    }
}
