package org.lanqiao.showcase.redis;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration("classpath:redis/applicationContext-redis.xml")
public class RedisTemplateTest extends AbstractJUnit4SpringContextTests {

  @Autowired
  private RedisTemplate redisTemplate;

  @Test
  public void testString() throws InterruptedException {
    final ValueOperations valueOperations = redisTemplate.opsForValue();
    valueOperations.set("key-925-1", "value-925-1");
    assertThat(valueOperations.get("key-925-1")).isEqualTo("value-925-1");
    valueOperations.set("key-925-2", "value-925-2",3, TimeUnit.SECONDS);
    TimeUnit.SECONDS.sleep(4);
    assertThat(valueOperations.get("key-925-2")).isNull();
    assertThat(valueOperations.setIfAbsent("key-925-1", "value-925-333")).isFalse();
  }
  @Test
  public void testHashOps(){
    final HashOperations hashOperations = redisTemplate.opsForHash();
    Map map = new HashMap();
    map.put("k1","v1");
    map.put("k2","v2");
    map.put("k3","v3");
    hashOperations.putAll("map-925-1",map);
    assertThat(hashOperations.get("map-925-1","k1")).isEqualTo("v1");
  }
}
