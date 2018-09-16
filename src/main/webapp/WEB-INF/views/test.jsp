<%--
  Created by IntelliJ IDEA.
  User: Qin_Yikai
  Date: 2018-09-16
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/tag.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
just for test

<h1>${test}</h1>
<table>
<c:forEach items="${list}" var="user">
    <tr>
        <td>${user.account}</td>
        <td>${user.name}</td>
    </tr>
</c:forEach>
</table>
</body>
</html>
