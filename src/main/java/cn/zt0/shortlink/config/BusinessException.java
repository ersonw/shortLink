package cn.zt0.shortlink.config;

public class BusinessException extends RuntimeException {

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
    public BusinessException(Integer code,String message) {
        super(message);
        this.code = code;
    }
    public BusinessException(String message) {
        super(message);
    }
}
