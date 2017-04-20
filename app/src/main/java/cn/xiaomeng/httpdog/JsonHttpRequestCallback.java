package cn.xiaomeng.httpdog;

import com.alibaba.fastjson.JSONObject;

public class JsonHttpRequestCallback extends BaseHttpRequestCallback<JSONObject> {

    public JsonHttpRequestCallback() {
        super();
        type = JSONObject.class;
    }
}
