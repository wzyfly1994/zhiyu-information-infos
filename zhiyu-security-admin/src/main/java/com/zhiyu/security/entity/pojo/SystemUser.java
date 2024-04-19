package com.zhiyu.security.entity.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author wengzhiyu
 * @since 2024-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SystemUser extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String passWord;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 头像图片
     */
    private String headImg;

    /**
     * 性别 1:男，2:女，3:未知
     */
    private Integer sex;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 组织id
     */
    private Long deptId;

    /**
     * 是否启用，1：启用，0：禁用
     */
    private Boolean isActivated;

    /**
     * 是否锁定，1：锁定，0：正常
     */
    private Boolean isLocked;

    /**
     * 是否删除，1：是，0：否
     */
    private Boolean isDelete;

    /**
     * 最大登录数
     */
    private Integer maxSession;

    /**
     * 记录时间
     */
    private Date recordDate;

}
