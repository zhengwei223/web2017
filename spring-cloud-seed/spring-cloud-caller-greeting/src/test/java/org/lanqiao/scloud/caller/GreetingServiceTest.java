package org.lanqiao.scloud.caller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MockedOuterBeanFactory.class)
public class GreetingServiceTest {
  @Autowired
  private GreetingService greetingService;

  @Test
  public void greet() throws Exception {
    assertThat(greetingService.greet("Sansa")).isNotNull();
  }

}