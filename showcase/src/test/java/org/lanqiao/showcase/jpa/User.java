package org.lanqiao.showcase.jpa;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
  private Integer id;
  private String username;
  private String password;
  private Integer status;

  @Id
  @GeneratedValue(strategy =GenerationType.AUTO)
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}
