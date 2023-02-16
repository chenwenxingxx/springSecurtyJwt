package com.chenwenxing.springsecurityjwt.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenwenxing.springsecurityjwt.entity.SysParmission;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * (SysParmission)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-25 11:18:59
 */
public interface SysParmissionDao extends BaseMapper<SysParmission> {

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysParmission> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysParmission> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysParmission> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysParmission> entities);

    List<SysParmission> getParmissions();

}

