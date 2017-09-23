package org.lanqiao.showcase.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="t_team")
public class Team implements Serializable {
  private Integer id;
  private String name;

  public Team() {
  }

  public Team(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
