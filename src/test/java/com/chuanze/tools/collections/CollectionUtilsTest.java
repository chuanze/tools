package com.chuanze.tools.collections;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: CollectionUtils 方法测试
 * @Author A05155
 * @Date 2020-4-1 14:23
 * @Version V1.0
 **/
public class CollectionUtilsTest {
    List<String> list1 = Arrays.asList("mick", "jim", "june");
    List<String> list2 = Arrays.asList("张三", "李四", "王五");

    /**
     * 往一个指定不为空的集合新增不为空的元素
     * 如果集合有新增到，则返回true，否则返回false
     * 如果集合为null或者新增的元素有null，则抛出异常
     */
    @Test
    public void addAll() {
        List<String> list = new ArrayList<>();
        CollectionUtils.addAll(list, "a", "b");
        list.forEach(System.out::println);
    }
}
