package org.web2017.web.rest;



import java.util.List;

/**
 * 响应结果生成工具
 */
public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static Result genSuccessResult() {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static Result genSuccessResult(Object data) {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static LayUIResult genSuccessLayUIResult(List<?> list,Long total) {
        LayUIResult result = new LayUIResult();
        result.setCode(0);
        result.setMessage(DEFAULT_SUCCESS_MESSAGE);
        result.setData(list);
        result.setCount(total);
        return  result;
    }
    public static Result genFailResult(String message) {
        return new Result()
                .setCode(ResultCode.FAIL)
                .setMessage(message);
    }
}
