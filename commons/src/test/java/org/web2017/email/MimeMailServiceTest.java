package org.web2017.email;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration("classpath:applicationContext-email.xml")
public class MimeMailServiceTest extends AbstractJUnit4SpringContextTests{
  @Autowired
  MimeMailService mimeMailService;
  @Test
  public void sendMail() throws Exception {
    mimeMailService.sendMail("zhengwei223@163.com","zhengwei@lanqiao.org","你好","<h1>hello</h1>",null);
  }

}