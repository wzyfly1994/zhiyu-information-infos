package com.zhiyu.common.entity.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.checkerframework.checker.units.qual.A;

/**
 * <p>
 * 用户角色表
 * </p>
 *
 * @author wengzhiyu
 * @since 2021-06-17
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class SystemRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 角色值
     */
    private String roleValue;

    /**
     * 部门id
     */
    private Integer depId;

    /**
     * 是否有效
     */
    @TableLogic
    private Boolean isUse;

    /**
     * 记录时间
     */
    private Date recordDate;


}
