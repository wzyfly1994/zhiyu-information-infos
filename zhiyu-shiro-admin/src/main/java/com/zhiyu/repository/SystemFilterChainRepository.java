package com.zhiyu.repository;

import com.zhiyu.entity.pojo.system.SystemFilterChain;
import com.zhiyu.repository.jpa.BaseJpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wengzhiyu
 * @date 2020/6/10
 */
@Repository
public interface SystemFilterChainRepository extends BaseJpaRepository<SystemFilterChain, Long> {


}
