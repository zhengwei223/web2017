package org.lanqiao.showcase.rpc.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class DubboClient {
  public static void main(String[] args) throws IOException {
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("rpc/dubbo-client-context.xml");
    applicationContext.start();

    DubboClientConsumer consumer = (DubboClientConsumer) applicationContext.getBean("consumer");
    System.out.println(consumer.saySomething());

  }
}
