package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DemoApplication {
	public static void main(String[]args) throws IOException {
		Test1 test1 = Test1.getIns();
		Test2 test2 = Test2.getIns();
		System.out.println("test2.test1:"+test2.test1);
		System.out.println("test1.test2:"+test1.test2);
		test1.doTest();
	}

}
