/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.lanqiao.showcase.rpc.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "User")
public class UserDTO {

  private Long id;
  private String loginName;
  private String name;
  private String email;
  private String teamName;

  public Long getId() {
    return id;
  }

  public void setId(Long value) {
    id = value;
  }

  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String value) {
    loginName = value;
  }

  public String getName() {
    return name;
  }

  public void setName(String value) {
    name = value;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String value) {
    email = value;
  }

  public String getTeamName() {
    return teamName;
  }

  public void setTeamName(String teamName) {
    this.teamName = teamName;
  }

  /**
   * 重新实现toString()函数方便在日志中打印DTO信息.
   */
  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
