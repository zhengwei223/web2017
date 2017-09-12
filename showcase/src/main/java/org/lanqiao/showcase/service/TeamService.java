package org.lanqiao.showcase.service;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.lanqiao.showcase.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.Configuration;
@Service
public class TeamService {
  @Autowired
  private CacheManager cacheManager;
  private Cache cache;
  private final String cacheName = "jaxrs-cache-team";

  /**
   * 这里我们放一些数据到缓存中，而不是放到数据库中，便于快速测试
   */
  @PostConstruct
  public void init() {
    cache = cacheManager.getCache(cacheName);
    cache.put(new Element("1", new Team(1L,"大神组")));
    cache.put(new Element("2", new Team(2L,"大婶组")));
  }

  public Team getTeam(Long teamId) {
    return (Team) cache.get(String.valueOf(teamId)).getObjectValue();
  }
}


