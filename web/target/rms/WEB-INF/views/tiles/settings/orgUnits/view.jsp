<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions' %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<link rel="stylesheet" type="text/css" href="${baseUrl}/static/files/bower_components/datatables.net/css/jquery.dataTables.min.css"/>
<link rel="stylesheet" type="text/css" href="${baseUrl}/static/files/bower_components/datatables.net-bs4/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet" type="text/css" href="${baseUrl}/static/files/assets/pages/data-table/css/buttons.dataTables.min.css">
<%--<link rel="stylesheet" type="text/css" href="${baseUrl}/static/files/bower_components/datatables.net-responsive-bs4/css/responsive.bootstrap4.min.css">--%>


<script src="${baseUrl }/static/files/bower_components/jquery/js/jquery.min.js"></script>
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

<script>
    $(document).ready(function() {
            $('#example').DataTable( {
            dom: 'Bfrtip',
            buttons: [
                'copy', 'csv', 'excel', 'pdf', 'print'
            ]
        } );
    } );
</script>
<div class="row">
    <div class="col-sm-12">
        <!-- HTML (DOM) Sourced Data table start -->
        <div class="card">
            <div class="card-header">
                <div class="btn btn-grd-primary">
                    <a href="${baseUrl}/org-units/add">Add</a>
                    <div style="clear: both;"></div>
                </div>
            </div>
            <div class="card-block">
                <div class="dt-responsive table-responsive">
                    <c:if test="${not empty orgUnits  && fn:length(orgUnits) > 0}">
                    <table id="example" class="table table-striped table-bordered nowrap">
                        <thead>
                        <tr>
                            <th>Branch Name</th>
                            <th>Abbreviation</th>
                            <th>Type</th>
                            <th>Parent</th>
                            <th>Head</th>
                            <th>AG.Head</th>
                            <th>Location</th>
                        </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="orgUnit" items="${orgUnits}">
                                <tr id="${orgUnit.id }">
                                    <td>${orgUnit.name}</td>
                                    <td>${orgUnit.abbreviation}</td>
                                    <td><c:if test="${not empty orgUnit.type}">${orgUnit.type.name}</c:if></td>
                                    <td>
                                        <c:if test="${not empty orgUnit.parent }">
                                            ${orgUnit.parent.abbreviation}
                                        </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${not empty orgUnit.head }">
                                            <c:choose>
                                                <c:when test="${not empty orgUnit.headTitle }">
                                                    ${orgUnit.headTitle.name}
                                                </c:when>
                                                <c:otherwise>
                                                    <b style="color:red;">(No Title!!)</b>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${not empty orgUnit.head }">
                                            ${orgUnit.head.fullName}
                                        </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${not empty orgUnit.location }">
                                            ${orgUnit.location }
                                        </c:if>
                                    </td>

                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    </c:if>
                    <c:if test="${empty orgUnits}">
                        <h3>
                            <font color="#ff0000">No Branches found</font>
                        </h3>
                    </c:if>
                </div>
            </div>
        </div>

    </div>
</div>






