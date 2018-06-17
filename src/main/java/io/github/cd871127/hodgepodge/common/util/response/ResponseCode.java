package io.github.cd871127.hodgepodge.common.util.response;

public enum ResponseCode {
	ERROR("999999", "操作失败"),
    SUCCESS("000000", "操作成功"),

	//用户登录相关代码0001xx
	LOGIN_FAILED("000100", "登录失败"),
	NOT_LOGIN("000101", "用户未登录"),
	NEED_VALID_CODE("000102","需要验证码"),
	GET_VALID_CODE_FAILED("000103","获取验证码失败"),
	GET_PUBLIC_KEY_FAILED("000104","获取公钥失败"),
	GET_REQUEST_ID_FAILED("000105","获取请求ID失败"),

	//数据操作相关代码0002xx
    EMPTY_RESULT("000201","结果集为空");


	private String code;
    private String msg;
    
	ResponseCode(String code, String msg){
		this.code = code;
        this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
