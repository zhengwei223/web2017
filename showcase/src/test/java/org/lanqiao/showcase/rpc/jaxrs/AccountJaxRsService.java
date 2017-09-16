/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.lanqiao.showcase.rpc.jaxrs;

import org.lanqiao.showcase.entity.User;
import org.lanqiao.showcase.rpc.dto.UserDTO;
import org.lanqiao.showcase.service.TeamService;
import org.lanqiao.showcase.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.web2017.mapper.BeanMapper;
import org.web2017.web.MediaTypes;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * cxf在web.xml侦听/cxf, 在applicationContext.xml里侦听/jaxrx，完整访问路径为 /cxf/jaxrs/user/1.xml
 *
 * @author calvin
 * @author zhengwei
 */
@Path("/user")
public class AccountJaxRsService {

  private static Logger logger = LoggerFactory.getLogger(AccountJaxRsService.class);

  @Autowired
  private UserService accountService;
  @Autowired
  private TeamService teamService;

  @GET
  @Path("/{id}.xml")
  @Produces({MediaTypes.APPLICATION_XML_UTF_8 })
  public UserDTO getAsXml(@PathParam("id") Long id) {
    return getUserDTO(id);
  }

  private UserDTO getUserDTO(Long id) {
    User user = accountService.getUser(String.valueOf(id));
    if (user == null) {
      String message = "用户不存在(id:" + id + ")";
      logger.warn(message);
      throw buildException(Status.NOT_FOUND, message);
    }
    return bindDTO(user);
  }

  @GET
  @Path("/{id}.json")
  @Produces(MediaTypes.JSON_UTF_8)
  public UserDTO getAsJson(@PathParam("id") Long id) {
    return getUserDTO(id);
  }

  private UserDTO bindDTO(User user) {
    UserDTO dto = BeanMapper.map(user, UserDTO.class);
    // 补充Dozer不能自动绑定的属性
    dto.setTeamName(teamService.getTeam(user.getTeamId()).getName());
    return dto;
  }

  private WebApplicationException buildException(Status status, String message) {
    return new WebApplicationException(Response.status(status).entity(message).type(MediaTypes.TEXT_PLAIN_UTF_8)
        .build());
  }
}
