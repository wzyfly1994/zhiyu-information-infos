package com.zhiyu.repository;

import com.zhiyu.entity.pojo.system.SystemDepartment;
import com.zhiyu.repository.jpa.BaseJpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wengzhiyu
 * @date 2020/10/14
 */
@Repository
public interface SystemDepartmentRepository extends BaseJpaRepository<SystemDepartment, Long> {
}
