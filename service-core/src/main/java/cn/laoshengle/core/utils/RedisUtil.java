package cn.laoshengle.core.utils;

import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.codec.SerializationCodec;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * @description: Redis操作Service, 配置信息暂时硬编码,后期优化
 * @author: 龙逸
 * @createDate: 2020/04/29 10:06:21
 **/
@SuppressWarnings("unused")
public class RedisUtil {

    private static final String REDIS_PREFIX = "redis://";

    /**
     * Redis数据库地址
     */
    private static final String REDIS_URL = "94.191.101.181";

    /**
     * Redis数据库索引
     */
    private static final Integer REDIS_DATABASE = 0;

    /**
     * Redis数据库访问密码
     */
    private static final String REDIS_PASSWORD = "2020NewLife";

    /**
     * Redis端口
     */
    private static final Integer REDIS_PORT = 6379;

    private static RedissonClient redissonClient;

    static {
        Config config = new Config();
        config.useSingleServer().setAddress(String.format("%s%s:%s", REDIS_PREFIX, REDIS_URL, REDIS_PORT)).setPassword(REDIS_PASSWORD).setDatabase(REDIS_DATABASE);
        config.setCodec(new SerializationCodec());
        redissonClient = Redisson.create(config);
    }

    //后期根据具体使用添加相应的方法

    /**
     * 存储普通对象
     *
     * @param key      对象的Key
     * @param value    对象类型
     * @param timeUnit 时间单位
     * @param time     时间
     */
    public static <T> void setObject(String key, T value, TimeUnit timeUnit, Long time) {
        RBucket<T> bucket = redissonClient.getBucket(key);
        bucket.set(value, time, timeUnit);
    }

    /**
     * 获取字符串对象
     *
     * @param objectName 对象的Key
     */
    public static <T> RBucket<T> getBucket(String objectName) {
        return redissonClient.getBucket(objectName);
    }

    /**
     * 获取Map对象
     *
     * @param objectName Map的Key
     */
    public <K, V> RMap<K, V> getMap(String objectName) {
        return redissonClient.getMap(objectName);
    }

    /**
     * 获取有序集合
     *
     * @param objectName 有序集合的Key
     */
    public <V> RSortedSet<V> getSortedSet(String objectName) {
        return redissonClient.getSortedSet(objectName);
    }

    /**
     * 获取集合
     *
     * @param objectName 集合的Key
     */
    public <V> RSet<V> getSet(String objectName) {
        return redissonClient.getSet(objectName);
    }

    /**
     * 获取列表
     *
     * @param objectName 列表的Key
     */
    public <V> RList<V> getList(String objectName) {
        return redissonClient.getList(objectName);
    }

    /**
     * 获取队列
     *
     * @param objectName 队列的Key
     */
    public <V> RQueue<V> getQueue(String objectName) {
        return redissonClient.getQueue(objectName);
    }

    /**
     * 获取双端队列
     *
     * @param objectName 双端队列的Key
     */
    public <V> RDeque<V> getDeque(String objectName) {
        return redissonClient.getDeque(objectName);
    }


    /**
     * 获取锁
     *
     * @param objectName 锁的Key
     */
    public RLock getLock(String objectName) {
        return redissonClient.getLock(objectName);
    }

    /**
     * 获取读写锁
     *
     * @param objectName 读写锁的Key
     */
    public RReadWriteLock getReadWriteLock(String objectName) {
        return redissonClient.getReadWriteLock(objectName);
    }

    /**
     * 获取原子数
     *
     * @param objectName 原子数的Key
     */
    public RAtomicLong getAtomicLong(String objectName) {
        return redissonClient.getAtomicLong(objectName);
    }

    /**
     * 获取记数锁
     *
     * @param objectName 计数锁的Key
     */
    public RCountDownLatch getCountDownLatch(String objectName) {
        return redissonClient.getCountDownLatch(objectName);
    }
}
