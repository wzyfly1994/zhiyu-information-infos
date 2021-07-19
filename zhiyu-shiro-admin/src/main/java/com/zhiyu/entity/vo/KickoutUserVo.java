package com.zhiyu.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wengzhiyu
 * @date 2020/11/10
 */
@Data
public class KickoutUserVo implements Serializable {

    private static final long serialVersionUID = -6508327490814515365L;
    private String sessionId;

    private boolean isKickout;
}
