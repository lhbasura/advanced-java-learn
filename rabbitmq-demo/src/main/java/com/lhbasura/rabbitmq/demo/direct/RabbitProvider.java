package com.lhbasura.rabbitmq.demo.direct;

import com.rabbitmq.client.ConnectionFactory;

/**
 * @author asura
 * @date 2020/6/9 12:06
 * @description
 */
public class RabbitProvider {
    private final static String EXCHANGE_NAME="exchange_direct";
    public static void main(String[]args){
        ConnectionFactory connectionFactory = new ConnectionFactory();
    }
}
