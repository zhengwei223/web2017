package org.lanqiao.scloud.caller;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MockedOuterBeanFactory {
  @Bean
  public GreetingService greetingService() {
    GreetingService greetingService= Mockito.mock(GreetingService.class);
    Mockito.when(greetingService.greet("Sansa")).thenReturn("Hello Sansa");
    return greetingService;
  }
}
