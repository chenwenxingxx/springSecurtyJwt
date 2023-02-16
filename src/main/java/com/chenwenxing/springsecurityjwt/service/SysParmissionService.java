package com.chenwenxing.springsecurityjwt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenwenxing.springsecurityjwt.entity.SysParmission;


import java.util.List;

/**
 * (SysParmission)表服务接口
 *
 * @author makejava
 * @since 2022-11-25 11:18:59
 */
public interface SysParmissionService extends IService<SysParmission> {

    List<SysParmission> getParmissions();
}

