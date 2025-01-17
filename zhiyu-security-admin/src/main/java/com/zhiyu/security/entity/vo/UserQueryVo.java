package com.zhiyu.security.entity.vo;

import com.zhiyu.security.entity.pojo.User;
import lombok.Data;

import java.util.Objects;

@Data
public class UserQueryVo {

    private String id;

    private String account;

    private String userName;

    private Integer sex;

    private String email;

    private String phone;

    private String deptId;

    private Boolean isAdmin;

    private Boolean status;


    public  static UserQueryVo build(User user) {
        if (user == null) {
            return null;
        }
        UserQueryVo userQueryVo = new UserQueryVo();
        userQueryVo.setId(user.getId().toString());
        userQueryVo.setAccount(user.getAccount());
        userQueryVo.setUserName(user.getUserName());
        userQueryVo.setSex(user.getSex());
        userQueryVo.setEmail(user.getEmail());
        userQueryVo.setPhone(user.getPhone());
        userQueryVo.setDeptId(Objects.nonNull(user.getDeptId()) ? user.getDeptId().toString() : null);
        userQueryVo.setIsAdmin(user.getIsAdmin());
        userQueryVo.setStatus(user.getStatus());
        return userQueryVo;
    }
}
