package org.lanqiao.restdemo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
@MapperScan("org.lanqiao.restdemo.repository")
@Configuration
public class MybatisConfig {
}
