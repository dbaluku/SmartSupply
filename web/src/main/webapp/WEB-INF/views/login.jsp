<%--
  Created by IntelliJ IDEA.
  User: CODE
  Date: 07/02/2018
  Time: 23:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="baseUrl"
       value="${fn:replace(pageContext.request.requestURL, pageContext.request.requestURI, pageContext.request.contextPath)}"/>

<html>
<head>
    <title>Welcome to SmartSystem- Login</title>
    <link type="text/css" rel="stylesheet" href="${baseUrl }<spring:theme code='css'/>"/>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Login|SmartSupply</title>
    <!-- Bootstrap core CSS-->
    <link href="${baseUrl }/static/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom fonts for this template-->
    <link href="${baseUrl }/static/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- Custom styles for this template-->
    <link href="${baseUrl }/static/css/sb-admin.css" rel="stylesheet">
</head>
<body class="bg-dark">
<div class="container">
    <div class="card card-login mx-auto mt-5">
        <div class="card-header">Login</div>
        <div class="card-body">
            <form name="loginForm"
                  action="j_spring_security_check" method="post">
                <div class="form-group">
                    <label>Username :</label>
                    <input class="form-control" name="j_username" id="j_username" value="" type="text" placeholder="Enter username" />
                </div>
                <div class="form-group">
                    <label>Password </label>
                    <input class="form-control" name="j_password" id="j_password" value="" type="password" placeholder="Enter your password"/>
                </div>

                <input type="submit" class="btn btn-primary btn-block"  value="Login"/>
            </form>
            <div class="text-center">
                <%--<a class="d-block small mt-3" href="register.html">Register an Account</a>--%>
                <%--<a class="d-block small" href="forgot-password.html">Forgot Password?</a>--%>
            </div>
        </div>
    </div>
</div>
<!-- Bootstrap core JavaScript-->
<script type="text/javascript" src="${baseUrl }/static/js/rms-system.js"></script>

</body>
</html>
