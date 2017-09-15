package org.web2017.email;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration("classpath:applicationContext-email.xml")
public class SimpleMailServiceTest extends AbstractJUnit4SpringContextTests {
  @Autowired
  private SimpleMailService simpleMailService;

  @Test
  public void sendMail() throws Exception {
    simpleMailService.sendMail("zhengwei223@163.com", "zhengwei@lanqiao.org", "纯文本邮件", "说点什么好呢");
  }


}