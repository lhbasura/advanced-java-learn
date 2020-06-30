package com.lhbasura.rabbitmq.demo;

import com.rabbitmq.client.ConnectionFactory;

/**
 * @author asura
 * @date 2020/6/9 17:29
 * @description
 */
public class Util {
   public static ConnectionFactory getConnectionFactory(){
      ConnectionFactory factory = new ConnectionFactory();
      factory.setHost("127.0.0.1");
      factory.setUsername("guest");
      factory.setPassword("guest");
      factory.setPort(5672);
      factory.setVirtualHost("/");
      return  factory;
   }
}
