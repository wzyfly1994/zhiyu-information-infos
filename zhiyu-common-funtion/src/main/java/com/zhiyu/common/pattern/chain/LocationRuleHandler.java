package com.zhiyu.common.pattern.chain;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wengzhiyu
 * @date 2020/12/31
 */
@Slf4j
public  class LocationRuleHandler extends RuleHandler {

    @Override
    public void apply(Context context) {
        log.info("LocationRuleHandler==========");
        boolean allowed = true;
        if (allowed) {
            log.info("this.getSuccessor()=========="+this.getSuccessor());
            if (this.getSuccessor() != null) {
                this.getSuccessor().apply(context);
            }
        } else {
            throw new RuntimeException("非常抱歉，您所在的地区无法参与本次活动");
        }
    }
}
