package com.zhiyu.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wengzhiyu
 * @date 2020/5/20
 */
@Data
public class SystemUserVo implements Serializable {
    private static final long serialVersionUID = 4457377736383918261L;

    private Long userId;

    private String userName;

    private String phone;

    private String token;

}
