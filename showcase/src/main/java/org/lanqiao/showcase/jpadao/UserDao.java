package org.lanqiao.showcase.jpadao;

import org.lanqiao.showcase.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User,Integer> {
}
