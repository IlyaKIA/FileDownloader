<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>File Downloader</title>
</head>
<body>
    <h4>Выберите файл для скачивания:</h4>
    <ul>
        <jsp:useBean id="files" scope="request" type="java.util.List"/>
        <c:forEach items="${files}" var="file">
                <li><a href="${pageContext.request.contextPath}/get_file?file_name=${file}">${file}</a></li>
        </c:forEach>
    </ul>
    <br/>
    <p><a href='${pageContext.request.contextPath}/logout'>Выход</a></p>
    <p><a href='${pageContext.request.contextPath}/user_manage'>User manager</a></p>
</body>
</html>
