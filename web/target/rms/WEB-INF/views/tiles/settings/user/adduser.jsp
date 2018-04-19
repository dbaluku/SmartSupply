<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 27-Mar-18
  Time: 8:38 AM
  To change this template use File | Settings | File Templates.
--%>
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
                        <h5>User Information</h5>
                    </div>
                    <div class="card-block">
                        <form:form action="${baseUrl }/staff-users/save" commandName="objectKey">
                            <form:hidden path="id" />
                            <div class="row">
                                <label class="col-sm-4 col-lg-2 col-form-label">First Name</label>
                                <div class="col-sm-8 col-lg-6">
                                    <div class="input-group">
                                        <form:input path="firstName" class="form-control" placeholder="Name"/>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <label class="col-sm-4 col-lg-2 col-form-label">Last Name</label>
                                <div class="col-sm-8 col-lg-6">
                                    <div class="input-group">
                                        <form:input path="lastName"  class="form-control" placeholder="Last Name"/>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <label class="col-sm-4 col-lg-2 col-form-label">User Type </label>
                                <div class="col-sm-8 col-lg-6">
                                    <div class="input-group">
                                        <form:select class="form-control" path="userType" items="${userTypes }" itemLabel="name"></form:select>                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <label class="col-sm-4 col-lg-2 col-form-label">Gender </label>
                                <div class="col-sm-8 col-lg-6">
                                    <div class="input-group">
                                        <form:select class="form-control" path="gender" items="${genders }" itemLabel="name"></form:select>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <label class="col-sm-4 col-lg-2 col-form-label">Email</label>
                                <div class="col-sm-8 col-lg-6">
                                    <div class="input-group">
                                        <form:input path="email" class="form-control"/>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <label class="col-sm-4 col-lg-2 col-form-label">Username</label>
                                <div class="col-sm-8 col-lg-6">
                                    <div class="input-group">
                                        <form:input path="username" class="form-control" />
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <label class="col-sm-4 col-lg-2 col-form-label">Dept/Branch</label>
                                <div class="col-sm-8 col-lg-6">
                                    <div class="input-group">
                                        <form:select path="branch" class="form-control" items="${branches}" itemLabel="name" itemValue="id"></form:select>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <label class="col-sm-4 col-lg-2 col-form-label"> Roles</label>
                                <div class="col-sm-8 col-lg-6">
                                    <div class="input-group">
                                        <form:checkboxes items="${roles }"  path="roles" itemValue="id" itemLabel="name" />
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <label class="col-sm-4 col-lg-2 col-form-label">Job Titles</label>
                                <div class="col-sm-8 col-lg-6">
                                    <div class="input-group">
                                        <form:checkboxes items="${jobTitles }"  path="jobTitles" itemValue="id" itemLabel="name" />

                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <label class="col-sm-4 col-lg-2 col-form-label">Password </label>
                                <div class="col-sm-8 col-lg-6">
                                    <div class="input-group">
                                        <form:password id="txtPassword" path="clearTextPassword" class="form-control" size="25" />
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <label class="col-sm-4 col-lg-2 col-form-label">Confirm Password </label>
                                <div class="col-sm-8 col-lg-6">
                                    <div class="input-group">
                                        <input type="password" class="form-control" id="txtPasswordConfirmation" size="25" />                                    </div>
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