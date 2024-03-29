<%--
  Created by IntelliJ IDEA.
  User: VK
  Date: 01.10.2019
  Time: 9:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Registration</title>
    <fmt:setLocale value="${locale}"/>
    <fmt:setBundle basename="message"/>
</head>
<body>

<jsp:include page="header.jsp"/>
<form name="registrationForm" action="register" method="post">
    <input type="hidden" name="command" value="register">
    <div class="formContainer">
        <label><b><fmt:message key="name"/> </b></label>
        <input type="text"  name="name" required>

        <label><b><fmt:message key="login"/></b></label>
        <input type="text"  name="login" required>

        <label><b><fmt:message key="password"/></b></label>
        <input type="password"  name="password" required>

        <label><b><fmt:message key="confirm_password"/></b></label>
        <input type="password"  name="confirmPassword" required>

        <div class="submitButton">
            <button type="submit"><fmt:message key="create"/></button>
        </div>

        <br/>
        <label style="color: brown">
            ${error}
        </label>
    </div>
</form>
</html>
