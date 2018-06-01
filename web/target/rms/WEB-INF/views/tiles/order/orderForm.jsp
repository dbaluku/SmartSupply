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

                            <input id="quantities" name="quantities" type="hidden"/>
                            <div class="row">
                                <label class="col-sm-4 col-lg-2 col-form-label">Customer</label>
                                <div class="col-sm-8 col-lg-6">
                                    <div class="input-group">
                                        <form:select path="customer" class="form-control" id="customerid">
                                            <form:option value="" label=""/>
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
                                            <select id= "id_select_1" name="products[0].product.id"  class="tabledit-input form-control product-select ">
                                                <option value=""></option>
                                                <c:forEach items="${products}" var="item">
                                                    <option  value="${item.product.id}">${item.product.name}</option>
                                                </c:forEach>
                                            </select>
                                            </td>
                                            <td><input id="id_quantity_1" class="tabledit-input form-control input-sm amount_calc numberinput"
                                                       type="text" name="products[0].quantity" value="0" />

                                            </td>
                                            <td><input id="id_unitprice_1" class=" form-control input-sm price-select" type="text" name="orderitem[].product.unitPrice" readonly="true"/>
                                                <select style='display:none;'>
                                                <option id="0" value="0">0</option>
                                                <c:forEach items="${products}" var="item">
                                                    <option id="${item.product.id}"  value="${item.product.unitprice}">${item.product.unitprice}</option>
                                                </c:forEach>
                                            </select></td>

                                            <td><input id="id_total_1" value="0" class=" form-control input-sm" type="text" name="item_amount" readonly="true"  /></td>
                                            <td><a class="btn btn-xs delete-record" data-id="1"><span style="color: #FF0000">Delete</span></a></td>

                                        </tr>
                                    </tbody>
                                </table>
                                <form:input id="final_amount" class="tabledit-input form-control input-sm" path="total_amount"
                                            style='font-weight: bold;color:red;font-size:18px;' readonly="true" />
                                <%--<a href="#" id="addPerson">Add Person</a>&nbsp;&nbsp;--%>
                                <%--<a href="?f=">Reset List</a>--%>

                            </div>
                            <a class="btn btn-primary pull-right add-record waves-light" data-added="0">Add Row </a>
                        </form:form>

                        <div style="display:none;">
                            <table id="sample_table">
                                <tr id="rec-0">
                                    <td>
                                        <select id= "id_select_0" name="products[0].product.id"  class="product-select tabledit-input form-control ">
                                            <option value=""></option>
                                            <c:forEach items="${products}" var="item">
                                                <option  value="${item.product.id}">${item.product.name}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td><input id="id_quantity_0" class="tabledit-input form-control input-sm amount_calc numberinput"
                                               type="text" name="products[0].quantity" value="0" />
                                        <select class="stock_quantity" style='display:none;'>
                                            <option id="0" value="0">0</option>
                                            <c:forEach items="${products}" var="item">
                                                <option id= "${item.product.id}"  value="${item.quantity}">${item.quantity}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td><input id="id_unitprice_0" class=" form-control input-sm price-select" type="text" name="orderitem[0].product.unitPrice" readonly="true"/>
                                        <select style='display:none;'>
                                            <option id="0" value="0">0</option>
                                            <c:forEach items="${products}" var="item">
                                                <option id="${item.product.id}"  value="${item.product.unitprice}">${item.product.unitprice}</option>
                                            </c:forEach>
                                        </select></td>

                                    <td><input id="id_total_0" class=" form-control input-sm" type="text"
                                               name="item_amount" readonly="true" value="0"  /></td>
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
