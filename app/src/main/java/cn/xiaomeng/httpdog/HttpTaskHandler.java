package cn.xiaomeng.httpdog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 类名：HttpTaskHandler
 * 编辑时间：2018/4/4
 * 编辑人：崔婧
 * 简介：Http请求辅助类，用于处理UI已经销毁，但后台线程还在进行的问题；
 */
public class HttpTaskHandler {

    //正在请求的任务集合
    private static Map<String, List<OkHttpTask>> httpTaskMap;

    //单例请求处理器
    private static HttpTaskHandler httpTaskHandler = null;

    private HttpTaskHandler() {
        httpTaskMap = new ConcurrentHashMap<>();
    }

    /**
     * 获得处理器实例
     *
     * @return 任务处理器实例
     */
    public static HttpTaskHandler getInstance() {
        if (httpTaskHandler == null) {
            httpTaskHandler = new HttpTaskHandler();
        }
        return httpTaskHandler;
    }

    /**
     * 移除KEY
     *
     * @param key 任务名
     */
    public void removeTask(String key) {
        if (httpTaskMap.containsKey(key)) {
            //移除对应的Key
            httpTaskMap.remove(key);
        }
    }

    /**
     * 将请求放到Map里面
     *
     * @param key  任务名
     * @param task 任务
     */
    void addTask(String key, OkHttpTask task) {
        if (httpTaskMap.containsKey(key)) {
            List<OkHttpTask> tasks = httpTaskMap.get(key);
            tasks.add(task);
            httpTaskMap.put(key, tasks);
        } else {
            List<OkHttpTask> tasks = new ArrayList<>();
            tasks.add(task);
            httpTaskMap.put(key, tasks);
        }
    }

    /**
     * 判断任务是否存在
     *
     * @param key 任务名
     * @return
     */
    boolean contains(String key) {
        return httpTaskMap.containsKey(key);
    }
}
