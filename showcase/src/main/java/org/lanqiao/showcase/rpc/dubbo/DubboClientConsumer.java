package org.lanqiao.showcase.rpc.dubbo;

public class DubboClientConsumer {
  private HelloService helloService;

  public void setHelloService(HelloService helloService) {
    this.helloService = helloService;
  }

  public String saySomething(){
    return helloService.greet()+"，拼接客户端字符串";
  }
}
