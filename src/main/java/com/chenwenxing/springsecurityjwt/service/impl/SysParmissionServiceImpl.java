package com.chenwenxing.springsecurityjwt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenwenxing.springsecurityjwt.dao.SysParmissionDao;
import com.chenwenxing.springsecurityjwt.entity.SysParmission;
import com.chenwenxing.springsecurityjwt.service.SysParmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (SysParmission)表服务实现类
 *
 * @author makejava
 * @since 2022-11-25 11:18:59
 */
@Service("sysParmissionService")
public class SysParmissionServiceImpl extends ServiceImpl<SysParmissionDao, SysParmission> implements SysParmissionService {
    @Autowired
    SysParmissionDao sysParmissionDao;
    @Override
    public List<SysParmission> getParmissions() {
        return sysParmissionDao.getParmissions();
    }
}

