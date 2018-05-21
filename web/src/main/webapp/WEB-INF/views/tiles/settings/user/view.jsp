<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 27-Mar-18
  Time: 9:02 AM
  To change this template use File | Settings | File Templates.
--%>

<%@page language="java" isELIgnored="false" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>

<html>
<head>
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
</head>
<body>
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
                    <a href="${baseUrl}/staff-users/add">Add User</a>
                    <div style="clear: both;"></div>
                </div>
            </div>
            <div class="card-block">
                <div class="dt-responsive table-responsive">
                    <c:if test="${not empty staffUsers  && fn:length(staffUsers) > 0}">
                        <table id="example" class="table table-striped table-bordered nowrap">
                            <thead>
                            <tr>
                                <th>Name</th>
                                <th>Username</th>
                                <th>Role(s)</th>
                                <th>Gender</th>
                                <th>User Type</th>
                                <th>Dept/Unit</th>
                                <th>Job Titles</th>
                            </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="user" items="${staffUsers }">
                                    <tr id="${user.id }">

                                        <td>${user.fullName }</td>
                                        <td>${user.username }</td>
                                        <td class="allow-wrap">${user.roleString }</td>
                                        <td>${user.gender }</td>
                                        <td>${user.userType.name }</td>
                                        <td class="allow-wrap">${user.branch.abbreviation }</td>
                                        <td>${user.jobTitleString }</td>
                                        <%--<td><commonTags:editdeletebuttons name="Staff-User" url="${activeItem}" id="${user.id }"/></td>--%>

                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${empty staffUsers}">
                        <h3>
                            <font color="#ff0000">No Users found</font>
                        </h3>
                    </c:if>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
</html>