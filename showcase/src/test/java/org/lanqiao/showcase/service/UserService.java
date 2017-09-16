package org.lanqiao.showcase.service;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.lanqiao.showcase.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web2017.test.data.RandomData;

import javax.annotation.PostConstruct;
import java.util.Random;

@Service
public class UserService {
  @Autowired
  private CacheManager cacheManager;
  private Cache cache;
  private final String cacheName = "jaxrs-cache-user";
  /**
   * 这里我们放一些数据到缓存中，而不是放到数据库中，便于快速测试
   */
  @PostConstruct
  public void init() {
    cache = cacheManager.getCache(cacheName);

    final User user1 = new User();
    user1.setEmail(RandomData.randomName("email")+"@163.com");
    user1.setLoginName(RandomData.randomName("loginName"));
    user1.setName(RandomData.randomName("name"));
    user1.setTeamId(1L);
    cache.put(new Element("1", user1));
    final User user2 = new User();
    user1.setEmail(RandomData.randomName("email")+"@163.com");
    user1.setLoginName(RandomData.randomName("loginName"));
    user1.setName(RandomData.randomName("name"));
    user2.setTeamId(1L);
    cache.put(new Element("2", user2));
  }

  public User getUser(String id) {
    return (User) cache.get(id).getObjectValue();
  }
}
