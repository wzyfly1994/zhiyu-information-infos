package com.zhiyu.common.shiro.filter;

import com.zhiyu.config.constant.BCErrorCode;
import com.zhiyu.config.constant.Constants;
import com.zhiyu.entity.pojo.system.SystemUser;
import com.zhiyu.entity.vo.KickoutUserVo;
import com.zhiyu.utils.JwtUtil;
import com.zhiyu.utils.ResponseUtil;
import io.jsonwebtoken.Claims;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.redisson.api.RBucket;
import org.redisson.api.RDeque;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 并发登陆控制
 *
 * @author wengzhiyu
 * @date 2020/11/2
 */
@Data
@Slf4j
public class KickOutFilter extends AccessControlFilter {

    private RedissonClient redissonClient;


    /**
     * 是否允许访问，返回true表示允许
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }


    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            String token = req.getHeader(Constants.TOKEN_HEADER);
            if (StringUtils.isBlank(token)) {
                ResponseUtil.setResponse(response, BCErrorCode.USER_TOKEN_NULL.getCode(), BCErrorCode.USER_TOKEN_NULL.getMsg(), null);
                return false;
            }
            token = token.replace(Constants.TOKEN_PREFIX, "");
            //解析传过来的token
            Claims claims = JwtUtil.getClaims(token);
            if (claims == null) {
                ResponseUtil.setResponse(response, BCErrorCode.PARAMS_VALIDATE_ERROR.getCode(), BCErrorCode.PARAMS_VALIDATE_ERROR.getMsg(), null);
                return false;
            }
            String accountPram = claims.get("account", String.class);
            if (StringUtils.isBlank(accountPram)) {
                ResponseUtil.setResponse(response, BCErrorCode.TOKEN_ACCOUNT_NULL.getCode(), BCErrorCode.TOKEN_ACCOUNT_NULL.getMsg(), null);
                return false;
            }
            Subject subject = SecurityUtils.getSubject();
            //获取session
            Session session = subject.getSession();
            //获取当前登陆的用户账号
            String account = (String) subject.getPrincipal();
            if (!accountPram.equals(account)) {
                ResponseUtil.setResponse(response, BCErrorCode.TOKEN_ACCOUNT_DIFF.getCode(), BCErrorCode.TOKEN_ACCOUNT_DIFF.getMsg(), null);
                return false;
            }
            //获取token登陆时间
            long tokenTime = claims.get("timestamp", Long.class);
            long currentTime = System.currentTimeMillis();
            //大于2小时重新登陆
            long maxTime = TimeUnit.HOURS.toMillis(2);
            long diffTime = currentTime - tokenTime;
            //大于两小时退出登陆
            if (diffTime > maxTime) {
                ResponseUtil.setResponse(response, BCErrorCode.LOGIN_TIMEOUT_ERROR.getCode(), BCErrorCode.LOGIN_TIMEOUT_ERROR.getMsg(), null);
                subject.logout();
                return false;
            }
            SystemUser systemUser = (SystemUser) session.getAttribute(Constants.LOGIN_USER + account);
            //获取session中的用户对象
            if (systemUser == null) {
                ResponseUtil.setResponse(response, BCErrorCode.SEESION_TOKEN_ACCOUNT_NULL.getCode(), BCErrorCode.SEESION_TOKEN_ACCOUNT_NULL.getMsg(), null);
                return false;
            }
            String sessionId = (String) session.getId();
            String lockKey = Constants.LOGIN_LOCK + account;
            String kickOutKey = Constants.KICK_OUT_LOGIN + account;
            String kickOutSign = Constants.KICK_OUT_SIGN + account + ":";
            //加入RBucket
            RBucket<KickoutUserVo> rBucket = redissonClient.getBucket(kickOutSign + sessionId);
            if (!rBucket.isExists()) {
                KickoutUserVo kickoutUserVo = new KickoutUserVo();
                kickoutUserVo.setSessionId(sessionId);
                rBucket.set(kickoutUserVo, maxTime, TimeUnit.MILLISECONDS);
            }
            KickoutUserVo kickoutUserDt = rBucket.get();
            Integer maxSession = systemUser.getMaxSession();
            RLock rLock = redissonClient.getLock(lockKey);
            rLock.lock();
            String removeSessionId = null;
            try {
                RDeque<String> deque = redissonClient.getDeque(kickOutKey);
                // 如果队列中没有放入队列
                if (!deque.contains(sessionId) && !kickoutUserDt.isKickout()) {
                    deque.push(sessionId);
                }
                if (deque.size() != 0 && deque.size() > maxSession) {
                    //提出前者
                    removeSessionId = deque.removeLast();
                }
            } catch (Exception e) {
                log.info("并发控制分布式加锁失败;[{}]", e.getMessage());
                ResponseUtil.setResponse(response, BCErrorCode.ERROR.getCode(), BCErrorCode.ERROR.getMsg(), null);
                return false;
            } finally {
                if (rLock.isLocked()) {
                    if (rLock.isHeldByCurrentThread()) {
                        rLock.unlock();
                    }
                }
            }
            //标记要提出的对象
            if (removeSessionId != null) {
                RBucket<KickoutUserVo> removeBucket = redissonClient.getBucket(kickOutSign + removeSessionId);
                KickoutUserVo removeKickOut = removeBucket.get();
                if (removeKickOut != null) {
                    removeKickOut.setKickout(true);
                    removeBucket.set(removeKickOut);
                }
            }
            //判断当前请求是否注销
            if (kickoutUserDt.isKickout()) {
                subject.logout();
                rBucket.delete();
                ResponseUtil.setResponse(response, BCErrorCode.PARAMS_VALIDATE_ONTHER.getCode(), BCErrorCode.PARAMS_VALIDATE_ONTHER.getMsg(), null);
                return false;
            }
        } catch (Exception e) {
            log.error("并发控制异常:", e);
            try {
                ResponseUtil.setResponse(response, BCErrorCode.ERROR.getCode(), BCErrorCode.ERROR.getMsg(), null);
            } catch (IOException ex) {
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }

}
