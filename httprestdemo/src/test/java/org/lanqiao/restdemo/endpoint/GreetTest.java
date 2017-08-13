package org.lanqiao.restdemo.endpoint;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;

public class GreetTest {
  @Test
  public void sayHi() throws Exception {
    RestTemplate restTemplate = new RestTemplate(  );
    ResponseEntity<String> result = restTemplate.getForEntity( "http://localhost:8080/greet/hi",String.class );
    assertThat( result.getStatusCode() ).isEqualTo( HttpStatus.OK );
    System.out.println(result.getHeaders().getContentType());
    assertThat( result.getHeaders().getContentType().toString().contains( MediaType.APPLICATION_JSON_VALUE ) ).isTrue();
    assertThat(result.getBody()).isEqualTo( "hi spring boot web" );
  }

}