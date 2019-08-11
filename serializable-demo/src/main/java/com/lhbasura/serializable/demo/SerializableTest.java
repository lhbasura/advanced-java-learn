package com.lhbasura.serializable.demo;

import com.lhbasura.serializable.demo.model.User;
import org.junit.Test;

import java.io.*;

public class SerializableTest {


    @Test
    public  void serialize() throws IOException {
        FileOutputStream fos=new FileOutputStream("object.txt");
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        User user=new User();
        user.setAge(18);
        user.setName("lhbasura");
        user.setEmail("lhbasura@gamil.com");
        oos.writeObject(user);
        oos.close();
    }
    @Test
    public  void unserialize() throws IOException, ClassNotFoundException {
        FileInputStream fis=new FileInputStream("object.txt");
        ObjectInputStream ois=new ObjectInputStream(fis);
        User user=(User)ois.readObject();
        System.out.println(user);
        ois.close();

    }


}
