package cn.xiaomeng.httpdog;

import java.util.concurrent.ConcurrentHashMap;

import cn.xiaomeng.httpdog.tools.StringUtils;
import okhttp3.Call;

class OkHttpCallManager {

    private ConcurrentHashMap<String, Call> callMap;
    private static cn.xiaomeng.httpdog.OkHttpCallManager manager;

    private OkHttpCallManager() {
        callMap = new ConcurrentHashMap<>();
    }

    public static cn.xiaomeng.httpdog.OkHttpCallManager getInstance() {
        if (manager == null) {
            manager = new cn.xiaomeng.httpdog.OkHttpCallManager();
        }
        return manager;
    }

    public void addCall(String url, Call call) {
        if (call != null && !StringUtils.isEmpty(url)) {
            callMap.put(url, call);
        }
    }

    public Call getCall(String url) {
        if ( !StringUtils.isEmpty(url) ) {
            return callMap.get(url);
        }

        return null;
    }

    public void removeCall(String url) {
        if ( !StringUtils.isEmpty(url) ) {
            callMap.remove(url);
        }
    }

}
