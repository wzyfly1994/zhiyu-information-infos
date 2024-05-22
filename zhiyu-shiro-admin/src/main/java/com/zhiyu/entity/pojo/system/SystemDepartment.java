package com.zhiyu.entity.pojo.system;

import com.zhiyu.utils.tree.TreeEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author wengzhiyu
 * @date 2020/9/15
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Table(name = "system_department")
@org.hibernate.annotations.Table(appliesTo = "system_department", comment = "组织架构表")
@Entity
@DynamicUpdate
@DynamicInsert
public class SystemDepartment extends BaseEntity implements Serializable, TreeEntity<SystemDepartment> {
    private static final long serialVersionUID = 817685043788156509L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = " varchar(255) comment '部门名称'")
    private String name;

    @Column(columnDefinition = " bigint(64) comment '父部门id，32位整型'")
    private Long parentId;

    @Column(columnDefinition = " bigint(64) comment '在父部门中的次序值。seq值小的排序靠前'")
    private Long seq;

    @Column(columnDefinition = " varchar(255) comment '组织描述'")
    private String description;

    @Column(columnDefinition = "varchar(128) null comment '部门编码'")
    private String code;

    @Column(columnDefinition = " tinyint(1) comment '是否可用'")
    private Boolean isUse;

    @Transient
    List<SystemDepartment> childList;
}
