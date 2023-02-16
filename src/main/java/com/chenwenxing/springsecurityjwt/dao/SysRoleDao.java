package com.chenwenxing.springsecurityjwt.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.chenwenxing.springsecurityjwt.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (SysRole)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-25 11:17:17
 */
public interface SysRoleDao extends BaseMapper<SysRole> {

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysRole> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysRole> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysRole> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysRole> entities);


    List<SysRole>  getRoleByUserId(Integer userId);



}

