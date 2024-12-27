package com.zhiyu.security.service.impl;

import com.zhiyu.security.entity.pojo.Department;
import com.zhiyu.security.mapper.DepartmentMapper;
import com.zhiyu.security.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门 服务实现类
 * </p>
 *
 * @author Jason
 * @since 2024-12-17
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

}
