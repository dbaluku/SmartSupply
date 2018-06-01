<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions'%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%--<%@ taglib prefix="rmsTags" tagdir="/WEB-INF/tags"%>--%>


<link href="${baseUrl }/static/css/sb-admin.css" rel="stylesheet"/>
<link href="${baseUrl }/static/css/style.css" rel="stylesheet"/>
<link href="${baseUrl }/static/css/bootstrap.min.css" rel="stylesheet"/>
<%--<%--%>
    <%--try {--%>
        <%--user = RmsSecurityUtil.getLoggedInUser();--%>
        <%--if (user != null) {--%>
            <%--pageContext.setAttribute("loggedInLecturer", user);--%>
        <%--}--%>
    <%--} catch (Exception ex) {--%>
        <%--/* ex.printStackTrace(); */--%>
    <%--}--%>

<%--%>    User user = null;--%>
 <%----%>

<div class="container-fluid">
    <form action="${baseUrl }/getAnalysisForms/branchPerformance"  method="POST" role="form"  >
        <div class="panel panel-success">
            <div class="panel-heading" align="center">
                <h4><b><font color="black" style="font-family: fantasy;">Select the Branch</font> </b></h4>
            </div>
            <div class="panel-body">
                <div class="container " style="margin-top: 10%; margin-bottom: 10%;">
                    <div class ="row" align="center">

                        <label class="col-md-3 control-label">Branch</label>

                        <div class="col-md-3">
                            <select name="branches" class="form-control" >
                                <c:forEach items="${branches}" var="branch">
                                    <option value="${branch.id}">${branch.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <p></p>


                </div>
                <p></p>
                <div class="row" align="center">
                    <label class="col-md-3 control-label"></label>
                    <div class="col-md-3">
                        <input  type="submit" value="Search" id="btn-login"  class="btn btn-block btn-success" />
                    </div>

                </div>
            </div>


        </div> <!--end panel -->
</form>

</div>


