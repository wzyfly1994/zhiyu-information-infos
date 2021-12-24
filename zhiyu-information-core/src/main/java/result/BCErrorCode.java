package result;

/**
 * @author wengzhiyu
 * @date 2020/01/07
 */
public enum BCErrorCode {
    /**
     * 异常状态
     */
    SUCCESS("成功", 0),
    ERROR("失败", -1),
    ;


    private String msg;
    private Integer code;

    BCErrorCode(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }
}
