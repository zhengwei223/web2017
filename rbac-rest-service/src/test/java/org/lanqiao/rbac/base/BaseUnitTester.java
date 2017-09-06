package org.lanqiao.rbac.base;

import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
// import org.web2017.test.category.FastTest;

/**
 * 单元测试基础类
 */
@ContextConfiguration("classpath:spring-context.xml")
@ActiveProfiles("development")
// @Category(FastTest.class)
public class BaseUnitTester extends AbstractJUnit4SpringContextTests {
  protected Logger logger = LoggerFactory.getLogger( getClass() );
}
