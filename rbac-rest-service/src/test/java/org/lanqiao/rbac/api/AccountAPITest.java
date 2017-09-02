package org.lanqiao.rbac.api;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import org.web2017.mapper.JsonMapper;
import org.web2017.web.rest.Result;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class AccountAPITest {
  private static String baseUrl="http://localhost:8080/rbac-rest-service";
  private static String resourceUrl = baseUrl + "/rbac/account";
  private final RestTemplate restTemplate = new RestTemplate();
  private final JsonMapper jsonMapper = new JsonMapper();
  private String pagingQuery="?pageNumber=1&pageSize=10";

  @Test
  public void list() throws Exception {
    Result result=restTemplate.getForObject(resourceUrl+ pagingQuery, Result.class);
    assertThat(result.getCode()).isEqualTo(200);
    System.out.println(result.getData());
  }

}