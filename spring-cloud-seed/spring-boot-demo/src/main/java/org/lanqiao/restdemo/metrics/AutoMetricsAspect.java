package org.lanqiao.restdemo.metrics;

import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.annotation.Counted;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.ConcurrentHashMap;

import static com.codahale.metrics.MetricRegistry.name;

@Component
@Aspect
public class AutoMetricsAspect {
  ConcurrentHashMap<String,Counter> counters = new ConcurrentHashMap<>();
  @Autowired
  MetricRegistry metricRegistry;

  @Pointcut("execution(public * *(..))")
  public void publicMethods(){}

  @Before("publicMethods() && @annotation(countedAnnotation)")
  public void instrumentCounted(JoinPoint jp, Counted countedAnnotation){
    String countName = countedAnnotation.name();
    String name = name(jp.getTarget().getClass(),
        StringUtils.hasLength( countName )? countName :jp.getSignature().getName(),
        "counter");
    Counter counter = counters.computeIfAbsent(name,key->metricRegistry.counter(key));
    counter.inc();
  }
}
