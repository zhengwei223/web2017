package org.web2017.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.web2017.commons.Responses;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 处理器方法抛出的异常解析器：<br/>
 * 在这里我们可以对异常进行转码、包装或者序列化为json响应给客户端
 */
public class RestHandlerExceptionResolver extends AbstractHandlerExceptionResolver {
  private final Logger logger = LoggerFactory.getLogger(RestHandlerExceptionResolver.class);

  @Override
  protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
    Result result = new Result();
    if (handler instanceof HandlerMethod) {
      HandlerMethod handlerMethod = (HandlerMethod) handler;

      if (e instanceof ServiceException) {//业务失败的异常，如“账号或密码错误”
        result.setCode(ResultCode.BAD_REQUEST).setMessage(e.getMessage());
        logger.info(e.getMessage());
      } else if(e instanceof BindException){
        BindException be= (BindException) e;
        result.setCode(ResultCode.BAD_REQUEST).setMessage("数据校验失败");
        Map data = new HashMap();
        for(FieldError fieldError:be.getFieldErrors()){
          data.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        result.setData(data);
      }else {
        result.setCode(ResultCode.INTERNAL_SERVER_ERROR).setMessage("接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员");
        String message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
            request.getRequestURI(),
            handlerMethod.getBean().getClass().getName(),
            handlerMethod.getMethod().getName(),
            e.getMessage());
        logger.error(message, e);
      }
    } else {
      if (e instanceof NoHandlerFoundException) {
        result.setCode(ResultCode.NOT_FOUND).setMessage("接口 [" + request.getRequestURI() + "] 不存在");
      } else {
        result.setCode(ResultCode.INTERNAL_SERVER_ERROR).setMessage(e.getMessage());
        logger.error(e.getMessage(), e);
      }
    }
    Responses.sendResult(response, result);
    return new ModelAndView();
  }

  @Override
  public int getOrder() {
    return 0;
  }
}
