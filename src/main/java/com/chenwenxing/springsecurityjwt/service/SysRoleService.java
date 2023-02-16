package com.chenwenxing.springsecurityjwt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenwenxing.springsecurityjwt.entity.SysRole;


import java.util.List;

/**
 * (SysRole)表服务接口
 *
 * @author makejava
 * @since 2022-11-25 11:17:17
 */
public interface SysRoleService extends IService<SysRole> {

    List<SysRole> getRoleByUserId(Integer userId);
}

