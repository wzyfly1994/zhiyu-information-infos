package com.zhiyu.service;


import com.zhiyu.entity.vo.DictionaryVo;

import java.util.List;
import java.util.Map;

/**
 * @author wengzhiyu
 * @date 2019/11/12
 */
public interface DictionaryService {

    /**
     * 加载下拉字典
     *
     * @param selectName
     * @return
     */
    Map<String, List<DictionaryVo>> loadKevValueManage(String selectName);


    /**
     * 测试保存权限
     */
    void permissionSave();
}
