package org.lanqiao.metrics.test;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Counter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import org.web2017.test.data.RandomData;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class MetricRegistryTest {
  // 统计量注册表
  final static MetricRegistry metricRegistry = new MetricRegistry();

  final static Queue queue = new LinkedList();

  final static Counter methodInvokeCounter = new Counter();

  static {
    //注册一个Gauge
    metricRegistry.register( MetricRegistry.name( MetricRegistryTest.class, "queue","size" ), new Gauge<Integer>() {
      @Override
      public Integer getValue() {
        return queue.size();
      }
    } );


  }


  public static void main(String[] args) {
    //模拟填充队列
    new Thread( () -> {
      while (true) {
        queue.add( RandomData.randomId() );
        try {
          TimeUnit.SECONDS.sleep( 3 );
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    } ).start();


    //向控制台输出报告
    //定义一个ConsoleReporter
    ConsoleReporter reporter = ConsoleReporter.forRegistry(metricRegistry)
        .convertRatesTo(TimeUnit.SECONDS)
        .convertDurationsTo(TimeUnit.MILLISECONDS)
        .build();
    reporter.start(1, TimeUnit.SECONDS);
  }
}
