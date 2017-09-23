package org.lanqiao.showcase.service;

import org.lanqiao.showcase.entity.Team;
import org.lanqiao.showcase.jpadao.TeamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@CacheConfig(cacheNames = {/*"demoCache",*/"teamCache"},cacheManager = "redisCacheManager")
public class TeamService {
  @Autowired
  private TeamDao teamDao;

  @Cacheable(key = "'Team_'+#p0")
  public Team getTeam(Integer teamId) {
    return teamDao.findOne(teamId);
  }
}


