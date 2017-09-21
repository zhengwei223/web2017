package org.lanqiao.showcase.jms;

import org.lanqiao.showcase.email.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

public class EmailSendingRequestConsumer implements MessageListener {
  private Logger logger = LoggerFactory.getLogger(getClass());

  @Override
  public void onMessage(Message message) {
    logger.info("receive message:{}", message);
    try {
      //将字节流对象转换成Java对象
      Mail mail = (Mail) new ObjectInputStream(new ByteArrayInputStream(message.getBody())).readObject();
      if (logger.isDebugEnabled()) {

        logger.debug("发送邮件至：" + mail.getTo());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
