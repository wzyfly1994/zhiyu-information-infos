package com.zhiyu.entity.pojo.dictionary;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zhiyu.entity.pojo.system.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author wengzhiyu
 * @date 2019/10/22
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "system_dictionary")
public class SystemDictionary extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @JsonIgnore
    @Column(columnDefinition = " bigint(64) comment '类别id'")
    private Long dicTypeId;

    @JsonIgnore
    @Column(columnDefinition = " varchar(64) comment '类别名称'")
    private String dicTypeName;

    @Column(columnDefinition = " varchar(64) comment '字典key'")
    private Integer dicTypeKey;

    @Column(columnDefinition = " varchar(64) comment '字典值'")
    private String dicTypeValue;

    @Column(columnDefinition = " tinyint(1) comment '是否可用'")
    private Boolean isUse;

}