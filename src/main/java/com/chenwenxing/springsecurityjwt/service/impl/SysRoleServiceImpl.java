package com.chenwenxing.springsecurityjwt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenwenxing.springsecurityjwt.dao.SysRoleDao;
import com.chenwenxing.springsecurityjwt.entity.SysRole;
import com.chenwenxing.springsecurityjwt.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (SysRole)表服务实现类
 *
 * @author makejava
 * @since 2022-11-25 11:17:17
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements SysRoleService {

    @Autowired
    SysRoleDao sysRoleDao;
    @Override
    public List<SysRole> getRoleByUserId(Integer userId) {
        return sysRoleDao.getRoleByUserId(userId);
    }
}

