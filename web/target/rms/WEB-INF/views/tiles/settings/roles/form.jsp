<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>

<style>
form#role input[type="text"] {
	width: 70%;
}

#permissionlist span {
	display: inline-block;
	width: 50%;
}

#courseList span {
	display: inline-block;
	width: 33%;
}

#courseIntakeList span {
	display: inline-block;
	width: 25%;
}

#courseDropDown {
	width: 250px;
}
</style>

<div>
	<form:form action="${baseUrl }/roles/save" commandName="objectKey">
		<form:hidden path="id" />
		<div class="splitcontentleft">
			<div class="box tabular">
				<h3>Information</h3>
				<p>
					<label>
						Name <span class="required">*</span>
					</label>
					<form:input path="name" cssClass="uiTextbox" readonly="${ (role.name eq 'ROLE_ADMINISTRATOR') ? true: false}" />
				</p>
				<p>
					<label>
						Description <span class="required">*</span>
					</label>
					<form:input path="description" cssClass="uiTextbox" />
				</p>
			</div>

			<div>
				<div class="box">
					<h3>Courses</h3>
					<div style="border-bottom: 1px solid #BBB;">
                        <input type="checkbox" name="selectAll" id="selectAllCheckBoxes" container-id="courseList"/>
                        <b>Select All &emsp;&emsp; Search</b>
                        <input id="txtFilterCheckBoxes" id-sub-string="courses" type="text"placeHolder="search courses"
                        class="uiTextbox long">
                    </div>
					<p id="courseList">
						<form:checkboxes items="${courses }" path="courses" itemValue="id" itemLabel="code" />
					</p>
				</div>
			</div>

			<div>
				<div class="box">
					Course:
                    <form:select id="courseDropDown" cssClass="uiDropdown" path="course">
                        <form:options itemValue="id" items="${courses}" itemLabel="name" />
                    </form:select>
					<a id="lnkLoadIntakes" class="uiButton"
						href="
					<c:choose>
						<c:when test="${not empty role.id }">
							<c:out value="${baseUrl }/roles/addcourseintakes/${role.id}/${role.course.id}"></c:out>
						</c:when>
						<c:otherwise>
							<c:out value="${baseUrl }/roles/addcourseintakes/"></c:out>
						</c:otherwise>
					</c:choose>
					"
						title="Click to load intakes of a selected course"
					>Load Intakes</a>

					<h3>Course Intakes</h3>

					<p id="courseIntakeList">
						<form:checkboxes items="${courseIntakes }" path="courseIntakes" itemValue="id" itemLabel="courseCodeIntakeName" />
					</p>
				</div>
			</div>
			<div style="clear: both"></div>
			<div>
				<input id="btnSaveRole" type="submit" value="Save" class="uiButton" />
			</div>
		</div>
		<div class="splitcontentright">
			<div class="box">
				<h3>Permissions</h3>
				<div style="border-bottom: 1px solid #BBB;">
                    <input type="checkbox" name="selectAll" id="selectAllCheckBoxes" container-id="permissionList"/>
                    <b>Select All &emsp;&emsp; Search</b>
                    <input id="txtFilterCheckBoxes" id-sub-string="perm" type="text" placeHolder="search permission" class="uiTextbox long">
                </div>
				<p id="permissionList" class="multipleCheckboxes">
					<form:checkboxes items="${permissions }" path="permissions" itemValue="id" itemLabel="displayName" />
				</p>
			</div>
		</div>

	</form:form>
</div>