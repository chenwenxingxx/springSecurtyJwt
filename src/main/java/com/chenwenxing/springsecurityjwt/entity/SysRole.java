package com.chenwenxing.springsecurityjwt.entity;


import com.baomidou.mybatisplus.extension.activerecord.Model;


import java.io.Serializable;

/**
 * (SysRole)表实体类
 *
 * @author makejava
 * @since 2022-11-25 11:17:17
 */
@SuppressWarnings("serial")
public class SysRole extends Model<SysRole> {

    private Integer id;

    private String rolename;

    private String rolenameEn;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getRolenameEn() {
        return rolenameEn;
    }

    public void setRolenameEn(String rolenameEn) {
        this.rolenameEn = rolenameEn;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}

