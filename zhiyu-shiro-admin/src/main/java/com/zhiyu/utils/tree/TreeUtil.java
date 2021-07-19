package com.zhiyu.utils.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 解析线性树
 *
 * @author wengzhiyu
 * @date 2019/11/08
 */
public class TreeUtil {

    private TreeUtil() {
        throw new IllegalStateException("TreeUtil class");
    }

    /**
     * 使用递归方法建树
     */
    public static <E extends TreeEntity<E>> List<E> listToTree(Object topId, List<E> entityList) {
        List<E> resultList = new ArrayList<>();
        //获取顶层元素集合
        Object parentId;
        for (E entity : entityList) {
            parentId = entity.getParentId();
            if (parentId == null || topId.equals(parentId)) {
                resultList.add(entity);
            }
        }
        //获取每个顶层元素的子数据集合
        for (E entity : resultList) {
            entity.setChildList(getSubList(entity.getId(), entityList));
        }
        return resultList;
    }

    /**
     * 获取子数据集合
     *
     * @param id
     * @param entityList
     * @return
     */
    private static <E extends TreeEntity<E>> List<E> getSubList(Object id, List<E> entityList) {
        List<E> childList = new ArrayList<>();
        Object parentId;
        //子集的直接子对象
        for (E entity : entityList) {
            parentId = entity.getParentId();
            if (id.equals(parentId)) {
                childList.add(entity);
            }
        }
        //子集的间接子对象
        for (E entity : childList) {
            entity.setChildList(getSubList(entity.getId(), entityList));
        }
        //递归退出条件
        if (childList.isEmpty()) {
            return null;
        }
        return childList;
    }
}
