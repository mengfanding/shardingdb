package com.example.shardingjdbc.config;

/**
 * 用线程控制当前选择的库
 *
 * @author Meng
 */
public class HandlerDataSource {

    private static ThreadLocal<String> handlerThredLocal = new ThreadLocal<String>();

    public static void putDataSource(String datasource) {
        handlerThredLocal.set(datasource);
    }

    public static String getDataSource() {
        return handlerThredLocal.get();
    }

    public static void clear() {
        handlerThredLocal.remove();
    }
}
