package com.zhiyu.repository;

import com.zhiyu.entity.pojo.dictionary.SystemDictionary;
import com.zhiyu.repository.jpa.BaseJpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wengzhiyu
 * @createTime 2019/11/11
 */
@Repository
public interface SystemDictionaryRepository extends BaseJpaRepository<SystemDictionary, Integer> {
}
