package cn.xiaomeng.httpdog;

import java.io.File;
import java.util.concurrent.TimeUnit;

import cn.xiaomeng.httpdog.tools.StringUtils;
import okhttp3.Call;
import okhttp3.OkHttpClient;

/**
 * Desction:http请求类
 */
public final class HttpRequest {

    public static void get(String url) {
        get(url, null, null);
    }

    public static void get(String url, RequestParams params) {
        get(url, params, null);
    }

    public static void get(String url, BaseHttpRequestCallback callback) {
        get(url, null, callback);
    }

    /**
     * Get请求
     * @param url
     * @param params
     * @param callback
     */
    public static void get(String url, RequestParams params, BaseHttpRequestCallback callback) {
        get(url, params, cn.xiaomeng.httpdog.Constants.REQ_TIMEOUT, callback);
    }

    public static void get(String url, RequestParams params, long timeout, BaseHttpRequestCallback callback) {
        OkHttpClient.Builder builder = HttpDog.getInstance().getOkHttpClientBuilder();
        builder.readTimeout(timeout, TimeUnit.MILLISECONDS);
        builder.connectTimeout(timeout, TimeUnit.MILLISECONDS);
        builder.writeTimeout(timeout, TimeUnit.MILLISECONDS);
        executeRequest(cn.xiaomeng.httpdog.Method.GET, url, params, builder, callback);
    }

    public static void get(String url, RequestParams params, OkHttpClient.Builder builder, BaseHttpRequestCallback callback) {
        executeRequest(cn.xiaomeng.httpdog.Method.GET, url, params, builder, callback);
    }

    public static void post(String url) {
        post(url, null, null);
    }

    public static void post(String url, RequestParams params) {
        post(url, params, null);
    }

    public static void post(String url, BaseHttpRequestCallback callback) {
        post(url, null, callback);
    }

    /**
     * Post请求
     * @param url
     * @param params
     * @param callback
     */
    public static void post(String url, RequestParams params, BaseHttpRequestCallback callback) {
        post(url, params, cn.xiaomeng.httpdog.Constants.REQ_TIMEOUT, callback);
    }

    public static void post(String url, RequestParams params, long timeout, BaseHttpRequestCallback callback) {
        OkHttpClient.Builder builder = HttpDog.getInstance().getOkHttpClientBuilder();
        builder.readTimeout(timeout, TimeUnit.MILLISECONDS);
        builder.connectTimeout(timeout, TimeUnit.MILLISECONDS);
        builder.writeTimeout(timeout, TimeUnit.MILLISECONDS);
        executeRequest(cn.xiaomeng.httpdog.Method.POST, url, params, builder, callback);
    }

    public static void post(String url, RequestParams params, OkHttpClient.Builder builder, BaseHttpRequestCallback callback) {
        executeRequest(cn.xiaomeng.httpdog.Method.POST, url, params, builder, callback);
    }

    public static void put(String url) {
        put(url, null, null);
    }

    public static void put(String url, RequestParams params) {
        put(url, params, null);
    }

    public static void put(String url, BaseHttpRequestCallback callback) {
        put(url, null, callback);
    }

    /**
     * put请求
     * @param url
     * @param params
     * @param callback
     */
    public static void put(String url, RequestParams params, BaseHttpRequestCallback callback) {
        put(url, params, cn.xiaomeng.httpdog.Constants.REQ_TIMEOUT, callback);
    }

    public static void put(String url, RequestParams params, long timeout, BaseHttpRequestCallback callback) {
        OkHttpClient.Builder builder = HttpDog.getInstance().getOkHttpClientBuilder();
        builder.readTimeout(timeout, TimeUnit.MILLISECONDS);
        builder.connectTimeout(timeout, TimeUnit.MILLISECONDS);
        builder.writeTimeout(timeout, TimeUnit.MILLISECONDS);
        executeRequest(cn.xiaomeng.httpdog.Method.PUT, url, params, builder, callback);
    }

    public static void put(String url, RequestParams params, OkHttpClient.Builder builder, BaseHttpRequestCallback callback) {
        executeRequest(cn.xiaomeng.httpdog.Method.PUT, url, params, builder, callback);
    }

    public static void delete(String url) {
        delete(url, null, null);
    }

    public static void delete(String url, RequestParams params) {
        delete(url, params, null);
    }

    public static void delete(String url, BaseHttpRequestCallback callback) {
        delete(url, null, callback);
    }

    /**
     * delete请求
     * @param url
     * @param params
     * @param callback
     */
    public static void delete(String url, RequestParams params, BaseHttpRequestCallback callback) {
        delete(url, params, cn.xiaomeng.httpdog.Constants.REQ_TIMEOUT, callback);
    }

