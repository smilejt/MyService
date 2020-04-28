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

    /**
     * corePoolSize ： 线程池核心池的大小
     * maximumPoolSize ： 线程池的最大线程数
     * keepAliveTime ： 当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间
     * unit ： keepAliveTime 的时间单位
     * workQueue ： 用来储存等待执行任务的队列
     * threadFactory ： 线程工厂
     * handler  拒绝策略
     */
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
