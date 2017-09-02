package org.lanqiao.showcase.rpc.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class DubboServer {
  public static void main(String[] args) throws IOException {
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("rpc/dubbo-server-context.xml");
    applicationContext.start();
    System.out.println("按任意键退出");
    System.in.read();// 任意键退出
  }
}
