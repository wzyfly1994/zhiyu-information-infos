package com.zhiyu.entity.pojo.system;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author wengzhiyu
 * @date 2020/01/08
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "system_user_role")
@org.hibernate.annotations.Table(appliesTo = "system_user_role",comment="用户角色映射表")
@DynamicInsert
public class SystemUserRole extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -2492563990586576637L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long roleId;
}
