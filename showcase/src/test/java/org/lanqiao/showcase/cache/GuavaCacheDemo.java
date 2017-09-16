/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.lanqiao.showcase.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web2017.commons.Threads;

import java.util.concurrent.TimeUnit;

/**
 * 本地缓存演示，使用GuavaCache.
 *
 * @author calvin
 */
public class GuavaCacheDemo {
  class User {
  }

  class AccountService {
    public User getUser(Long key) {
      return null;
    }
  }

  protected final Logger logger = LoggerFactory.getLogger(this.getClass());
  private AccountService accountService;

  @Test
  public void demo() throws Exception {
    // 设置缓存最大个数为100，缓存过期时间为5秒
    LoadingCache<Long, User> cache = CacheBuilder.newBuilder().maximumSize(100)
        .expireAfterAccess(5, TimeUnit.SECONDS).build(new CacheLoader<Long, User>() {
          @Override
          public User load(Long key) throws Exception {
            logger.info("fetch from database");
            return accountService.getUser(key); // 模拟从数据库获取
          }
        });

    // 第一次加载会查数据库
    User user = cache.get(1L);
    //assertThat(user.getLoginName()).isEqualTo("admin");
    //assertThat(appender.isEmpty()).isFalse();

    // 第二次加载时直接从缓存里取
    User user2 = cache.get(1L);
    //assertThat(user2.getLoginName()).isEqualTo("admin");
    //assertThat(appender.isEmpty()).isTrue();

    // 第三次加载时，因为缓存已经过期所以会查数据库
    Threads.sleep(10, TimeUnit.SECONDS);
    User user3 = cache.get(1L);
    //assertThat(user3.getLoginName()).isEqualTo("admin");
    //assertThat(appender.isEmpty()).isFalse();
  }
}
