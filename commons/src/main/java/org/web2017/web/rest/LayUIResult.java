package org.web2017.web.rest;

import com.alibaba.fastjson.JSON;

/**
 * 统一API响应结果封装
 */
public class LayUIResult extends  Result{
    private Long count;
    public String getMsg() {
        return super.message;
    }

    public Long getCount() {
        return count;
    }

    public LayUIResult setCount(Long count) {
        this.count = count;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
