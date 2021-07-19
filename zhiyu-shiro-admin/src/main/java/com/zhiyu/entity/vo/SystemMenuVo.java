package com.zhiyu.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhiyu.utils.tree.TreeEntity;
import lombok.Data;

import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * @author wengzhiyu
 * @date 2020/10/27
 */
@Data
public class SystemMenuVo implements TreeEntity<SystemMenuVo> {

    private Long id;

    private Integer level;

    private String menuName;

    private Integer serialNum;

    private Long parentId;

    private String parentName;

    private String redirectUrl;

    private String filterUrl;

    private String permissionValue;

    @JsonFormat(timezone = "GMT+8", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(timezone = "GMT+8", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Integer isUse;

    private String description;

    private String imageUrl;

    @Transient
    List<SystemMenuVo> childList;
}
