package org.lanqiao.scloud.caller;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// 远调的代理，value是服务名
@FeignClient(value = "greeting.service")
// 写法应和服务接口一模一样
public interface GreetingService{
  @RequestMapping(method = RequestMethod.GET, value = "/greeting/{name}")
  public String greet(@PathVariable("name") String name) ;

}
