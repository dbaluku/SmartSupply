<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${not empty contentHeader }">
        ${contentHeader }
    </c:when>
    <c:otherwise>
        &nbsp;
    </c:otherwise>
</c:choose>