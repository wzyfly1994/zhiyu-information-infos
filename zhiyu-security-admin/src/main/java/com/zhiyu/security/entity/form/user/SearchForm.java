package com.zhiyu.security.entity.form.user;

import com.zhiyu.security.constant.Constants;
import lombok.Data;

@Data
public class SearchForm {

    /**
     * 行号
     */
    private int pageIndex = Constants.DEFAULT_PAGE_INDEX;

    /**
     * 页码大小
     */
    private int pageSize = Constants.DEFAULT_PAGE_SIZE;

}
