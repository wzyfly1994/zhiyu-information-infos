package com.zhiyu.entity.pojo.system;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author wengzhiyu
 * @date 2020/01/09
 */
@Data
@Table(name = "system_menu")
@org.hibernate.annotations.Table(appliesTo = "system_menu", comment = "系统菜单表")
@Entity
@DynamicUpdate
@DynamicInsert
public class SystemMenu implements Serializable {

    private static final long serialVersionUID = -3470801336745091477L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(columnDefinition = " tinyint(4) comment '菜单级别'")
    private Integer level;

    @Column(columnDefinition = " varchar(128) comment '菜单名称'")
    private String menuName;

    @Column(columnDefinition = " int(11) comment '菜单排序号'")
    private Integer serialNum;

    @Column(columnDefinition = " int(11) comment '父节点ID'")
    private Long parentId;

    @Column(columnDefinition = " varchar(255) comment '过滤URL'")
    private String filterUrl;

    @Column(columnDefinition = " varchar(64) comment '权限值'")
    private String permissionValue;

    @Column(columnDefinition = " timestamp comment '创建时间'")
    private Date createTime;

    @Column(columnDefinition = " timestamp comment '修改时间'")
    private Date updateTime;

    @Column(columnDefinition = " tinyint(1) comment '是否有效,1有效，0无效'")
    private Boolean isUse;

    @Column(columnDefinition = " varchar(255) comment '菜单描述'")
    private String description;

    @Column(columnDefinition = " varchar(128) comment '菜单图标'")
    private String imageUrl;

}
