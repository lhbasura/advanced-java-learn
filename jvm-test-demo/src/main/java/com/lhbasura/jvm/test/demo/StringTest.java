package com.lhbasura.jvm.test.demo;


public class StringTest {


    /**
     * intern()方法先检查这个常量是否存在于常量池所在内存区域
     * 如果存在则返回值否则在常量池中创建该常量
     * java1.7之前：
     * String a=new String("ab")+ new String("c");常量池没有创建"abc"常量
     * a.intern()
     * java1.7之前常量池在方法区中，常量池所在内存区域没有"abc"常量,所以在方法区常量池中创建常量
     * b="abc"将b指向常量池中的"abc"
     * String b="abc"相当于将常量池的引用赋值给
     * 此时a指向为堆中的值，b指向方法区常量池中的值a==b为false
     * java1.7之后：
     * String a=new String("ab")+ new String("c");常量池没有创建"abc"常量
     * a.intern()
     * 在java1.7后常量池在堆中，常量池所在区域存在"abc",所以返回a的引用
     * String b="abc"相当于将常量池的引用赋值给b
     * 所以a==b为true
     */
    public static void main(String[] args) {
        String a = new String("ab") + new String("c");
        a.intern();
        String b = "abc";
        System.out.println(b == a);
    }
}
