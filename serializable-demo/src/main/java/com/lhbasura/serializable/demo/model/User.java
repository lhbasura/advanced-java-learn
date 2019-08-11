package com.lhbasura.serializable.demo.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    /*
    序列化无法序列化static以及被transient关键字修饰的变量
    serialVersionUID静态常量用于表示唯一序列化对象，若不做该变量的声明则当序列化类改变时
    反序列化过程中找不到该类
     */
    private static final long serialVersionUID = 1386583756403881124L;
    static String planet="earth";

    private String name;
    private String email;
    private transient int age;
}
