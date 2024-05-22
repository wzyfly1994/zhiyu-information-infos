package com.zhiyu.entity.pojo.system;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author wengzhiyu
 * @date 2020/01/04
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "system_user")
@org.hibernate.annotations.Table(appliesTo = "system_user",comment="用户表")
@DynamicUpdate
@DynamicInsert
public class SystemUser extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 8062449516584938156L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(32) comment '账号'")
    private String account;

    @Column(columnDefinition = "varchar(64) comment '密码'")
    private String passWord;

    @Column(columnDefinition = " varchar(16) comment '用户名'")
    private String userName;

    @Column(columnDefinition = " varchar(255) comment '头像图片'")
    private String headImg;

    @Column(columnDefinition = " smallint(5) comment '性别 1:男，2:女，3:未知'")
    private Integer sex;

    @Column(columnDefinition = " varchar(255) comment '邮箱'")
    private String email;

    @Column(columnDefinition = " varchar(32) comment '手机号'")
    private String phone;

    @Column(columnDefinition = " bigint(20) comment '组织id'")
    private Long deptId;

    @Column(columnDefinition = " tinyint(1) comment '是否启用，1：启用，0：禁用'")
    private Boolean isActivated;

    @Column(columnDefinition = " tinyint(1)comment '是否锁定，1：锁定，0：正常'")
    private Boolean isLocked;

    @Column(columnDefinition = " tinyint(1) comment '是否删除，1：是，0：否'")
    private Boolean isDelete;

    @Column(columnDefinition = " smallint(5) comment '最大登录数'")
    private Integer maxSession;

    @Column(columnDefinition = " tinyint(1) comment '是否有效,1有效，0无效'")
    private Boolean isUse;

}
