<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>

<div class="pcoded-inner-content">
	<!-- Main-body start -->
	<div class="main-body">
		<div class="page-wrapper">
			<div class="page-body">
				<div class="card">
					<div class="card-header">
						<h5> Url Form</h5>
					</div>
					<div class="card-block">
						<form:form action="${baseUrl }/urls/save" commandName="objectKey">
							<form:hidden path="id" />
							<div class="row">
								<label class="col-sm-4 col-lg-2 col-form-label">Url String</label>
								<div class="col-sm-8 col-lg-6">
									<div class="input-group">
										<form:input path="url" class="form-control" />
									</div>
								</div>
							</div>
							<div class="row">
								<label class="col-sm-4 col-lg-2 col-form-label">User Types</label>
								<div class="col-sm-8 col-lg-6">
									<div class="input-group">
										<form:checkboxes path="userTypes"  items="${userTypes }"></form:checkboxes>
									</div>
								</div>
							</div>

							<div class="row">
								<label class="col-sm-4 col-lg-6 col-form-label"></label>
								<div class="col-sm-2 col-lg-2">
									<div class="input-group">
										<input id="btnSave" type="submit" value="Save" class="btn btn-block"/>
									</div>
								</div>
							</div>

						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>