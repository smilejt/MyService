package cn.laoshengle.core.utils;

import com.google.common.collect.Maps;
import org.apache.commons.compress.utils.Lists;
import org.springframework.cglib.beans.BeanMap;

import java.util.List;
import java.util.Map;

/**
 * @description: Bean对象转换相关帮助类
 * @author: 龙逸
 * @createDate: 2020/05/17 17:14:29
 **/
public class BeanUtil {

    /**
     * 将对象装换为map
     *
     * @param bean 要转换的Bean对象
     * @return 转换后的Map对象
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = Maps.newHashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key + "", beanMap.get(key));
            }
        }
        return map;
    }

    /**
     * 将map装换为javabean对象
     *
     * @param map  要转换的Map对象
     * @param bean 要转换的Bean对象
     */
    public static <T> void mapToBean(Map<String, Object> map, T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
    }

    /**
     * 将List<T>转换为List<Map<String, Object>>
     *
     * @param objList 要转换的存储Bean的List对象
     * @return 转换后存储Map的List
     */
    public static <T> List<Map<String, Object>> objectsToMaps(List<T> objList) {
        List<Map<String, Object>> list = Lists.newArrayList();
        if (objList != null && objList.size() > 0) {
            Map<String, Object> map = null;
            T bean = null;
            for (T t : objList) {
                bean = t;
                map = beanToMap(bean);
                list.add(map);
            }
        }
        return list;
    }

    /**
     * 将List<Map<String,Object>>转换为List<T>
     *
     * @param maps  要转换的存储Map的List
     * @param clazz 要转换的Bean.class
     * @return 转换后的List
     */
    public static <T> List<T> mapsToObjects(List<Map<String, Object>> maps, Class<T> clazz)
            throws InstantiationException, IllegalAccessException {
        List<T> list = Lists.newArrayList();
        if (maps != null && maps.size() > 0) {
            Map<String, Object> map = null;
            T bean = null;
            for (Map<String, Object> objectMap : maps) {
                map = objectMap;
                bean = clazz.newInstance();
                mapToBean(map, bean);
                list.add(bean);
            }
        }
        return list;
    }


}
