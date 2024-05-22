package com.zhiyu.entity.pojo.system;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "system_role_menu")
@org.hibernate.annotations.Table(appliesTo = "system_role_menu", comment = "角色菜单表")
@DynamicUpdate
@DynamicInsert
public class SystemRoleMenu extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(columnDefinition = " bigint(20) comment '菜单id'")
    private Long menuId;

    @Column(columnDefinition = " bigint(20) comment '角色id'")
    private Long roleId;

}
