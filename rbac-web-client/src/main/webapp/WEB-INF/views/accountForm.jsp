<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${ctx}/account/register" method="post">
        <input name="account">
        <input name="password" type="password">
        <input name="userProfileId">
        <input type="submit" value="ok">
    </form>
</body>
</html>
