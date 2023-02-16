package com.chenwenxing.springsecurityjwt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenwenxing.springsecurityjwt.dao.SysUserDao;
import com.chenwenxing.springsecurityjwt.entity.SysUser;
import com.chenwenxing.springsecurityjwt.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * (SysUser)表服务实现类
 *
 * @author makejava
 * @since 2022-11-25 11:17:17
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

}

