package com.zhiyu.repository;

import com.zhiyu.entity.pojo.dictionary.ColumnDomain;
import com.zhiyu.repository.jpa.BaseJpaRepository;

import java.util.List;

/**
 * @author wengzhiyu
 * @createTime 2019/11/11
 */
public interface ColumnDomainRepository extends BaseJpaRepository<ColumnDomain, Integer> {

    /**
     * 查找字典key
     *
     * @param selectName
     * @return
     */
    List<ColumnDomain> findAllBySelectName(String selectName);


    /**
     * 查询所有字典
     *
     * @return
     */
    @Override
    List<ColumnDomain> findAll();
}
