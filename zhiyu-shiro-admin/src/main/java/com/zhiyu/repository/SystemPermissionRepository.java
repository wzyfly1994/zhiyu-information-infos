package com.zhiyu.repository;

import com.zhiyu.entity.pojo.system.SystemPermission;
import com.zhiyu.repository.jpa.BaseJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wengzhiyu
 * @date 2020/1/10
 */
@Repository
public interface SystemPermissionRepository extends BaseJpaRepository<SystemPermission, Long> {

    /**
     * findAllByRoleIdIn
     *
     * @param idList
     * @return
     */
    List<SystemPermission> findAllByIdIn(List<Long> idList);

    /**
     * findAllByMenuId
     *
     * @param menuId
     * @return
     */
    List<SystemPermission> findAllByMenuId(Long menuId);


    /**
     * countByMenuId
     *
     * @param menuId
     * @return
     */
    Long countByMenuId(Long menuId);

}
