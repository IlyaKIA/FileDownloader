<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>File Downloader</title>
</head>
<body>
<table>
    <tr>
        <th> ID </th>
        <th> Логин </th>
        <th> Роль </th>
        <th> Изменить роль </th>
    </tr>
    <jsp:useBean id="users" scope="request" type="java.util.List"/>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.login}</td>
            <td>${user.role}</td>
            <td><form method="post" action="${pageContext.request.contextPath}/user_manage">
                <input type="number" name="user_id" value="${user.id}" hidden>
                <input class="button" type="submit" value=">">
            </form></td>
        </tr>
    </c:forEach>
</table>
<br/>
<p><a href='${pageContext.request.contextPath}/logout'>Выход</a></p>
<p><a href='${pageContext.request.contextPath}/main'>Домой</a></p>
</body>
</html>
