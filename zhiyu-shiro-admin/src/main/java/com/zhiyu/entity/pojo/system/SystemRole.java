package com.zhiyu.entity.pojo.system;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author wengzhiyu
 * @date 2020/01/08
 */
@Data
@Table(name = "system_role")
@org.hibernate.annotations.Table(appliesTo = "system_role",comment="用户角色表")
@Entity
@DynamicUpdate
@DynamicInsert
public class SystemRole implements Serializable {

    private static final long serialVersionUID = 8138231999166051122L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(columnDefinition = "varchar(16) comment '角色名'")
    private String roleName;

    @Column(columnDefinition = "varchar(64) comment '角色值'")
    private String roleValue;

    @Column(columnDefinition = " int(11) comment '部门id'")
    private Long depId;

    @Column(columnDefinition = "tinyint(1) comment'是否有效'")
    private boolean isUse;

    @Column(columnDefinition = " timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录时间'")
    private Date recordDate;


}
