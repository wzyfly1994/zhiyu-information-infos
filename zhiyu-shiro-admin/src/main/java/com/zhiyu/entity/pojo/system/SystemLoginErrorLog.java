package com.zhiyu.entity.pojo.system;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * @author wengzhiyu
 * @date 2020/01/11
 */
@Data
public class SystemLoginErrorLog implements Serializable {

    private static final long serialVersionUID = -6265612527157067896L;

    @Column(columnDefinition = " varchar(64) comment '账号'")
    private String account;

    @Column(columnDefinition = " timestamp comment '创建时间'")
    private Date createTime;
}
