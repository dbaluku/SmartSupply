<%--
  Created by IntelliJ IDEA.
  User: CODE
  Date: 25/03/2018
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" isELIgnored="false" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions' %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--<%@ taglib prefix="sysFormFragments" tagdir="/WEB-INF/tags/form-fragments" %>--%>
<%--<%@ taglib prefix="breadcrumbTags" tagdir="/WEB-INF/tags/breadcrumbs"%>--%>

<div class="pcoded-inner-content">
    <!-- Main-body start -->
    <div class="main-body">
        <div class="page-wrapper">
            <div class="page-body">
                <div class="card">
                    <div class="card-header">
                        <h5>Add Product Form</h5>
                    </div>
                    <div class="card-block">
                        <form:form action="${baseUrl }/product/save" commandName="product"  enctype="multipart/form-data">

                            <div class="row">
                                <label class="col-sm-4 col-lg-2 col-form-label">Name</label>
                                <div class="col-sm-8 col-lg-6">
                                    <div class="input-group">
                                        <form:input type="text" path="name" id="txtName" class="form-control" placeholder="Name"/>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <label class="col-sm-4 col-lg-2 col-form-label">Product Type:</label>
                                <div class="col-sm-8 col-lg-6">
                                    <div class="input-group">
                                        <form:select  path="productType" items="${productTypes}" itemLabel="name" class="form-control">
                                        </form:select>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <label class="col-sm-4 col-lg-2 col-form-label">Unit Price:</label>
                                <div class="col-sm-8 col-lg-6">
                                    <div class="input-group">
                                        <form:input  path="unitprice" class="form-control" />
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <label class="col-sm-4 col-lg-2 col-form-label">Quantity Type</label>
                                <div class="col-sm-8 col-lg-6">
                                    <div class="input-group">
                                        <form:select  path="quantityType" class="form-control" items="${quantityTypes}" itemLabel="name">
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