<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions' %>

<!-- Date-time picker css -->
<link rel="stylesheet" type="text/css" href="${baseUrl}/static/files/assets/pages/advance-elements/css/bootstrap-datetimepicker.css">
<script type="text/javascript" src="${baseUrl }/static/files/bower_components/jquery/js/jquery.min.js"></script>
<!-- Bootstrap date-time-picker js -->
<script type="text/javascript" src="${baseUrl}/static/files/bower_components/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${baseUrl}/static/files/assets/pages/advance-elements/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="${baseUrl }/static/js/rms-system.js"></script>

<div class="pcoded-inner-content">
    <!-- Main-body start -->
    <div class="main-body">
        <div class="page-wrapper">
<div class="page-body">
<div class="card">
    <div class="card-header">
        <h5>Branch Add Form</h5>
    </div>
    <div class="card-block">
        <form:form action="${baseUrl }/org-units/save" commandName="objectKey">
            <div class="row">
                <label class="col-sm-4 col-lg-2 col-form-label">Name</label>
                <div class="col-sm-8 col-lg-6">
                    <div class="input-group">
                        <form:input type="text" path="name" id="txtName" class="form-control" placeholder="Name"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <label class="col-sm-4 col-lg-2 col-form-label">Abbreviation</label>
                <div class="col-sm-8 col-lg-6">
                    <div class="input-group">
                        <form:input type="text" path="abbreviation"  class="form-control" placeholder="Abbreviation"/>
                    </div>
                </div>
            </div>

            <div class="row">
                <label class="col-sm-4 col-lg-2 col-form-label">Location</label>
                <div class="col-sm-8 col-lg-6">
                    <div class="input-group">
                        <form:input type="text" path="location"  class="form-control"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <label class="col-sm-4 col-lg-2 col-form-label">Date Created</label>
                <div class="col-sm-8 col-lg-6">
                    <div class="input-group">
                        <form:input class="form-control bootstrap-datetimepicker-widget" type="date" path="date_created" />
                    </div>
                </div>
            </div>
            <div class="row">
                <label class="col-sm-4 col-lg-2 col-form-label">Unit Type</label>
                <div class="col-sm-8 col-lg-6">
                    <div class="input-group">
                        <form:select path="type" id="ddType"  class="form-control" items="${orgUnitTypes}"/>
                    </div>
                </div>
            </div>

            <div class="row">
                <label class="col-sm-4 col-lg-2 col-form-label">Parent</label>
                <div class="col-sm-8 col-lg-6">
                    <div class="input-group">
                        <form:select path="parent" class="form-control">
                            <form:option value="none" label=""/>
                            <form:options itemValue="id" items="${orgUnits}" itemLabel="name"/>
                        </form:select>
                    </div>
                </div>
            </div>

            <div class="row">
                <label class="col-sm-4 col-lg-2 col-form-label">Head Title</label>
                <div class="col-sm-8 col-lg-6">
                    <div class="input-group">
                        <form:select path="headTitle" class="form-control">
                            <form:option value="none" label=""/>
                            <form:options itemValue="id" items="${jobTitles}" itemLabel="name"/>
                        </form:select>
                    </div>
                </div>
            </div>

            <div class="row">
                <label class="col-sm-4 col-lg-2 col-form-label">Head</label>
                <div class="col-sm-8 col-lg-6">
                    <div class="input-group">
                        <form:select path="head" class="form-control">
                            <form:option value="none" label=""/>
                            <form:options itemValue="id" items="${staffUsers}" itemLabel="fullName"/>
                        </form:select>
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