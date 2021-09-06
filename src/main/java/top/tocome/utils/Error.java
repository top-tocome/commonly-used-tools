package top.tocome.utils;

public class Error {
    /**
     * 错误消息
     */
    protected String message;

    public Error(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
