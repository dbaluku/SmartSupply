<%--
  Created by IntelliJ IDEA.
  User: SOLEMA
  Date: 5/31/2018
  Time: 1:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" isELIgnored="false" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%--<%@ taglib prefix="rmsTags" tagdir="/WEB-INF/tags"%>--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>--%>
<%--<%@ taglib prefix="rmsTags" tagdir="/WEB-INF/tags"%>--%>
<link href="${baseUrl }/static/css/sb-admin.css" rel="stylesheet"/>
<link href="${baseUrl }/static/css/style.css" rel="stylesheet"/>


<div>

    <div id="records" style="min-height: 400px;">
        <table class="table table-striped">
            <thead>
            <tr class="active">
                <th>Product </th>
                <th>Sales</th>
            </tr>
            </thead>

            <tbody>
            <c:choose>
                <c:when test="${not empty itemsLists  && fn:length(itemsLists) > 0}">
                    <c:forEach var="itemsList" items="${itemsLists }">
                           <td>${itemsList.name}</td>
                        <td>${itemsList.numbers}</td>
                            <%--<td>${itemsList.salesperItem(itemsList)}</td>--%>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="5"><h3>
                            <font color="#ff0000">No Sales found</font>
                        </h3></td>
                    </tr>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>
    </div>

</div>