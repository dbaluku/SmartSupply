<%@page language="java" isELIgnored="false" contentType="text/html"
        pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions' %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="baseUrl"
       value="${fn:replace(pageContext.request.requestURL, pageContext.request.requestURI, pageContext.request.contextPath)}"/>

<div id="tree" baseUrl="${baseUrl }">
    <input type="hidden" id="treeInitUrl" value="
    <c:choose>
		<c:when test="${not empty treeviewExpandId }">
			${baseUrl }/get/json/expandedcourseJsonTree/${treeviewExpandId }/
		</c:when>
		<c:otherwise>
			${baseUrl }/get/json/courseJsonTree
		</c:otherwise>
	</c:choose>
	">
</div>
