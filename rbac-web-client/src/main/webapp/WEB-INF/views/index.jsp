<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>首页</title>
</head>
<body>
${ctx}

<table>
    <c:forEach items="${result.data.list}" var="account">
        <tr>
            <td>${account.account}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
