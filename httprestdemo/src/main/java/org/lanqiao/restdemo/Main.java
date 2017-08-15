package org.lanqiao.restdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@Configuration
//@ComponentScan
//@EnableAutoConfiguration // 启动自动配置
@SpringBootApplication // 等价于上面三行
public class Main  {
  public static void main(String[] args) {
    SpringApplication.run( Main.class,args );
  }
}
