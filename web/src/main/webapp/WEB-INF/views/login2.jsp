<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="baseUrl"
       value="${fn:replace(pageContext.request.requestURL, pageContext.request.requestURI, pageContext.request.contextPath)}"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Welcome to CHS M&E System - Login</title>
    <link type="text/css" rel="stylesheet" href="${baseUrl }/static/css/custom-theme/jquery-ui-1.8.15.custom.css"/>

    <link type="text/css" rel="stylesheet" href="${baseUrl }/static/css/login.css"/>
    <link type="text/css" rel="stylesheet" href="${baseUrl }<spring:theme code='css'/>"/>

    <link rel="icon" type="image/png" href="${baseUrl }/static/images/types.png"/>
    <script type="text/javascript" src="${baseUrl }/static/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${baseUrl }/static/js/jquery-ui-1.8.15.custom.min.js"></script>
    <script type="text/javascript" src="${baseUrl }/static/js/rms-system.js"></script>
    <%--<link type="text/css" rel="stylesheet" href="${baseUrl}/static/css/bootstrap/bootstrap.min.css" />--%>
    <%--<script type="text/javascript" src="${baseUrl}/static/js/bootstrap/bootstrap.min.js"></script>--%>

</head>
<body class="login-bg">
<form name="loginForm"
      action="j_spring_security_check" method="post">
<div class="login-box">

    <div id="rcorners2" class="login-box-body ">
        <div class="login-logo">
            <img src="${baseUrl }/static/images/chs/muk_logo.png" id="first_logo" align="center"><br>
            <label style="font-size:12px !important;">College of Health Sciences<br> Information Management System <br/>(CHSIMS)</label>
        </div><!-- /.login-logo -->

        <div class="row form-row">
            <div class="col-md-12 pad-t-10">
                <div class="row form-row">
                    <div class="col-md-12">

                    </div>
                </div>
            </div>
        </div>

        <div class="container">
            <div class="error" style="margin-bottom: 5px;color: #ff0000;background: wheat;padding: 5px;font-size: 12px;">
                <c:if test="${not empty errorMessage }">
                    <c:out value="${errorMessage }"></c:out>
                </c:if>
            </div>
        <div class="form-group has-feedback">
            <label id="usernameLabel" class="uiLabel">Username:</label>
            <input name="j_username" id="j_username" value="" type="text" class="uiTextbox"/>
        </div>
        <div class="form-group has-feedback">
            <label id="passwordLabel" class="uiLabel">Password:</label>
            <input name="j_password" id="j_password" value="" type="password" class="uiTextbox"/>
        </div>
        </div>

        <!-- Buttons -->
        <div class="row form-row" style="margin-bottom: 5px;">
            <div class="col-md-12 pad-t-10">
                <div class="row form-row">
                    <div class="col-md-6" style="text-align: center;">
                        <input class="login-button" name="btnSubmit" id="btnSubmitOnLoginInterface" type="submit" value="Sign In"/>
                    </div>
                </div>
            </div>
            <!--/.col-md-12-->
        </div>
        <a href="http://www.mets.or.ug" target="_blank" style="color: white !important;text-decoration: none;font-size: 11px;margin: 0px 15%;">Powered by MakSPH METS Program</a><br>
    </div><!-- /.login-box-body -->
</div>
</form>
<div class="footer centered-block-element">
    <div class="copyright"></div>
    <!--<div class="privacy-policy">Privacy Policy</div>
    <div class="terms-of-service">Terms of Service</div>
    <div class="help">Help</div>
    <div class="powered-by">Powered By - JAR</div>-->
</div>
</body>
</html>