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
 * @date 2020/01/08
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Table(name = "system_role")
@org.hibernate.annotations.Table(appliesTo = "system_role",comment="用户角色表")
@Entity
@DynamicUpdate
@DynamicInsert
public class SystemRole extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 8138231999166051122L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(16) comment '角色名'")
    private String roleName;

    @Column(columnDefinition = "varchar(64) comment '角色值'")
    private String roleValue;

    @Column(columnDefinition = " tinyint(1) comment '是否有效,1有效，0无效'")
    private Boolean isUse;

}
