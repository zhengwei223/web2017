package org.lanqiao.restdemo.config;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.management.MBeanServer;

@Component
@Configuration
@ComponentScan({"org.lanqiao.restdemo.metrics"})
@AutoConfigureAfter(AopAutoConfiguration.class)
public class DropwizardMetricsMBeanConfig {
  @Autowired
  private MBeanServer mBeanServer;
  @Autowired
  private MetricRegistry metricRegistry;

  @Bean
  public JmxReporter jmxReporter() {
    JmxReporter jmxReporter = JmxReporter
        .forRegistry(metricRegistry)
        .inDomain("org.lanqiao")
        .registerWith(mBeanServer)
        .build();
    return jmxReporter;
  }

}
