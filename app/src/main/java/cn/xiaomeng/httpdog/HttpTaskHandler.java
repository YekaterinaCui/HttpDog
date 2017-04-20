package cn.xiaomeng.httpdog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Desction:Http请求辅助类，用于处理UI已经销毁，但后台线程还在进行的问题；
 */
public class HttpTaskHandler {

    /** 正在请求的任务集合 */
    private static Map<String, List<cn.xiaomeng.httpdog.OkHttpTask>> httpTaskMap;
    /** 单例请求处理器 */
    private static cn.xiaomeng.httpdog.HttpTaskHandler httpTaskHandler = null;

    private HttpTaskHandler() {
        httpTaskMap = new ConcurrentHashMap<>();
    }

    /**
     * 获得处理器实例
     */
    public static cn.xiaomeng.httpdog.HttpTaskHandler getInstance() {
        if (httpTaskHandler == null) {
            httpTaskHandler = new cn.xiaomeng.httpdog.HttpTaskHandler();
        }
        return httpTaskHandler;
    }

    /**
     * 移除KEY
     * @param key
     */
    public void removeTask(String key) {
        if (httpTaskMap.containsKey(key)) {
            //移除对应的Key
            httpTaskMap.remove(key);
        }
    }

    /**
     * 将请求放到Map里面
     * @param key
     * @param task
     */
    void addTask(String key, cn.xiaomeng.httpdog.OkHttpTask task) {
        if (httpTaskMap.containsKey(key)) {
            List<cn.xiaomeng.httpdog.OkHttpTask> tasks = httpTaskMap.get(key);
            tasks.add(task);
            httpTaskMap.put(key, tasks);
        } else {
            List<cn.xiaomeng.httpdog.OkHttpTask> tasks = new ArrayList<>();
            tasks.add(task);
            httpTaskMap.put(key, tasks);
        }
    }

    /**
     * 判断是否存在
     * @param key
     * @return
     */
    boolean contains(String key) {
        return httpTaskMap.containsKey(key);
    }
}
