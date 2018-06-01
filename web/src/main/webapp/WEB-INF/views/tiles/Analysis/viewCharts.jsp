<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"  %>


<!DOCTYPE html>
<html lang="en">
<head>
    <title>Application Quality Dashboard</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <%--<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"  media="screen" />--%>
    <%--<link href="<c:url value="/resources/css/cerulean.bootstrap.min.css" />" rel="stylesheet"  media="screen" />--%>
    <%--<link href="<c:url value="/resources/css/stickyfooter.css" />" rel="stylesheet"  media="screen" />--%>

    <script type="text/javascript" src="<c:url value="/static/js/jquery-1.10.2.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/static/js/highcharts.js" />"></script>
    <script type="text/javascript" src="<c:url value="/static/js/exporting.js" />"></script>
    <script type="text/javascript" src="<c:url value="/static/js/analyticsChart.js" />"></script>

</head>
<body>
<div id="wrap">
    <!-- NAVBAR -->
    <!-- Docs master nav -->
   <br/><br/><br/><br/>

    <!-- CONTAINER -->
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-6">
                <div id="ItemsSold">
                </div>
            </div>


            </div>
            <div class="card-footer small text-muted">Updated </div>
        </div>
    </div>



<!-- Footer
=================================-->
<div id="footer">
    <div class="container">
        <div class="row">
            <p class="text-center"><small>Copyright &copy; 2013 <a href="http://some.link.com">SomeLink Inc.</a> All rights reserve.</small></p>
        </div>

    </div>
</div>

<!-- /Footer
=================================-->

</body>
</html>
