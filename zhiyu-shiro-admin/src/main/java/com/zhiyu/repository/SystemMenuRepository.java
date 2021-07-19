package com.zhiyu.repository;

import com.zhiyu.entity.pojo.system.SystemMenu;
import com.zhiyu.repository.jpa.BaseJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author wengzhiyu
 * @date 2020/1/10
 */
@Repository
public interface SystemMenuRepository extends BaseJpaRepository<SystemMenu, Long> {


    /**
     * findAllByOrOrderBySerialNumAsc
     *
     * @return
     */
    List<SystemMenu> findAllByOrderBySerialNumAsc();

    /**
     * 菜单树
     *
     * @return
     */
    @Query(value = "SELECT CASE WHEN sm.menu_name IS NOT NULL THEN sm.menu_name ELSE '顶层啦' END as parent_name, s.id, s.LEVEL, s.menu_name, s.serial_num, s.parent_id, s.redirect_url, s.filter_url, s.permission_value, s.create_time, s.update_time, s.is_use, s.description, s.image_url FROM system_menu  s LEFT JOIN system_menu sm ON s.parent_id = sm.id ORDER BY  s.serial_num asc"
            , nativeQuery = true)
    List<Map<String, Object>> tree();


}
