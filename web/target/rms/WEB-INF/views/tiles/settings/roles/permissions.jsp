<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>


<table id="recordTable" class="sysTable recordTable">
	<thead>
		<tr>
		    <th></th>
            <th>Display Name</th>
            <th>System Name</th>
			<th>Description</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${not empty listItems  && fn:length(listItems) > 0}">
				<c:forEach var="perm" items="${listItems }" varStatus="status">
					<tr>
					    <td>
                            ${status.count}
                        </td>
						<td>${perm.displayName }</td>
						<td>${perm.name }</td>
						<td>${perm.description }</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>

			</c:otherwise>
		</c:choose>
	</tbody>
</table>
</div>