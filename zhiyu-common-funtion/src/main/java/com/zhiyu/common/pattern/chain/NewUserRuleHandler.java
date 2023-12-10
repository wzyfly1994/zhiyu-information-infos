package com.zhiyu.common.pattern.chain;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wengzhiyu
 * @date 2020/12/31
 */
@Slf4j
public class NewUserRuleHandler extends  RuleHandler {

    @Override
    public void apply(Context context) {
        log.info("NewUserRuleHandler==========");
        
        if (context.isNewUser()) {
            // 如果有后继节点的话，传递下去
            log.info("this.getSuccessor()=========="+this.getSuccessor());
            if (this.getSuccessor() != null) {
                this.getSuccessor().apply(context);
            }
        } else {
            throw new RuntimeException("该活动仅限新用户参与");
        }
    }
}
