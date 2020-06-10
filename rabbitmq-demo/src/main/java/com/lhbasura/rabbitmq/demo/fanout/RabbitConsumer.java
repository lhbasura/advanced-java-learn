package com.lhbasura.rabbitmq.demo.fanout;

import com.lhbasura.rabbitmq.demo.Util;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeoutException;

/**
 * @author asura
 * @date 2020/6/9 10:19
 */
public class RabbitConsumer {
    private final static String EXCHANGE_NAME="exchange_dirct";
    public static void main(String[]args) throws IOException, TimeoutException {
        String name= ManagementFactory.getRuntimeMXBean().getName();
        String pid = name.split("@")[0];
        ConnectionFactory factory = Util.getConnectionFactory();
        Connection connection=factory.newConnection();
        Channel channel=connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        String queueName=channel.queueDeclare().getQueue();
        channel.queueBind(queueName,EXCHANGE_NAME,"");
        System.out.println(String.format("%s已创建,正在等待消息......",pid));
        Consumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Customer Received '" + message + "' ："+envelope.getDeliveryTag() );
                // 手动签收   1消息id   2 是否确认签收
                channel.basicAck(envelope.getDeliveryTag(), true);
                try {
                    // 模拟消费等待
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        channel.basicConsume(queueName,false,consumer);
    }
}
