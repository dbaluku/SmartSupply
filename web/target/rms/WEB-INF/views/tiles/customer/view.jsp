<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 26-Apr-18
  Time: 9:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" isELIgnored="false" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions' %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row">
    <div class="col-sm-12">
        <!-- HTML (DOM) Sourced Data table start -->
        <div class="card">
            <div class="card-header">
                <div class="btn btn-grd-primary">
                    <a href="${baseUrl}/customer/add">Add</a>
                    <div style="clear: both;"></div>
                </div>
            </div>
            <div class="card-block">
                <div class="dt-responsive table-responsive">
                    <c:if test="${not empty customers  && fn:length(customers) > 0}">
                        <table id="example" class="table table-striped table-bordered nowrap">
                            <thead>
                            <tr>
                                <th><strong>Customer Name</strong></th>
                                <th><strong>Phone</strong></th>
                                <th><strong>Address</strong></th>

                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="customer" items="${customers }">
                                <tr>
                                    <td>${customer.name}</td>
                                    <td>${customer.phoneNo}</td>

                                    <td>${product.Address}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${empty customers}">
                        <h3>
                            <font color="#ff0000">No Customers found</font>
                        </h3>
                    </c:if>
                </div>
            </div>
        </div>

    </div>
</div>

