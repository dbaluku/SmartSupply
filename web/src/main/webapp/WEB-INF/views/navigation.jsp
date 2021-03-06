<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="navigation" style="margin-top: 5px;">

	<c:if test="${navigationTotalPages > 0 }">
		<div class="navSpan">
			<c:if test="${navigationCurrentPage > 1 }">
				<c:choose>
					<c:when test="${not empty noneSearchUrl and noneSearchUrl}">
						<a href="${baseUrl }${navigationUrl }/page/${navigationCurrentPage -1 }" title=".ui-icon-triangle-1-w"
							class="ui-state-default ui-corner-all">
							<span class="ui-icon ui-icon-triangle-1-w"></span>
						</a>
					</c:when>
					<c:otherwise>
						<a href="${baseUrl }${navigationUrl }&pageNo=${navigationCurrentPage -1 }" title=".ui-icon-triangle-1-w"
							class="ui-state-default ui-corner-all">
							<span class="ui-icon ui-icon-triangle-1-w"></span>
						</a>
					</c:otherwise>
				</c:choose>
			</c:if>

			<c:if test="${navigationCurrentPage < navigationTotalPages }">
				<c:choose>
					<c:when test="${not empty noneSearchUrl and noneSearchUrl }">
						<a href="${baseUrl }${navigationUrl }/page/${navigationCurrentPage + 1 }" title=".ui-icon-triangle-1-e"
							class="ui-state-default ui-corner-all">
							<span class="ui-icon ui-icon-triangle-1-e"></span>
						</a>
					</c:when>
					<c:otherwise>
						<a href="${baseUrl }${navigationUrl }&pageNo=${navigationCurrentPage + 1 }" title=".ui-icon-triangle-1-e"
							class="ui-state-default ui-corner-all"
						>
							<span class="ui-icon ui-icon-triangle-1-e"></span>
						</a>
					</c:otherwise>
				</c:choose>

			</c:if>
		</div>
		<div class="navSpan" style="padding-top: 3px; padding-right: 3px;">
			<span> <c:choose>
					<c:when test="${navigationCurrentPage == 1 }">
						1 - ${navigationNumberOfItemsOnPage } 
					</c:when>
					<c:otherwise>
						<c:set var="recordCount" value="${((navigationCurrentPage - 1) * max_num_per_page) }"></c:set>
						 ${recordCount + 1 } - ${ recordCount + navigationNumberOfItemsOnPage }
					</c:otherwise>
				</c:choose> of ${navigationTotalNumberOfItems }
			</span>
		</div>

	</c:if>
</div>