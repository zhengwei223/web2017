package org.lanqiao.showcase.jms;

import org.lanqiao.showcase.email.Mail;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class ProducerTest {

  public static void main(String[] args) throws IOException {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("jms/applicationContext-jms.xml");
    EmailSendingRequestProducer producer = (EmailSendingRequestProducer) applicationContext.getBean("msgSender");
    producer.sendMessage(new Mail(
        "zhangwei","zhengwei@lanqiao.org","title","nice 2 meet u!!"
    ));

  }
}