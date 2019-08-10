package com.lhbasura.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.exit(0);
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        List<String> strings =Arrays.asList("this","is","a","string");
        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
        System.out.println("平方：" + numbers.stream().map((x)->{
            return x*x;
        }).collect(Collectors.toList()));
        System.out.println("平方小于5的：" + numbers.stream().map((x)->{ return x*x; }).filter((x)->{return x<5;}).collect(Collectors.toList()));

        System.out.println(strings.stream().collect(Collectors.joining(" ","{","}")));
    }

}
