package com.zhiyu.security.entity.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Jason
 * @since 2024-05-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SystemUser implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    @TableField("pass_word")
    private String passWord;

    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 头像图片
     */
    @TableField("head_img")
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
    @TableField("dept_id")
    private Long deptId;

    /**
     * 是否启用，1：启用，0：禁用
     */
    @TableField("is_activated")
    private Boolean isActivated;

    /**
     * 是否锁定，1：锁定，0：正常
     */
    @TableField("is_locked")
    private Boolean isLocked;

    /**
     * 是否删除，1：是，0：否
     */
    @TableField("is_delete")
    private Boolean isDelete;

    /**
     * 最大登录数
     */
    @TableField("max_session")
    private Integer maxSession;

    /**
     * 是否有效,1有效，0无效
     */
    @TableField("is_use")
    private Boolean isUse;

    /**
     * 删除时间
     */
    @TableField("delete_time")
    private Date deleteTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 删除人
     */
    @TableField("delete_at")
    private Long deleteAt;

    /**
     * 修改人
     */
    @TableField("update_at")
    private Long updateAt;

    /**
     * 创建人
     */
    @TableField("create_at")
    private Long createAt;


}
