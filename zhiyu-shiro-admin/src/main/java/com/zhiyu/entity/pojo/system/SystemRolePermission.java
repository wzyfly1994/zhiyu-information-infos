package com.zhiyu.entity.pojo.system;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author wengzhiyu
 * @date 2020/1/10
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Table(name = "system_role_permission")
@Entity
@org.hibernate.annotations.Table(appliesTo = "system_role_permission",comment="角色权限映射表")
@DynamicUpdate
@DynamicInsert
public class SystemRolePermission extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -7756080639525873726L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = " bigint(20) comment '角色id'")
    private Long roleId;

    @Column(columnDefinition = " bigint(20) comment '权限id'")
    private Long permissionId;
}
