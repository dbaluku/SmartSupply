<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions' %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div id="buttonStrip" class="group buttonStrip">
    <div style="clear: both;"></div>
</div>

<div>
    <div id="records" style="min-height: 400px;">

        <table class="sysTable recordTable">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Organisation Type</th>
                    <th>Allowed Children Types</th>
                </tr>
            </thead>

            <tbody>
            <c:choose>
                <c:when test="${not empty orgUnitTypes  && fn:length(orgUnitTypes) > 0}">
                    <c:forEach var="orgUnitType" items="${orgUnitTypes}" varStatus="status">
                        <tr>
                            <td>
                                ${status.count}
                            </td>

                            <td>${orgUnitType.name}</td>
                            <td>${orgUnitType.childrenStr}</td>
                        </tr>
                    </c:forEach>
                </c:when>

                <c:otherwise>
                    <tr>
                        <td colspan="5">
                            <h3>
                                <font color="#ff0000">No Organisation Unit Types found</font>
                            </h3>
                        </td>
                    </tr>
                </c:otherwise>
            </c:choose>

            </tbody>
        </table>
    </div>
</div>