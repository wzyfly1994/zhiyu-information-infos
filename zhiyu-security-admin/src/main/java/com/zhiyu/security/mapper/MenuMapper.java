package com.zhiyu.security.mapper;

import com.zhiyu.security.entity.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 菜单 Mapper 接口
 * </p>
 *
 * @author Jason
 * @since 2024-12-17
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectMenuPermsByUserId(Long userId);
}
