package com.zhiyu.common.pattern.chain;

/**
 * 设计模式：责任链模式
 *
 * @author wengzhiyu
 * @date 2020/12/31
 */
public abstract class RuleHandler {
    /**
     * RuleHandler
     */
    private RuleHandler successor;

    public abstract void apply(Context context);

    public void setSuccessor(RuleHandler successor) {
        this.successor = successor;
    }

    public RuleHandler getSuccessor() {
        return successor;
    }


    public static void main(String[] args) {
        RuleHandler newUserHandler = new NewUserRuleHandler();
        RuleHandler locationHandler = new LocationRuleHandler();
        RuleHandler limitHandler = new LimitRuleHandler();
        Context context = new Context();
        context.setNewUser(true);

        //链表式运行
        locationHandler.setSuccessor(limitHandler);
        limitHandler.setSuccessor(newUserHandler);
        locationHandler.apply(context);
    }
}
