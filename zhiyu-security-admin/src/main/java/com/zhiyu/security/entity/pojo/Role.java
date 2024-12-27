package com.zhiyu.security.entity.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author Jason
 * @since 2024-12-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Role implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色名
     */
    @TableField("role_name")
    private String roleName;

    /**
     * 角色值
     */
    @TableField("role_value")
    private String roleValue;

    /**
     * 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
     */
    @TableField("data_scope")
    private String dataScope;

    /**
     * 状态(0:正常 1:禁用)
     */
    private Boolean status;

    /**
     * 是否删除,1是，0否
     */
    private Boolean deleted;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 删除时间
     */
    @TableField("delete_time")
    private Date deleteTime;

    /**
     * 创建人
     */
    @TableField("create_at")
    private Long createAt;

    /**
     * 修改人
     */
    @TableField("update_at")
    private Long updateAt;

    /**
     * 删除人
     */
    @TableField("delete_at")
    private Long deleteAt;


}
