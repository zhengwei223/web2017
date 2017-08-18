package org.lanqiao.restdemo.entity;

public class User {

  private Integer id;
  private String username;
  private String password;
  private Integer status;

  public User() {
  }

  public User(String username, String password, Integer status) {
    this.username = username;
    this.password = password;
    this.status = status;
  }

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

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", username='" + username + '\'' +
        ", password='" + password + '\'' +
        ", status=" + status +
        '}';
  }
}
