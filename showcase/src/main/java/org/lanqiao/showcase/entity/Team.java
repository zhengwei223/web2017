/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.lanqiao.showcase.entity;

import com.google.common.collect.Lists;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 开发团队.
 * 
 * @author calvin
 */
public class Team extends IdEntity {

	private String name;
	private Long masterId;

	public Team() {
	}

	public Team(Long id,String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getMasterId() {
		return masterId;
	}

	public void setMasterId(Long masterId) {
		this.masterId = masterId;
	}
}
