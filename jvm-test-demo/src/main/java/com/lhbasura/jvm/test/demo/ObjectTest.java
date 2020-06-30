package com.lhbasura.jvm.test.demo;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author lhb
 * @version ObjectTest, v 1.0 2020/3/7 10:23 上午
 * @desc jvm对象存储
 */
public class ObjectTest {
    public static void main(String[] args) {
        ObjectTest test=new ObjectTest();
        System.out.println(test.canMeasureWater(1,0,0));
    }
    int gcd(int x,int y){
        if(x<y){
            int temp=x;
            x=y;
            y=temp;
        }
        while(x%y!=0){
            int temp=y;
            y=x%y;
            x=temp;
        }
        return y;
    }
    public boolean canMeasureWater(int x, int y, int z) {
        if(x+y<z)return false;
        if(x==0){
            return z==y;
        }
        if(y==0){
            return z==x;
        }
        int d=gcd(x,y);
        return z%d==0;
    }
}
