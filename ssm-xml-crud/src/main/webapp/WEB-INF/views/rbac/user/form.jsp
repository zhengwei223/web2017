<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>维护用户信息</title>
</head>
<body>
<div class="row">
    <form:form action="${ctx}/rbac/user/save" class="form-horizontal" method="post" commandName="user">
        <form:hidden path="id" />
        <div class="form-group">
            <label class="col-sm-2 control-label">昵称</label>
            <div class="col-sm-10">
            <form:input class="form-control" path="nickName" placeholder="昵称"  />
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">真实姓名</label>
            <div class="col-sm-10">
            <form:input class="form-control" path="realName" placeholder="真实姓名" />
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">性别</label>
            <div class="col-sm-10">
            <%--<label class="radio-inline">
                <input type="radio" name="gender"  value="1" <c:if test="${user.gender}"> checked </c:if>>男
            </label>
            <label class="radio-inline">
                <input type="radio" name="gender"  value="0" <c:if test="${!user.gender}"> checked </c:if>> 女
            </label>--%>
                <form:radiobutton  path="gender" value="1" label="男"/>
                <form:radiobutton  path="gender" value="0" label="女"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">角色</label>
            <div class="col-sm-10">
                <form:checkboxes   path="roles" items="${allRoles}" itemValue="id" itemLabel="name"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">提交</button>
            </div>
        </div>
    </form:form>
</div>
</body>
</html>
