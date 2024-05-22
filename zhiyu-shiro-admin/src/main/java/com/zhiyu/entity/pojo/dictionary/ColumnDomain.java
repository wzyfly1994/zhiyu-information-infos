package com.zhiyu.entity.pojo.dictionary;

import com.zhiyu.entity.pojo.system.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author wengzhiyu
 * @date 2019/10/22
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "column_domains")
public class ColumnDomain extends BaseEntity {

    @Id
    @Column(columnDefinition = " varchar(64) comment '字典名称'")
    private String selectName;

    @Column(columnDefinition = " varchar(64) comment '表名'")
    private String tableName;

    @Column(columnDefinition = " varchar(64) comment '键值key(表字段)'")
    private String keyColumn;

    @Column(columnDefinition = " varchar(64) comment '键值value(表字段)'")
    private String valueColumn;

    @Column(columnDefinition = " varchar(64) comment '父类键值(表字段)'")
    private String conditionColumn;

    @Column(columnDefinition = " tinyint(1) comment '是否可用'")
    private Boolean isUse;

}