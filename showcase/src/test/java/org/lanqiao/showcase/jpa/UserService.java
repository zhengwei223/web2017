package org.lanqiao.showcase.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@CacheConfig(cacheNames = {"demoCache"},cacheManager = "cManager")
public class UserService {
  @Autowired
  private UserDao userDao;

  public Iterable<User> findAll(){
    return userDao.findAll();
  }
  @CachePut(key = "'User_'+#id")  // 将键值对存储在缓存中
  public void save(User user){
    userDao.save(user);
  }
  // @Cacheable(key = "'User_'+#id")  // 默认以参数为key，查询缓存，命中则返回；未命中执行方法的逻辑，最终将参数和返回值作为k-v存储在缓存中
  public User findById(Integer id){
    return userDao.findOne(id);
  }
  @CacheEvict(key = "'User_'+#id")
  public void delete(Integer id){
    userDao.delete(id);
  }
}
