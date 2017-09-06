package org.lanqiao.rbac.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.web2017.mapper.JsonMapper;
// import org.web2017.test.category.SlowTest;

// @Category(SlowTest.class)
public class BaseFunctionalTester {
  protected static String baseUrl = "http://localhost:8080/rbac-rest-service";
  protected final RestTemplate restTemplate = new RestTemplate();
  protected final JsonMapper jsonMapper = new JsonMapper();
  protected Logger logger = LoggerFactory.getLogger(getClass());
}
