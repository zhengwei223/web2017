<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="header" class="row">
    <div>
        <h1>ssm示例
            <small>--crud</small>
        </h1>
    </div>
    <div class="pull-right">
        <a href="${ctx}/login">登录</a>
        <c:if test="${sessionScope.user != null}">
            你好, ${sessionScope.user.name} <a href="${ctx}/logout">退出登录</a>
        </c:if>
    </div>
</div>