    public static void delete(String url, RequestParams params, long timeout, BaseHttpRequestCallback callback) {
        OkHttpClient.Builder builder = HttpDog.getInstance().getOkHttpClientBuilder();
        builder.readTimeout(timeout, TimeUnit.MILLISECONDS);
        builder.connectTimeout(timeout, TimeUnit.MILLISECONDS);
        builder.writeTimeout(timeout, TimeUnit.MILLISECONDS);
        executeRequest(cn.xiaomeng.httpdog.Method.DELETE, url, params, builder, callback);
    }

    public static void delete(String url, RequestParams params, OkHttpClient.Builder builder, BaseHttpRequestCallback callback) {
        executeRequest(cn.xiaomeng.httpdog.Method.DELETE, url, params, builder, callback);
    }

    public static void head(String url) {
        head(url, null, null);
    }

    public static void head(String url, RequestParams params) {
        head(url, params, null);
    }

    public static void head(String url, BaseHttpRequestCallback callback) {
        head(url, null, callback);
    }

    /**
     * head请求
     * @param url
     * @param params
     * @param callback
     */
    public static void head(String url, RequestParams params, BaseHttpRequestCallback callback) {
        head(url, params, cn.xiaomeng.httpdog.Constants.REQ_TIMEOUT, callback);
    }

    public static void head(String url, RequestParams params, long timeout, BaseHttpRequestCallback callback) {
        OkHttpClient.Builder builder = HttpDog.getInstance().getOkHttpClientBuilder();
        builder.readTimeout(timeout, TimeUnit.MILLISECONDS);
        builder.connectTimeout(timeout, TimeUnit.MILLISECONDS);
        builder.writeTimeout(timeout, TimeUnit.MILLISECONDS);
        executeRequest(cn.xiaomeng.httpdog.Method.HEAD, url, params, builder, callback);
    }

    public static void head(String url, RequestParams params, OkHttpClient.Builder builder, BaseHttpRequestCallback callback) {
        executeRequest(cn.xiaomeng.httpdog.Method.HEAD, url, params, builder, callback);
    }

    public static void patch(String url) {
        patch(url, null, null);
    }

    public static void patch(String url, RequestParams params) {
        patch(url, params, null);
    }

    public static void patch(String url, BaseHttpRequestCallback callback) {
        patch(url, null, callback);
    }

    /**
     * patch请求
     * @param url
     * @param params
     * @param callback
     */
    public static void patch(String url, RequestParams params, BaseHttpRequestCallback callback) {
        patch(url, params, cn.xiaomeng.httpdog.Constants.REQ_TIMEOUT, callback);
    }

    public static void patch(String url, RequestParams params, long timeout, BaseHttpRequestCallback callback) {
        OkHttpClient.Builder builder = HttpDog.getInstance().getOkHttpClientBuilder();
        builder.readTimeout(timeout, TimeUnit.MILLISECONDS);
        builder.connectTimeout(timeout, TimeUnit.MILLISECONDS);
        builder.writeTimeout(timeout, TimeUnit.MILLISECONDS);
        executeRequest(cn.xiaomeng.httpdog.Method.PATCH, url, params, builder, callback);
    }

    public static void patch(String url, RequestParams params, OkHttpClient.Builder builder, BaseHttpRequestCallback callback) {
        executeRequest(cn.xiaomeng.httpdog.Method.PATCH, url, params, builder, callback);
    }

    /**
     * 取消请求
     * @param url
     */
    public static void cancel(String url) {
        if ( !StringUtils.isEmpty(url) ) {
            Call call = cn.xiaomeng.httpdog.OkHttpCallManager.getInstance().getCall(url);
            if ( call != null ) {
                call.cancel();
            }

            cn.xiaomeng.httpdog.OkHttpCallManager.getInstance().removeCall(url);
        }
    }

    public static void download(String url, File target) {
        download(url, target, null);
    }

    /**
     * 下载文件
     * @param url
     * @param target 保存的文件
     * @param callback
     */
    public static void download(String url, File target, FileDownloadCallback callback) {
        if (!StringUtils.isEmpty(url) && target != null) {
            cn.xiaomeng.httpdog.FileDownloadTask task = new cn.xiaomeng.httpdog.FileDownloadTask(url, target, callback);
            task.execute();
        }
    }

    private static void executeRequest(cn.xiaomeng.httpdog.Method method, String url, RequestParams params, OkHttpClient.Builder builder, BaseHttpRequestCallback callback) {
        if (!StringUtils.isEmpty(url)) {
            if(builder == null) {
                builder = HttpDog.getInstance().getOkHttpClientBuilder();
            }
            cn.xiaomeng.httpdog.OkHttpTask task = new cn.xiaomeng.httpdog.OkHttpTask(method, url, params, builder, callback);
            task.execute();
        }
    }

}
