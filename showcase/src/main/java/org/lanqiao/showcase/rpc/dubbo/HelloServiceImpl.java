package org.lanqiao.showcase.rpc.dubbo;

public class HelloServiceImpl implements HelloService {
  @Override
  public String greet() {
    return "hello";
  }
}
