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
 * 用户
 * </p>
 *
 * @author Jason
 * @since 2024-12-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {


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
     * 是否超级管理员
     */
    @TableField("is_admin")
    private Boolean isAdmin;

    /**
     * 状态(0:正常 1:禁用)
     */
    private Boolean status;

    /**
     * 是否删除,1是，0否
     */
    private Boolean deleted;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 删除时间
     */
    @TableField("delete_time")
    private Date deleteTime;

    /**
     * 创建人
     */
    @TableField("create_at")
    private Long createAt;

    /**
     * 修改人
     */
    @TableField("update_at")
    private Long updateAt;

    /**
     * 删除人
     */
    @TableField("delete_at")
    private Long deleteAt;


}
