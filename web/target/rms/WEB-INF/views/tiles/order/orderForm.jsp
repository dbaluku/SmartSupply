<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 24-Apr-18
  Time: 10:00 AM
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
                        <h5> Order Form</h5>
                    </div>
                    <div class="card-block">
                        <form:form method="post" action="${baseUrl}/order/saveorder" modelAttribute="newOrder" id="myform" >

                            <div class="row">
                                <label class="col-sm-4 col-lg-2 col-form-label">Customer</label>
                                <div class="col-sm-8 col-lg-6">
                                    <div class="input-group">
                                        <form:select path="customer" class="form-control">
                                            <form:option value="none" label=""/>
                                            <form:options itemValue="id" items="${customers}" itemLabel="name"/>
                                        </form:select>
                                    </div>
                                </div>
                                <div class="col-sm-2 col-lg-2">
                                    <div class="input-group">
                                        <input id="btnSave" type="submit" value="Save" class="btn btn-block"/>
                                    </div>
                                </div>
                            </div>
                            <div>
                                <p></p>
                            </div>

                            <div class="table-responsive">
                                <table class="table table-striped table-bordered" id="order_table">
                                    <thead>
                                    <tr>
                                        <th>Item</th>
                                        <th>Quantity</th>
                                        <th>UnitPrice</th>
                                        <th>Amount</th>
                                        <th>Action</th>
                                    </tr>
                                    </thead>

                                    <tbody id="order_table_body">

                                        <tr id="rec-1">
                                            <td>
                                            <select name="products[0].product.id"  class="tabledit-input form-control ">
                                                <option value=""></option>
                                                <c:forEach items="${products}" var="item">
                                                    <option  value="${item.product.id}">${item.product.name}</option>
                                                </c:forEach>
                                            </select>
                                            </td>
                                            <td><input class="tabledit-input form-control input-sm" type="text" name="products[0].quantity" /></td>
                                            <td><input class=" form-control input-sm" type="text" name="orderitem[].product.unitPrice" disabled="disabled" /></td>

                                            <td><input class=" form-control input-sm" type="text" name="item_amount" disabled="disabled"  /></td>
                                            <td><a class="btn btn-xs delete-record" data-id="1"><span style="color: #FF0000">Delete</span></a></td>

                                        </tr>
                                    </tbody>
                                </table>
                                <%--<input type="submit" value="Save" id="submit" />&nbsp;&nbsp;--%>
                                <%--<a href="#" id="addPerson">Add Person</a>&nbsp;&nbsp;--%>
                                <%--<a href="?f=">Reset List</a>--%>

                            </div>
                            <a class="btn btn-primary pull-right add-record waves-light" data-added="0">Add Row </a>
                        </form:form>

                        <div style="display:none;">
                            <table id="sample_table">
                                <tr id="">
                                    <td>
                                        <select  class="selectable form-control " name="product_id">
                                            <option value=""></option>
                                            <c:forEach items="${products}" var="item">
                                                <option  value="${item.id}" >${item.product.name}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td><input class="tabledit-input form-control input-sm" type="text" name="quantity" /></td>
                                    <td><input class=" form-control input-sm" type="text" name="orderitem[].product.unitPrice" disabled="disabled" /></td>

                                    <td><input class=" form-control input-sm" type="text" name="item_amount" disabled="disabled"  /></td>
                                    <td><a class="btn btn-xs delete-record" data-id="0"><span style="color: #FF0000">Delete</span></a></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%--<script type="text/javascript" src="${baseUrl }/static/js/jquery.min.js"></script>--%>
