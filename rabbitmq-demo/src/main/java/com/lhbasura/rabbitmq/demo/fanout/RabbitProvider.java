package com.lhbasura.rabbitmq.demo.fanout;

import com.lhbasura.rabbitmq.demo.Util;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

/**
 * @author asura
 * @date 2020/6/8 18:04
 */
public class RabbitProvider {


    private final static String EXCHANGE_NAME = "direct_fanout";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory= Util.getConnectionFactory();

        // 创建connection
        Connection conn = factory.newConnection();
        // 创建channel
        Channel channel = conn.createChannel();
        // 声明该channel是fanout类型
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        Date nowDate = new Date();
        String msg = nowDate.getTime() + " have log ...";
        // 将消息发送给exchange
        channel.basicPublish("", "directqueue", null, msg.getBytes());
        System.out.println(nowDate + " 已经生成一条日志...");

        channel.close();
        conn.close();
    }

}
