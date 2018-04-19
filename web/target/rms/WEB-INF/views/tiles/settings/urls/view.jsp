<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div id="buttonStrip" class="group buttonStrip">
	<a href="${baseUrl}/urls/add">Add</a>
	<div style="clear: both;"></div>
</div>

<div>
	<div id="records" style="min-height: 400px;">
		<rmsTags:name-of-item-on-page name="Url" />
		<table class=" sysTable recordTable">
			<thead>
				<tr>
					<th></th>
					<th>No</th>
					<th>Url String</th>
					<th>User types</th>
					<th></th>
				</tr>
			</thead>

			<tbody>
				<c:choose>
					<c:when test="${not empty listItems  && fn:length(listItems) > 0}">
						<c:forEach var="url" items="${listItems}" varStatus="status">
							<tr id="${url.id }">
								<td>
									<!--<rmsTags:rowcheckbox nameOfItemOnPage="${nameOfItemOnPage}" id="${url.id }" /> -->
								</td>
								<td>${status.count }</td>
								<td>${url.url}</td>
								<td>
									<c:choose>
										<c:when test="${not empty url.userTypeString }">
											${url.userTypeString }<br>
										</c:when>
										<c:otherwise>
											<font color="#ff0000">None</font>
										</c:otherwise>
									</c:choose>
								</td>
								<td><commonTags:editdeletebuttons name="Url" url="${activeItem}" id="${url.id }"/></td>
							</tr>
						</c:forEach>
					</c:when>

					<c:otherwise>
						<tr>
							<td colspan="5">
								<h3>
									<font color="#ff0000">No url's found</font>
								</h3>
							</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
</div>