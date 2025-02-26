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
 * @date 2020/01/09
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Table(name = "system_permission")
@org.hibernate.annotations.Table(appliesTo = "system_permission",comment="用户角色映射表")
@Entity
@DynamicUpdate
@DynamicInsert
public class SystemPermission extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1286088385795878538L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(16) comment '权限名称'")
    private String permissionName;

    @Column(columnDefinition = "varchar(64) comment '权限值'")
    private String permissionValue;

    @Column(columnDefinition = "varchar(64) comment '菜单Id'")
    private Long menuId;

    @Column(columnDefinition = "varchar(64) comment '权限描述'")
    private String description;

    @Column(columnDefinition = " tinyint(1) comment '是否有效,1有效，0无效'")
    private Boolean isUse;

}
