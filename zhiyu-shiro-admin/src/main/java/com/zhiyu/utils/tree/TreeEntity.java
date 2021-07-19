package com.zhiyu.utils.tree;

import java.util.List;

/**
 * 树状实体
 * @author wengzhiyu
 * @date 2019/11/08
 */
public interface TreeEntity<E> {
     /**
      * 获取ID
      * @return
      */
     Object getId();

     /**
      * 获取父级ID
      * @return
      */
     Object getParentId();

     /**
      * 赋值子类
      * @param childList
      */
     void setChildList(List<E> childList);

}
