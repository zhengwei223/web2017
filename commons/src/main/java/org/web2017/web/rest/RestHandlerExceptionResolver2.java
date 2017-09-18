// package org.web2017.web.rest;
//
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.ConversionNotSupportedException;
// import org.springframework.beans.TypeMismatchException;
// import org.springframework.http.converter.HttpMessageNotReadableException;
// import org.springframework.http.converter.HttpMessageNotWritableException;
// import org.springframework.validation.BindException;
// import org.springframework.validation.FieldError;
// import org.springframework.web.HttpMediaTypeNotAcceptableException;
// import org.springframework.web.HttpMediaTypeNotSupportedException;
// import org.springframework.web.HttpRequestMethodNotSupportedException;
// import org.springframework.web.bind.MethodArgumentNotValidException;
// import org.springframework.web.bind.MissingServletRequestParameterException;
// import org.springframework.web.bind.ServletRequestBindingException;
// import org.springframework.web.multipart.support.MissingServletRequestPartException;
// import org.springframework.web.servlet.ModelAndView;
// import org.springframework.web.servlet.NoHandlerFoundException;
// import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
// import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;
// import org.web2017.commons.Responses;
// import org.web2017.validation.BeanValidators;
//
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import javax.validation.ConstraintViolationException;
// import java.util.HashMap;
// import java.util.Map;
//
// /**
//  * 处理器方法抛出的异常处理器：<br/>
//  * 在这里我们可以对异常进行转码、包装或者序列化
//  */
// public class RestHandlerExceptionResolver2 extends DefaultHandlerExceptionResolver {
//   private final Logger logger = LoggerFactory.getLogger(RestHandlerExceptionResolver2.class);
//
//
//   @Override
//   protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//     try {
//       if (ex instanceof ServiceException) {
//         sendResult(ResultCode.BAD_REQUEST, response, ex);
//       } else if (ex instanceof NoSuchRequestHandlingMethodException) {
//         sendResult(ResultCode.NOT_FOUND, response, ex);
//       }else if (ex instanceof ConstraintViolationException) {
//         Result result = new Result();
//         result.setCode(ResultCode.BAD_REQUEST);
//         result.setMessage("参数不正确");
//         result.setData(BeanValidators.extractPropertyAndMessage((ConstraintViolationException)ex));
//         Responses.sendResult(response, result);
//       } else if (ex instanceof HttpRequestMethodNotSupportedException) {
//         sendResult(ResultCode.BAD_REQUEST, response, ex);
//
//       } else if (ex instanceof HttpMediaTypeNotSupportedException) {
//         sendResult(ResultCode.BAD_REQUEST, response, ex);
//
//       } else if (ex instanceof HttpMediaTypeNotAcceptableException) {
//         sendResult(ResultCode.BAD_REQUEST, response, ex);
//
//       } else if (ex instanceof MissingServletRequestParameterException) {
//         sendResult(ResultCode.BAD_REQUEST, response, ex);
//
//       } else if (ex instanceof ServletRequestBindingException) {
//         sendResult(ResultCode.BAD_REQUEST, response, ex);
//
//       } else if (ex instanceof ConversionNotSupportedException) {
//         sendResult(ResultCode.BAD_REQUEST, response, ex);
//
//       } else if (ex instanceof TypeMismatchException) {
//         sendResult(ResultCode.BAD_REQUEST, response, ex);
//
//       } else if (ex instanceof HttpMessageNotReadableException) {
//         sendResult(ResultCode.BAD_REQUEST, response, ex);
//
//       } else if (ex instanceof HttpMessageNotWritableException) {
//         sendResult(ResultCode.BAD_REQUEST, response, ex);
//
//       } else if (ex instanceof MethodArgumentNotValidException) {
//         Map<String, String> errors = new HashMap<>();
//         MethodArgumentNotValidException e = (MethodArgumentNotValidException) ex;
//         for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
//           errors.put(fieldError.getObjectName() + fieldError.getField(),
//               fieldError.getDefaultMessage());
//         }
//         Result result = new Result();
//         result.setCode(ResultCode.BAD_REQUEST);
//         result.setMessage("参数不正确");
//         result.setData(errors);
//         Responses.sendResult(response, result);
//       } else if (ex instanceof MissingServletRequestPartException) {
//         sendResult(ResultCode.BAD_REQUEST, response, ex);
//       } else if (ex instanceof BindException) {
//         sendResult(ResultCode.BAD_REQUEST, response, ex);
//       } else if (ex instanceof NoHandlerFoundException) {
//         sendResult(ResultCode.BAD_REQUEST, response, ex);
//       }
//     } catch (Exception handlerException) {
//       logger.warn("Handling of [" + ex.getClass().getName() + "] resulted in Exception", handlerException);
//     }
//     return null;
//   }
//
//   private void sendResult(ResultCode code, HttpServletResponse response, Exception ex) {
//     Result result = new Result();
//     result.setCode(code).setMessage(ex.getMessage());
//     logger.info(ex.getMessage());
//     Responses.sendResult(response, result);
//   }
// }
