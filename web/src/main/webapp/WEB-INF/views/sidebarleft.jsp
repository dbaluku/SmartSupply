<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 27-Apr-18
  Time: 1:51 PM
  To change this template use File | Settings | File Templates.
--%>

<!-- Sidebar FOR MENU DISPLAY-->
<div class="pcoded-main-container">
    <div class="pcoded-wrapper">
        <nav class="pcoded-navbar">
            <div class="sidebar_toggle"><a href="#"><i class="icon-close icons"></i></a></div>
            <div class="pcoded-inner-navbar main-menu">
                <div class="">
                    <div class="main-menu-header">
                        <img class="img-80 img-radius" src="${baseUrl}/static/images/avatar-4.jpg" alt="User-Profile-Image">
                        <div class="user-details">

                        </div>
                    </div>
                    <div class="pcoded-navigation-label">Menu Items</div>
                    <ul class="pcoded-item pcoded-left-item">
                        <li class="pcoded-hasmenu">
                            <a href="javascript:void(0)" class="waves-effect waves-dark">
                                <span class="pcoded-micon"><i class="ti-layout"></i><b>P</b></span>
                                <span class="pcoded-mtext">Products</span>
                                <span class="pcoded-badge label label-warning">NEW</span>
                                <span class="pcoded-mcaret"></span>
                            </a>
                            <ul class="pcoded-submenu">
                                <li class=" pcoded-hasmenu">
                                    <a href="/product/view" class="waves-effect waves-dark">
                                        <span class="pcoded-micon"><i class="icon-pie-chart"></i></span>
                                        <span class="pcoded-mtext">View </span>
                                        <span class="pcoded-mcaret"></span>
                                    </a>
                                </li>
                                <li class=" pcoded-hasmenu">
                                    <a href="/product/add" class="waves-effect waves-dark">
                                        <span class="pcoded-micon"><i class="icon-pie-chart"></i></span>
                                        <span class="pcoded-mtext">Add  </span>
                                        <span class="pcoded-mcaret"></span>
                                    </a>
                                </li>
                            </ul>
                        </li>

                        <li class="pcoded-hasmenu">
                            <a href="javascript:void(0)" class="waves-effect waves-dark">
                                <span class="pcoded-micon"><i class="ti-layout"></i><b>P</b></span>
                                <span class="pcoded-mtext">Stock</span>
                                <span class="pcoded-badge label label-warning">NEW</span>
                                <span class="pcoded-mcaret"></span>
                            </a>
                            <ul class="pcoded-submenu">
                                <li class=" pcoded-hasmenu">
                                    <a href="/stockproducts/view" class="waves-effect waves-dark">
                                        <span class="pcoded-micon"><i class="icon-pie-chart"></i></span>
                                        <span class="pcoded-mtext">View </span>
                                        <span class="pcoded-mcaret"></span>
                                    </a>
                                </li>
                                <li class=" pcoded-hasmenu">
                                    <a href="/stockproducts/add" class="waves-effect waves-dark">
                                        <span class="pcoded-micon"><i class="icon-pie-chart"></i></span>
                                        <span class="pcoded-mtext">Add  </span>
                                        <span class="pcoded-mcaret"></span>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li class="pcoded-hasmenu">
                            <a href="javascript:void(0)" class="waves-effect waves-dark">
                                <span class="pcoded-micon"><i class="ti-layout"></i><b>P</b></span>
                                <span class="pcoded-mtext">Users</span>
                                <span class="pcoded-badge label label-warning">NEW</span>

                            </a>
                            <ul class="pcoded-submenu">
                                <li class=" pcoded-hasmenu">
                                    <a href="/staff-users/view" class="waves-effect waves-dark">
                                        <span class="pcoded-micon"><i class="icon-pie-chart"></i></span>
                                        <span class="pcoded-mtext">View </span>
                                        <span class="pcoded-mcaret"></span>
                                    </a>
                                </li>
                                <li class=" pcoded-hasmenu">
                                    <a href="/staff-users/add" class="waves-effect waves-dark">
                                        <span class="pcoded-micon"><i class="icon-pie-chart"></i></span>
                                        <span class="pcoded-mtext">Add  </span>
                                        <span class="pcoded-mcaret"></span>
                                    </a>
                                </li>
                            </ul>
                        </li>

                        <li class="pcoded-hasmenu">
                            <a href="javascript:void(0)" class="waves-effect waves-dark">
                                <span class="pcoded-micon"><i class="ti-layout"></i><b>P</b></span>
                                <span class="pcoded-mtext">Branches</span>
                                <span class="pcoded-badge label label-warning">NEW</span>
                                <span class="pcoded-mcaret"></span>
                            </a>
                            <ul class="pcoded-submenu">
                                <li class=" pcoded-hasmenu">
                                    <a href="/org-units/view" class="waves-effect waves-dark">
                                        <span class="pcoded-micon"><i class="icon-pie-chart"></i></span>
                                        <span class="pcoded-mtext">View </span>
                                        <span class="pcoded-mcaret"></span>
                                    </a>
                                </li>
                                <li class=" pcoded-hasmenu">
                                    <a href="/org-units/add" class="waves-effect waves-dark">
                                        <span class="pcoded-micon"><i class="icon-pie-chart"></i></span>
                                        <span class="pcoded-mtext">Add  </span>
                                        <span class="pcoded-mcaret"></span>
                                    </a>
                                </li>
                            </ul>
                        </li>



                    </ul>
                </div>
            </div>
        </nav>
        <div class="pcoded-content">
            <div id="content">
                <div style="clear: both;"></div>
                <div id="content-body">
                    <tiles:insertAttribute name="body"/>
                </div>
            </div>
        </div>
    </div>
</div>
