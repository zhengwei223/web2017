package org.lanqiao.showcase.rpc.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class DubboClient {
  public static void main(String[] args) throws IOException {
    ClassPathXmlApplicationContext applicationContext =
        new ClassPathXmlApplicationContext("rpc_dubbo/dubbo-client-context.xml");
    applicationContext.start();
    for (int i = 0; i < 10; i++) {

      HelloService service = (HelloService) applicationContext.getBean("helloService");
      System.out.println("=============================="+service.greet());
    }
    System.out.println("按任意键退出");
    System.in.read();// 任意键退出
  }
}
