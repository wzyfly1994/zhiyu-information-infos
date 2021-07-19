package com.zhiyu.entity.pojo.dictionary;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

/**
 * @author wengzhiyu
 * @date 2019/10/22
 */
@Data
@Entity
@Table(name = "system_dictionary")
public class SystemDictionary {

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

    @JsonIgnore
    @Column(columnDefinition = " tinyint(1) comment '是否可用'")
    private Boolean isUse;

}