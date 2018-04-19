<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 28-Mar-18
  Time: 8:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions' %>

<div class="pcoded-inner-content">
    <!-- Main-body start -->
    <div class="main-body">
        <div class="page-wrapper">
            <div class="page-body">
                <div class="card">
                    <div class="card-header">
                        <h5>Add StockProduct Form</h5>
                    </div>
                    <div class="card-block">
                        <form:form action="${baseUrl }/stockproducts/saveproduct" commandName="objectKey" method="POST">
                            <form:input type="hidden" path="id"/>
                            <form:input type="hidden" path="stock"/>
                            <div class="row">
                                <label class="col-sm-4 col-lg-2 col-form-label">Product</label>
                                <div class="col-sm-8 col-lg-6">
                                    <div class="input-group">
                                        <form:select  path="product" name="product" class="form-control">
                                            <form:option value="none" label=""/>
                                            <form:options itemValue="id" items="${productlist}" itemLabel="name"/>
                                        </form:select>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <label class="col-sm-4 col-lg-2 col-form-label">Quantity</label>
                                <div class="col-sm-8 col-lg-6">
                                    <div class="input-group">
                                        <form:input  path="quantity" class="form-control"/>                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <label class="col-sm-4 col-lg-6 col-form-label"></label>
                                <div class="col-sm-2 col-lg-2">
                                    <div class="input-group">
                                        <input  type="submit" value="Save" class="btn btn-block"/>
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
