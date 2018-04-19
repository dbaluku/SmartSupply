<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="navigation" style="margin-top: 5px;">

    <c:if test="${numPages > 0 }">
        <div class="navSpan">
            <c:if test="${not empty searchParamsBack }">
                <form:form action="${baseUrl}/${activeItem}/back" commandName="searchParamsBack">

                    <c:if test="${activeItem eq 'curriculums'}">
                        <form:hidden path="name"/>
                        <form:hidden path="type"/>
                        <form:hidden path="userCurriculumsOnly"/>
                    </c:if>

                    <c:if test="${activeItem eq 'courseunits'}">
                        <form:hidden path="codeOrName"/>
                        <form:hidden path="type"/>
                    </c:if>

                    <c:if test="${activeItem eq 'course-intakes'}">
                        <form:hidden path="course"/>
                        <form:hidden path="intake"/>
                        <form:hidden path="gradingSystem"/>
                        <form:hidden path="curriculum"/>
                        <form:hidden path="userItemsOnly"/>
                    </c:if>

                    <c:if test="${activeItem eq 'staff-users'}">
                        <form:hidden path="nameOrUserName"/>
                        <form:hidden path="userType"/>
                        <form:hidden path="branch"/>
                        <form:hidden path="role"/>
                        <form:hidden path="gender"/>
                    </c:if>

                    <!-- special-grades have no search-->
                    <!-- grading-systems have no search-->

                    <form:hidden path="pageNo"/>
                    <button type="submit"><span class="ui-icon ui-icon-triangle-1-w"></span></button>
                </form:form>
            </c:if>

            <c:if test="${not empty searchParamsNext }">
                <form:form action="${baseUrl}/${activeItem}/next" commandName="searchParamsNext">

                    <c:if test="${activeItem eq 'curriculums'}">
                        <form:hidden path="name"/>
                        <form:hidden path="type"/>
                        <form:hidden path="userCurriculumsOnly"/>
                    </c:if>

                    <c:if test="${activeItem eq 'courseunits'}">
                        <form:hidden path="codeOrName"/>
                        <form:hidden path="type"/>
                    </c:if>

                    <c:if test="${activeItem eq 'course-intakes'}">
                        <form:hidden path="course"/>
                        <form:hidden path="intake"/>
                        <form:hidden path="gradingSystem"/>
                        <form:hidden path="curriculum"/>
                        <form:hidden path="userItemsOnly"/>
                    </c:if>

                    <c:if test="${activeItem eq 'staff-users'}">
                        <form:hidden path="nameOrUserName"/>
                        <form:hidden path="userType"/>
                        <form:hidden path="branch"/>
                        <form:hidden path="role"/>
                        <form:hidden path="gender"/>
                    </c:if>

                    <form:hidden path="pageNo"/>

                    <button type="submit"><span class="ui-icon ui-icon-triangle-1-e"></span></button>
                </form:form>
            </c:if>
        </div>
        <div class="navSpan" style="padding-top: 3px; padding-right: 3px;">
            <span>${from} - ${to} of ${totalNumberOfItems}</span>
        </div>

    </c:if>
</div>