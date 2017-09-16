package org.lanqiao.showcase.email;

import freemarker.template.Configuration;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.web2017.email.MimeMailService;

import java.util.HashMap;
import java.util.Map;

@ContextConfiguration("classpath:email/applicationContext-email.xml")
public class MimeMailServiceTest extends AbstractJUnit4SpringContextTests{
  @Autowired
  MimeMailService mimeMailService;
  @Test
  public void sendMail() throws Exception {
    Configuration fmConfig = new Configuration(Configuration.VERSION_2_3_23);
    fmConfig.setDirectoryForTemplateLoading(new ClassPathResource("email/").getFile());
    fmConfig.setDefaultEncoding( "UTF-8" );

    Map data = new HashMap();
    data.put("userName","999");
    String content = FreeMarkerTemplateUtils.processTemplateIntoString(fmConfig.getTemplate("mailTemplate.ftl"),data);
    mimeMailService.sendMail("zhengwei223@163.com","zhengwei@lanqiao.org","你好",content,"email/mailAttachment.txt");
    
  }

}