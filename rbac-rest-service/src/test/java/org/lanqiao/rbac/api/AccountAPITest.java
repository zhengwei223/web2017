package org.lanqiao.rbac.api;

import org.junit.Test;
import org.lanqiao.rbac.base.BaseFunctionalTester;
import org.web2017.web.rest.Result;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 启动本例需要先启动web server
 */
public class AccountAPITest extends BaseFunctionalTester {
  private static String resourceUrl = baseUrl + "/rbac/account";
  private String pagingQuery = "?pageNumber=1&pageSize=10";

  @Test
  public void list() throws Exception {
    // 发起get请求，参数1：url 参数2：将结果转换为什么类型
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