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
 * 菜单
 * </p>
 *
 * @author Jason
 * @since 2024-12-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Menu implements Serializable {


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
    private Long parentId;

    /**
     * 过滤URL
     */
    @TableField("url")
    private String url;

    /**
     * 权限值
     */
    private String permissions;

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
     * 菜单类型（1目录 2菜单 3按钮）
     */
    @TableField("menu_type")
    private Integer menuType;

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
