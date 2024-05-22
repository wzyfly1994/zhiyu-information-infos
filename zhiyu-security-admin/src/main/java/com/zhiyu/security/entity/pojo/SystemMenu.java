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
 * 系统菜单表
 * </p>
 *
 * @author Jason
 * @since 2024-05-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SystemMenu implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 菜单级别
     */
    private Integer level;

    /**
     * 菜单名称
     */
    @TableField("menu_name")
    private String menuName;

    /**
     * 菜单排序号
     */
    @TableField("serial_num")
    private Integer serialNum;

    /**
     * 父节点ID
     */
    @TableField("parent_id")
    private Integer parentId;

    /**
     * 过滤URL
     */
    @TableField("filter_url")
    private String filterUrl;

    /**
     * 权限值
     */
    @TableField("permission_value")
    private String permissionValue;

    /**
     * 菜单描述
     */
    private String description;

    /**
     * 菜单图标
     */
    @TableField("image_url")
    private String imageUrl;

    /**
     * 是否有效,1有效，0无效
     */
    @TableField("is_use")
    private Boolean isUse;

    /**
     * 删除时间
     */
    @TableField("delete_time")
    private Date deleteTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 删除人
     */
    @TableField("delete_at")
    private Long deleteAt;

    /**
     * 修改人
     */
    @TableField("update_at")
    private Long updateAt;

    /**
     * 创建人
     */
    @TableField("create_at")
    private Long createAt;


}
