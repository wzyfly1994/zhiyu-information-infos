package com.zhiyu.entity.pojo.system;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseEntity {


    @Column(columnDefinition = " bigint null  comment '创建人'")
    private Long createAt;

    @Column(columnDefinition = " bigint null  comment '修改人'")
    private Long updateAt;

    @Column(columnDefinition = " bigint null  comment '删除人'")
    private Long deleteAt;

    @Column(columnDefinition = " timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '创建时间'")
    private Date createTime;

    @Column(columnDefinition = " timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间'")
    private Date updateTime;

    @Column(columnDefinition = " timestamp null  comment '删除时间'")
    private Date deleteTime;

}
