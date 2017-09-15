/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.web2017.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author zhengwei
 */
public class MimeMailService {

  private static final String DEFAULT_ENCODING = "utf-8";

  private static Logger logger = LoggerFactory.getLogger(MimeMailService.class);

  private JavaMailSender mailSender;

  /**
   * Spring的MailSender.
   */
  public void setMailSender(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }


  /**
   * 发送MIME格式的邮件.
   */
  public void sendMail(String from ,String to,String title,String content,String attachmentPath) {

    try {
      MimeMessage msg = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(msg, true, DEFAULT_ENCODING);

      helper.setFrom(from);
      helper.setTo(to);
      helper.setSubject(title);
      helper.setText(content, true);
      if (null!=attachmentPath) {
        File attachment = new ClassPathResource(attachmentPath).getFile();
        helper.addAttachment(attachment.getName(), attachment);
      }

      mailSender.send(msg);
    } catch (MessagingException e) {
      logger.error("构造邮件失败", e);
    } catch (IOException e) {
      logger.error("构造邮件失败,附件文件不存在", e);
    }catch (Exception e) {
      logger.error("发送邮件失败", e);
    }
  }
}
