package com.zhiyu.entity.pojo.system;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author wengzhiyu
 * @date 2020/6/10
 */
@Data
@Table(name = "system_filter_chain")
@org.hibernate.annotations.Table(appliesTo = "system_filter_chain",comment="shrio过滤链")
@Entity
@DynamicUpdate
@DynamicInsert
public class SystemFilterChain implements Serializable {
    private static final long serialVersionUID = -2922251047891088101L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "`key`", nullable = false, columnDefinition = " varchar(64) comment '过滤链key'")
    private String key;

    @Column(name = "`value`", nullable = false, columnDefinition = " varchar(255) comment '过滤链value'")
    private String value;

    @Column(columnDefinition = " int(16) comment '菜单ID'")
    private Long systemMenuId;

    @Column(columnDefinition = " varchar(8) comment 'auto 系统自动创建 manual 用户创建'")
    private String type;
}
