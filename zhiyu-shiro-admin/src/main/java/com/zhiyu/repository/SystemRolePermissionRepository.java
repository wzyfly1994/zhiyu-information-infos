package com.zhiyu.repository;

import com.zhiyu.entity.pojo.system.SystemRolePermission;
import com.zhiyu.repository.jpa.BaseJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wengzhiyu
 * @date 2020/1/10
 */
@Repository
public interface SystemRolePermissionRepository extends BaseJpaRepository<SystemRolePermission, Long> {


    /**
     * findAllByRoleIdIn
     *
     * @param roleList
     * @return
     */
    List<SystemRolePermission> findAllByRoleIdIn(List<Long> roleList);

    /**
     * removeByRoleIdIn
     *
     * @param listRoleId
     */
    @Transactional(rollbackFor = Exception.class)
    void deleteByRoleIdIn(List<Long> listRoleId);
}
