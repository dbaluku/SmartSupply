<%@page import="org.smartsupply.model.admin.User"%>
<%@page import="org.smartsupply.api.security.util.RmsSecurityUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style>
    .header-nav{
        background-color:#28a745;
        height:30px;
        font-size: 14pt;
    }
    .header-nav a{
        color: #ffffff;
    }
</style>

<c:set var="baseUrl" value="${fn:replace(pageContext.request.requestURL, pageContext.request.requestURI, pageContext.request.contextPath)}" />

<input id="baseUrl" type="hidden" value="${baseUrl }" />

<%
    User user = null;
    try {
        user = RmsSecurityUtil.getLoggedInUser();
        if (user != null) {
            pageContext.setAttribute("loggedInUser", user);
        }
    } catch (Exception ex) {
        /* ex.printStackTrace(); */
    }

%>
<div class="header-nav" style="">
<div class="user-profile">
    <c:if test="${not empty loggedInUser }">
        <a href="${baseUrl }/settings/myaccount/edit">
            <span class="my-profile-icon invert-profile-icon"></span>
            <span class="logged-in-user">${loggedInUser.firstName } ${loggedInUser.lastName }</span>
        </a>
    </c:if>
</div>

<div class="message-container">
    <div id="errorMsg"
        <c:choose>
            <c:when test="${not empty errorMessage}">
                class="<c:out value="ui-state-error ui-corner-all"></c:out>"
            </c:when>
            <c:otherwise>
                class="<c:out value="ui-state-error ui-corner-all hidden"></c:out>"
            </c:otherwise>
        </c:choose>>

        <span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span> <strong>Alert:</strong>
        <span id="eMsg">
            <c:if test="${not empty errorMessage }">
                <c:out value="${errorMessage }"></c:out>
            </c:if>
        </span>
    </div>
    <div id="systemMsg"
        <c:choose>
            <c:when test="${not empty systemMessage}">
                class="<c:out value="ui-state-highlight ui-corner-all"></c:out>"
            </c:when>
            <c:otherwise>
                class="<c:out value="ui-state-highlight ui-corner-all hidden"></c:out>"
            </c:otherwise>
        </c:choose>>

        <span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span> <strong>Hey!</strong>
        <span id="sMsg">
            <c:if test="${not empty systemMessage }">
                <c:out value="${systemMessage }"></c:out>
            </c:if>
        </span>
    </div>
</div>

<div class="header-links">

    <a title="logout" href="${baseUrl }/ServiceLogout">Logout</a>

    <c:if test="${not empty loggedInUser }">
        <c:if test="${loggedInUser.userType.name eq 'course-Admin' or loggedInUser.hasAdministrativePrivileges() }">
            <a title="Ms-Excel Imports" href="${baseUrl }/imports">Imports</a>
            <a title="System settings" href="${baseUrl }/settings/view">Settings</a>
            <a style="display: none;" title="Complaints" href="${baseUrl }/staff/complaint/viewcomplaints">Complaints</a>
        </c:if>
    </c:if>

    <a title="Home"  href="${baseUrl }/">Home</a>
</div>

<div style="clear: both;"></div>

<div id="loader">
    <span>Loading please wait</span>
</div>

</div>