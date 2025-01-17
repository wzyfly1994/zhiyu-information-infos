package com.zhiyu.security.entity.dto.user;


import com.zhiyu.security.entity.pojo.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class UserRoleDto {

    private String id;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 角色值
     */
    private String roleValue;

    /**
     * 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
     */
    private String dataScope;


    public static UserRoleDto toMo(Role role) {
        if (role == null) {
            return null;
        }
        UserRoleDto userRoleDto = new UserRoleDto();
        userRoleDto.setId(role.getId() != null ? role.getId().toString() : null);
        userRoleDto.setRoleName(role.getRoleName());
        userRoleDto.setRoleValue(role.getRoleValue());
        userRoleDto.setDataScope(role.getDataScope());
        return userRoleDto;
    }

}
