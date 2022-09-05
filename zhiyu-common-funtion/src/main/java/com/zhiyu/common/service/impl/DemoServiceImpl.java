package com.zhiyu.common.service.impl;

import com.zhiyu.common.entity.dto.elasticsearch.SearchDocDto;
import com.zhiyu.common.entity.dto.log.ActionLogDto;
import com.zhiyu.common.entity.pojo.SystemRole;
import com.zhiyu.common.service.DemoService;
import com.zhiyu.common.service.SystemRoleService;
import com.zhiyu.common.utils.response.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author wengzhiyu
 * @since 2021/5/14 15:24
 */
@Service
@Slf4j
public class DemoServiceImpl implements DemoService {

    @Autowired
    private SystemRoleService systemRoleService;

    private List<String>  arrList=new ArrayList<>();


    @Override
    //@Async
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void async1() {
        log.info("runIn----async1-->" + Thread.currentThread().getName());
        //saveData("async1");
    }

    @Override
    @Async
    //@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
    public Future<String> async2() {
        log.info("333333333333");
        log.info("runIn----async2-->" + Thread.currentThread().getName());
        log.info("111111111111");
        //saveData("async2");
        //int i=1/0;
        return new AsyncResult<>("success");
    }

    @Override
    // @Async
    @Transactional(rollbackFor = Exception.class)
    public void async3() {
        log.info("runIn----async3-->" + Thread.currentThread().getName());
        saveData("async3");
    }

    @Override
    public String locks() {
        String threadName = Thread.currentThread().getName();
        log.info("=======================================================");
        log.info("=======================================================");
        log.info("=======================================================");
        log.info("线程：{}进方法,当前对象：{}", threadName, this);
        synchronized (this) {
            log.info("线程：{}进锁", threadName);
            try {
                TimeUnit.SECONDS.sleep(6L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("线程：{}执行完毕", threadName);
        }
        return threadName;
    }

    @Override
    public void setList(String meta) {
        String threadName = Thread.currentThread().getName();
        log.info("线程：{}进方法,当前对象：{}", threadName, this);
        arrList.add(meta);
    }

    @Override
    public List<String> getList() {
        String threadName = Thread.currentThread().getName();
        log.info("线程：{}进方法,当前对象：{}", threadName, this);
        return arrList;
    }

    @Override
    public ResponseData recordLog(SearchDocDto searchDocDto) {

        return null;
    }


    private void saveData(String data) {
        SystemRole systemRole = new SystemRole();
        systemRole.setRoleName(data);
        systemRoleService.save(systemRole);
    }
}
