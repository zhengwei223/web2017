package org.lanqiao.showcase.jms;

import org.lanqiao.showcase.email.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class EmailSendingRequestProducer {
  private Logger logger = LoggerFactory.getLogger(getClass());
  @Autowired
  private AmqpTemplate amqpTemplate;

  public void sendMessage(Mail mail) {
    if (logger.isDebugEnabled()){
      logger.debug("send email : {}",mail);
    }
    amqpTemplate.convertAndSend("emailDirectExchange","emailSendKey",mail);
  }
}
