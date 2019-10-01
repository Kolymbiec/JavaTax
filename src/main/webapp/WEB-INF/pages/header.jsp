<%--
  Created by IntelliJ IDEA.
  User: VK
  Date: 20.09.2019
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/user" prefix="userInfo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <link rel="stylesheet" href="../../css/style.css"/>
    <fmt:setLocale value="${locale}"/>
    <fmt:setBundle basename="message"/>
</head>
<body>
<div class="navbar">
    <a href="/"><fmt:message key="home"/> </a>
    <c:choose>
        <c:when test="${empty user}">
            <a href="login"><fmt:message key="sign_in"/></a>
            <a href="register"><fmt:message key="sign_up"/></a>
        </c:when>
        <c:otherwise>

        </c:otherwise>
    </c:choose>
    <div class="locale">
        <a href="Controller?command=locale&locale=en"><img src="../../images/en.png"></a>
        <a href="Controller?command=locale&locale=ua"><img src="../../images/ua.png"></a>
        <a href="Controller?command=locale&locale=ru"><img src="../../images/ru.png"></a>
    </div>
</div>
<userInfo:getInfo/>
<br>
<jsp:useBean id="date" class="java.util.Date" />
<fmt:setLocale value="${locale}" />
<fmt:formatDate pattern="yyyy-MM-dd" value="${date}" />

</body>
</html>
