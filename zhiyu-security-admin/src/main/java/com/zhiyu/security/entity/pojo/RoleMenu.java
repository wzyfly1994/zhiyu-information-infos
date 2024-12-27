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
 * 角色菜单
 * </p>
 *
 * @author Jason
 * @since 2024-12-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleMenu implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 菜单id
     */
    @TableField("menu_id")
    private Long menuId;

    /**
     * 角色id
     */
    @TableField("role_id")
    private Long roleId;

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
