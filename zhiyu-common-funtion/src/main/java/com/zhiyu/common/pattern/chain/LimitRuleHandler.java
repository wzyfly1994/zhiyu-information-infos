package com.zhiyu.common.pattern.chain;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wengzhiyu
 * @date 2020/12/31
 */
@Slf4j
public class LimitRuleHandler extends RuleHandler {
    @Override
    public void apply(Context context) {
        log.info("LimitRuleHandler==========");
        int remainedTimes = 5;
        if (remainedTimes > 0) {
            log.info("this.getSuccessor()=========="+this.getSuccessor());
            if (this.getSuccessor() != null) {
                this.getSuccessor().apply(context);
            }
        } else {
            throw new RuntimeException("您来得太晚了，奖品被领完了");
        }
    }
}
