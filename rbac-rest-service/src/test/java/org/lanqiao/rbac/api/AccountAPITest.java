package org.lanqiao.rbac.api;

import org.junit.Test;
import org.lanqiao.rbac.base.BaseFunctionalTester;
import org.web2017.web.rest.Result;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountAPITest extends BaseFunctionalTester {
  private static String resourceUrl = baseUrl + "/rbac/account";
  private String pagingQuery = "?pageNumber=1&pageSize=10";

  @Test
  public void list() throws Exception {
    Result result = restTemplate.getForObject( resourceUrl + pagingQuery, Result.class );
    assertThat( result.getCode() ).isEqualTo( 200 );
    HashMap map = (HashMap) result.getData();
    map.forEach( (key, value) -> {
      if (key.equals( "list" )) {
        List<HashMap> list = (List<HashMap>) value;
        for (HashMap mm:list){
          mm.forEach( (k,v)->{
              System.out.print( k+":"+v +"\t");
            }
          );
          System.out.println();
        }
      } else
        logger.debug( key + ":" + value );
    } );
  }

}