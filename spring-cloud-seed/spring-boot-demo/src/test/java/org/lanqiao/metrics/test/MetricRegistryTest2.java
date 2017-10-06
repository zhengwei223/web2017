package org.lanqiao.metrics.test;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

import java.util.concurrent.TimeUnit;

public class MetricRegistryTest2 {
  // 统计量注册表
  final static MetricRegistry metricRegistry = new MetricRegistry();


  final static Counter methodInvokeCounter =  metricRegistry.register(
      MetricRegistry.name( MetricRegistryTest2.class, "someMethod", "invokeCount" ),
      new Counter() );

  final static Timer methodTiming = metricRegistry.timer(
      MetricRegistry.name( MetricRegistryTest2.class, "someMethod", "timing" )
  );


  public static void someMethod() {
    //计时
    Timer.Context context = methodTiming.time();
    methodInvokeCounter.inc();
    //结束
    context.stop();
  }

  public static void main(String[] args) {
    //模拟方法调用
    new Thread( () -> {
      while (true) {
        someMethod();
        try {
          TimeUnit.SECONDS.sleep( 3 );
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    } ).start();


    //向控制台输出报告
    //定义一个ConsoleReporter
    ConsoleReporter reporter = ConsoleReporter.forRegistry( metricRegistry )
        .convertRatesTo( TimeUnit.SECONDS )
        .convertDurationsTo( TimeUnit.MILLISECONDS )
        .build();
    reporter.start( 2, TimeUnit.SECONDS );
  }
}
