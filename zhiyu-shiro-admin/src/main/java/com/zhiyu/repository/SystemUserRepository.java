package com.zhiyu.repository;

import com.zhiyu.entity.pojo.system.SystemUser;
import com.zhiyu.repository.jpa.BaseJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author wengzhiyu
 * @date 2020/01/07
 */
@Repository
public interface SystemUserRepository extends BaseJpaRepository<SystemUser, Long> {


    /**
     * 查询账号或手机
     *
     * @param account
     * @param phone
     * @return
     */
    Optional<SystemUser> findByAccountOrPhone(String account, String phone);


    /**
     * 查找不是当前用户的信息
     *
     * @param id
     * @return
     */
    List<SystemUser> findAllByIdNot(Long id);
}
