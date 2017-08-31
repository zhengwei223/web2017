package org.lanqiao.rbac.base;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration("classpath:spring-context.xml")
@ActiveProfiles("dev2")
public class BaseTester extends AbstractJUnit4SpringContextTests {
}
