package cn.xiaomeng.httpdog;

import java.lang.reflect.Type;

import okhttp3.Headers;
import okhttp3.Response;

/**
 * 类名：BaseHttpRequestCallback
 * 编辑时间：2018/4/4
 * 编辑人：崔婧
 * 简介：请求回调类
 */
public class BaseHttpRequestCallback<T> {

    //public static final int ERROR_RESPONSE_NULL = 1001;
    public static final int ERROR_RESPONSE_DATA_PARSE_EXCEPTION = 1002;
    public static final int ERROR_RESPONSE_UNKNOWN = 1003;
    //public static final int ERROR_RESPONSE_TIMEOUT = 1004;

    protected Type type;
    protected Headers headers;

    public BaseHttpRequestCallback() {
        type = ClassTypeReflect.getModelClazz(getClass());
    }

    public void onStart() {
    }

    public void onResponse(Response httpResponse, String response, Headers headers) {

    }

    public void onResponse(String response, Headers headers) {
    }

    public void onFinish() {
    }

    protected void onSuccess(Headers headers, T t) {
    }

    protected void onSuccess(T t) {

    }

    /**
     * 上传文件进度
     *
     * @param progress     进度
     * @param networkSpeed 网速
     * @param done         完成
     */
    public void onProgress(int progress, long networkSpeed, boolean done) {
    }

    public void onFailure(int errorCode, String msg) {
    }

    public Headers getHeaders() {
        return headers;
    }

    protected void setResponseHeaders(Headers headers) {
        this.headers = headers;
    }


}
