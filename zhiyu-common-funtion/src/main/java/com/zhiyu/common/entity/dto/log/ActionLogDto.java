package com.zhiyu.common.entity.dto.log;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: wengzhiyu
 * @create: 2022-09-05 20:34
 **/
@Data
public class ActionLogDto {

    private Long id;

    private String context;

    private LocalDateTime localDateTime;

}
