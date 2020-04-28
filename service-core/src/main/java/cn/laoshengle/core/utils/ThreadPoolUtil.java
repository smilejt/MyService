package cn.laoshengle.core.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @description: 符合阿里规范的线程池
 * @author: 龙逸
 * @createDate: 2020/04/28 16:33:03
 **/
public class ThreadPoolUtil {

    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("test-pool-%d").build();

    private static ExecutorService service = new ThreadPoolExecutor(1, 5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    /**
     * 获取线程池
     *
     * @return 线程池
     */
    public static ExecutorService getEs() {
        return service;
    }

    /**
     * 使用线程池创建线程并异步执行任务
     *
     * @param r 任务
     */
    public static void newTask(Runnable r) {
        service.execute(r);
    }
}
