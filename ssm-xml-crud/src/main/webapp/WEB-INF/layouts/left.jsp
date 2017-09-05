<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div id="leftbar" class="col-md-2">
	<h1>单表crud</h1>
	<div class="submenu">
		<a id="account-tab"href="${ctx}/rbac/user/">用户信息管理</a>
	</div>
</div>