<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 24-Apr-18
  Time: 10:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>

<link rel="stylesheet" type="text/css" href="${baseUrl}/static/files/bower_components/datatables.net/css/jquery.dataTables.min.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrl}/static/files/bower_components/datatables.net-bs4/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet" type="text/css" href="${baseUrl}/static/files/assets/pages/data-table/css/buttons.dataTables.min.css">
<%--<link rel="stylesheet" type="text/css" href="${baseUrl}/static/files/bower_components/datatables.net-responsive-bs4/css/responsive.bootstrap4.min.css">--%>


<%--<script src="${baseUrl }/static/files/bower_components/jquery/js/jquery.min.js"></script>--%>
<script src="${baseUrl}/static/files/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="${baseUrl}/static/files/bower_components/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
<script src="${baseUrl}/static/files/assets/pages/data-table/js/jszip.min.js"></script>
<script src="${baseUrl}/static/files/assets/pages/data-table/js/pdfmake.min.js"></script>
<script src="${baseUrl}/static/files/assets/pages/data-table/js/vfs_fonts.js"></script>
<script src="${baseUrl}/static/files/bower_components/datatables.net-buttons/js/buttons.print.min.js"></script>
<script src="${baseUrl}/static/files/bower_components/datatables.net-buttons/js/buttons.html5.min.js"></script>
<script src="${baseUrl}/static/files/bower_components/datatables.net-bs4/js/dataTables.bootstrap4.min.js"></script>
<script src="${baseUrl}/static/files/bower_components/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
<%--<script src="${baseUrl}/static/files/bower_components/datatables.net-responsive-bs4/js/responsive.bootstrap4.min.js"></script>--%>


<%--<script>--%>
<%--$(document).ready(function() {--%>
<%--$('#example').DataTable( {--%>
<%--dom: 'Bfrtip',--%>
<%--buttons: [--%>
<%--'copy', 'csv', 'excel', 'pdf', 'print'--%>
<%--]--%>
<%--} );--%>
<%--} );--%>
<%--</script>--%>
<div class="row">
    <div class="col-sm-12">
        <!-- HTML (DOM) Sourced Data table start -->
        <div class="card">
            <div class="card-header">
                <div class="btn btn-grd-primary">
                    <a href="${baseUrl}/order/add">Add</a>
                    <div style="clear: both;"></div>
                </div>
            </div>
            <div class="card-block">
                <div class="dt-responsive table-responsive">
                    <c:if test="${not empty orders  && fn:length(orders) > 0}">
                        <table id="example" class="table table-striped table-bordered nowrap">
                            <thead>
                            <tr>
                                <th>Date of Sale</th>
                                <th>Sales Rep.</th>
                                <th>Total Amount</th>
                                <th>Products </th>

                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="order" items="${orders }" varStatus="status">
                                <tr id="${order.id }">

                                    <td>${order.date_of_ordering }</td>
                                    <td>${order.sales_man.firstName }</td>
                                    <td>${order.total_amount}</td>
                                    <td>
                                    <c:forEach var="item" items="${order.products}" >
                                        ${item.product.name} ,
                                    </c:forEach>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${empty orders}">
                        <h3>
                            <font color="#ff0000">No Orders found</font>
                        </h3>
                    </c:if>
                </div>
            </div>
        </div>

    </div>
</div>

