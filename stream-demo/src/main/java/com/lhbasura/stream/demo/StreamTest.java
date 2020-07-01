package com.lhbasura.stream.demo;

import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
 * @author asura
 * @date 2020/7/1 17:38
 * @description java8流式语法
 */

public class StreamTest {
    @Test
    public void testCaculator() {
        System.exit(0);
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        List<String> strings = Arrays.asList("this", "is", "a", "string");
        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
        System.out.println("平方：" + numbers.stream().map((x) -> {
            return x * x;
        }).collect(Collectors.toList()));
        System.out.println("平方小于5的：" + numbers.stream().map((x) -> {
            return x * x;
        }).filter((x) -> {
            return x < 5;
        }).collect(Collectors.toList()));

        System.out.println(strings.stream().collect(Collectors.joining(" ", "{", "}")));
    }

    //按每3个一组分割
    private static final Integer MAX_SEND = 3;

    @Test
    public void testLimit() {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        int limit = countStep(list.size());
        //方法一：使用流遍历操作
        List<List<Integer>> mglist = new ArrayList<>();
        Stream.iterate(0, n -> n + 1).limit(limit).forEach(i -> {
            mglist.add(list.stream().skip(i * MAX_SEND).limit(MAX_SEND).collect(Collectors.toList()));
        });

        System.out.println(mglist);
        //方法二：获取分割后的集合
        List<List<Integer>> splitList = Stream.iterate(0, n -> n + 1).limit(limit).parallel().map(a -> list.stream().skip(a * MAX_SEND).limit(MAX_SEND).parallel().collect(Collectors.toList())).collect(Collectors.toList());

        System.out.println(splitList);
    }

    /**
     * 计算切分次数
     */
    private static Integer countStep(Integer size) {
        return (size + MAX_SEND - 1) / MAX_SEND;
    }

    @Test
    public void testGuava() {
        DateTime date = new DateTime().withTimeAtStartOfDay();
        Date endDate = date.toDate();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        System.out.println(simpleDateFormat.format(endDate));

        List<String> strs = Arrays.asList(new String[]{"a", "b", "c", "d"});
        List<List<String>> parts = Lists.partition(strs, 2);
        parts.stream().forEach(System.out::println);
    }

}
