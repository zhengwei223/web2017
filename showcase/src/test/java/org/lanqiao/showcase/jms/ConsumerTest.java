package org.lanqiao.showcase.jms;

import org.lanqiao.showcase.email.Mail;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class ConsumerTest {

  public static void main(String[] args) throws IOException {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("jms/applicationContext-jms-consumer.xml");
    // System.in.read();
  }
}