package com.lhbasura.collection.demo.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MapDemo {
    @Test
    public  void testMapEqual() {
        User user1 = new User("lhbasura", 22);
        User user2 = new User("lms", 23);
        User user3 = new User("lsm", 23);

        School school = new School("cust");

        Map<User, School> map = new HashMap<User, School>();
        map.put(user1, school);
        map.put(user2, school);
        map.put(user3, school);
        Iterator<Map.Entry<User, School>> iterator = map.entrySet().iterator();

        System.out.println(user1.equals(user2));
        while (iterator.hasNext()) {
            Map.Entry<User, School> entry = iterator.next();
            System.out.println(entry.getKey() + " from " + map.get(entry.getKey()));
        }
    }
    @Test
    public void testLinkedHashMap(){
        Map<String, String> map = new LinkedHashMap<String, String>(16,0.75f,true){
            @Override
            protected boolean removeEldestEntry(Entry<String, String> eldest) {
                return size()>5;
            }
        };
        map.put("apple", "苹果");
        map.put("watermelon", "西瓜");
        map.put("banana", "香蕉");
        map.put("peach", "桃子");
        map.get("banana");
        map.get("apple");
        map.put("1", "桃子");
        map.put("2", "桃子");
        map.put("3", "桃子");



        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }}
}
