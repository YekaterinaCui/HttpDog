package cn.xiaomeng.httpdog;

import okhttp3.Headers;
import okhttp3.Response;

/**
 * 类名：ResponseData
 * 编辑时间：2018/4/4
 * 编辑人：崔婧
 * 简介：Http响应内容
 */
class ResponseData {

    private boolean responseNull;//http是否无响应
    private boolean timeout;//是否请求超时

    private int code;//http code
    private String message; //http响应消息
    private String response;//http响应结果
    private boolean success;//是否成功
    private Headers headers;//http headers
    private Response httpResponse;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Headers getHeaders() {
        return headers;
    }

    public void setHeaders(Headers headers) {
        this.headers = headers;
    }

    public boolean isResponseNull() {
        return responseNull;
    }

    public void setResponseNull(boolean responseNull) {
        this.responseNull = responseNull;
    }

    public boolean isTimeout() {
        return timeout;
    }

    public void setTimeout(boolean timeout) {
        this.timeout = timeout;
    }

    public Response getHttpResponse() {
        return httpResponse;
    }

    public void setHttpResponse(Response httpResponse) {
        this.httpResponse = httpResponse;
    }
}
