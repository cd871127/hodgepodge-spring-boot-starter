package io.github.cd871127.hodgepodge.common;

public class ServerResponse<T> {
    private String code;
    private String msg;
    private String token;
    private T data;

    public ServerResponse() {
    }

    public ServerResponse(RequestResult requestResult) {
        this();
        setResult(requestResult);
    }

    public void setResult(RequestResult requestResult) {
        this.code = requestResult.getCode();
        this.msg = requestResult.getMsg();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public enum RequestResult {

        ERROR("999999","操作失败"),
        SUCCESS("000000", "操作成功"),
        //users
        USER_NOT_EXIST("000101","用户不存在"),
        PASSWORD_ERROR("000102","密码错误"),
        INVALID_USER("000103", "无效的用户"),

        //file
        FILE_TOO_LARGE("000201", "文件过大"),
        EMPTY_FILE_NAME("000202", "文件名为空"),
        UPLOAD_FAILED("000203", "文件上传失败"),

        NO_TOKEN("000104", "用户未登录"), USER_EXISTED("002", "用户名已被注册"), ADD_USER_ERROR("003", "插入用户失败"),
        INVALID_TOKEN("004", "无效的token"), LOGIN_FAILED("005", "用户不存在或密码错误"), OPERATION_FAILED("006", "操作失败")
        ;

        private String code;
        private String msg;

        public String getCode() {
            return code;
        }

        public void setValue(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        RequestResult(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }
}
