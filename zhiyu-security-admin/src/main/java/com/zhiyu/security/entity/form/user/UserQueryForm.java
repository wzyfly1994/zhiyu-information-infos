package com.zhiyu.security.entity.form.user;

import lombok.Data;

@Data
public class UserQueryForm extends SearchForm {

    private String account;

    private String userName;


}
