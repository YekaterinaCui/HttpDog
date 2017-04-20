package cn.xiaomeng.httpdog;

import com.alibaba.fastjson.JSONArray;

public class JsonArrayHttpRequestCallback extends BaseHttpRequestCallback<JSONArray> {

    public JsonArrayHttpRequestCallback() {
        super();
        type = JSONArray.class;
    }
}
