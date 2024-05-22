package com.zhiyu;

import com.zhiyu.common.exception.BusinessException;
import com.zhiyu.entity.pojo.system.SystemPermission;
import com.zhiyu.entity.pojo.system.SystemRole;
import com.zhiyu.repository.*;
import com.zhiyu.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author wengzhiyu
 * @date 2020/1/10
 */
@SpringBootTest(classes = ShiroAdminApplication.class)
@RunWith(SpringRunner.class)
public class Test01 {
    @Resource
    private SystemUserRoleRepository systemUserRoleRepository;

    @Resource
    private SystemUserRepository systemUserRepository;
    @Autowired
    private SystemRoleRepository systemRoleRepository;

    @Autowired
    private SystemPermissionRepository systemPermissionRepository;
    @Autowired
    private SystemDepartmentRepository systemDepartmentRepository;
    @Autowired
    private SystemRolePermissionRepository systemRolePermissionRepository;
    @Autowired
    private SystemMenuRepository systemMenuRepository;
    @Autowired
    RedisUtil redisUtil;
    @Resource
    private RedissonClient redissonClient;

    @Test
    @Transactional
    public void test() {
//        RDeque<String> deque = redissonClient.getDeque("ABC");
//        deque.push("A");
//        deque.push("B");
//        deque.removeLast();
//        System.out.println(deque.size());
//        RBucket<String> bucket = redissonClient.getBucket("BBB");
//        bucket.set("CC");
//        bucket.set("CC");

//        KickoutUserVo kickoutUserVo=new KickoutUserVo();
//        kickoutUserVo.setSessionId("3333333");
//        kickoutUserVo.setKickout(true);
//        RBucket<KickoutUserVo> rBucket = redissonClient.getBucket("GGG");
//        System.out.println(rBucket.isExists());
//        KickoutUserVo kickoutUserVo=rBucket.get();
//        if(kickoutUserVo==null){
//            KickoutUserVo vo=new KickoutUserVo();
//            vo.setKickout(true);
//            rBucket.set(vo);
//        }
//        System.out.println("1+++++++++++"+kickoutUserVo);
//
//        RBucket<KickoutUserVo> rBuckets = redissonClient.getBucket("ABC");
//        KickoutUserVo kickoutUsers=rBuckets.get();
//        System.out.println(kickoutUsers);
        a();
//        try {
//            a();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }


    public void a() {
        SystemRole systemRole = new SystemRole();
        systemRole.setRoleName("角色1");
        systemRoleRepository.save(systemRole);
        throw new BusinessException("33");
    }

    public void b() throws Exception {
        SystemPermission systemPermission = new SystemPermission();
        systemPermission.setMenuId(55L);
        systemPermission.setDescription("权限");
        systemPermissionRepository.save(systemPermission);
        throw new Exception("1");
    }
}
