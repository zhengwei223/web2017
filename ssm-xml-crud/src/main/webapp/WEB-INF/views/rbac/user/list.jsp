<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>用户信息列表</title>
    <script>
        $(document).ready(function () {
            $("#account-tab").addClass("active");
        });
    </script>
</head>
<body>
<h1>用户信息</h1>
<c:if test="${not empty message}">
    <div id="message" class="alert alert-success">
        <button data-dismiss="alert" class="close">×</button>
            ${message}</div>
</c:if>

<div class="row">
    <div class="offset4">
        <a class="btn btn-link pull-left" href="${ctx}/rbac/user/create">新增</a>
        <form class="form-search pull-right" action="#">
            <label>昵称：</label> <input type="text" name="search_LIKE_nickName" class="input-small"
                                       value="${param.search_LIKE_nickName}">
            <label>姓名：</label> <input type="text" name="search_LIKE_realName" class="input-small"
                                       value="${param.search_LIKE_realName}">
            <button type="submit" class="btn" id="search_btn">Search</button>
        </form>
    </div>
</div>

<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>ID</th>
        <th>昵称</th>
        <th>姓名</th>
        <th>性别</th>
        <th>角色</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${pageInfo.list}" var="user">
        <tr>
            <td>${user.id}&nbsp;</td>
            <td>${user.nickName}&nbsp;</td>
            <td>${user.realName}&nbsp;</td>
            <td>${user.genderString}&nbsp;</td>
            <td>${user.roleNames}&nbsp;</td>
            <td>
                <a href="${ctx}/rbac/user/update/${user.id}" id="editLink-${user.id}">修改</a>
                <a href="${ctx}/rbac/user/delete/${user.id}" id="deleteLink-${user.id}">删除</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<nav aria-label="Page navigation" class="pull-right">
    <ul class="pagination">

        <li <c:if test="${pageInfo.isFirstPage}">class="disabled"</c:if>>
            <a href="#" aria-label="Previous" >
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>
        <c:forEach begin="1" end="${pageInfo.pages}" var="page">
            <li><a href="${ctx}/rbac/user?page=${page}&size=10">${page}</a></li>
        </c:forEach>
        <li <c:if test="${pageInfo.isLastPage}">class="disabled"</c:if>>
            <a href="#" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
            </a>
        </li>
    </ul>
</nav>
</body>
</html>
