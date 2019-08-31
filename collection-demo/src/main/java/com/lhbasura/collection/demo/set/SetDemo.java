package com.lhbasura.collection.demo.set;

import java.util.*;

public class SetDemo {
    public static void main(String[] args) {
        Set set =new HashSet();
        for(int i=0;i<100;i++)
        {
            new Thread(()->{
                Map<String, Integer> map = new HashMap<>();
                map.put("a",2);
                set.add(map);
            }).start();
        }
        System.out.println(set);
    }
}
