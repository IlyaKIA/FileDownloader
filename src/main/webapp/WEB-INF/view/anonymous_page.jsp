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
            <c:if test="${file.contains('.txt')}">
                <li><a href="${pageContext.request.contextPath}/get_file?file_name=${file}">${file}</a></li>
            </c:if>
            <c:if test="${!file.contains('.txt')}">
                <li>${file}</li>
            </c:if>
        </c:forEach>
    </ul>
    <br/>Остальные файлы доступны поле <a href='${pageContext.request.contextPath}/main/auth'>авторизации</a>.
</body>
</html>