package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[]args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("test.txt")));
		String line;
		while ((line=bufferedReader.readLine())!=null){
			System.out.println(line);
		}
	}

}
