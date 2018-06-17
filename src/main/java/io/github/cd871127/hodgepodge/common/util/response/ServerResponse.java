package io.github.cd871127.hodgepodge.common.util.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "返回响应数据")
public class ServerResponse<T> {
    @ApiModelProperty(value = "响应代码")
    private String code;
    @ApiModelProperty(value = "响应消息")
    private String msg;
    @ApiModelProperty(value = "响应体", allowEmptyValue = true)
    private T data;

    public ServerResponse(ResponseCode responseCode) {
        setResponseCode(responseCode);
    }

    public ServerResponse() {

    }

    public void setResponseCode(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
