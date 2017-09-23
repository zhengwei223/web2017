package org.lanqiao.showcase.jpadao;

import org.lanqiao.showcase.entity.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamDao extends CrudRepository<Team,Integer> {
}
