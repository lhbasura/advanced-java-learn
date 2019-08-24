package com.lhbasura.collection.demo;

import com.lhbasura.collection.demo.map.School;
import com.lhbasura.collection.demo.map.User;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapDemo {
    public static void main(String[] args) {
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
}
