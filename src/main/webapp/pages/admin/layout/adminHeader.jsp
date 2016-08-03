<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=utf-8" %>


<%--<div id="wpf-loader-two">--%>
<%--<div class="sk-cube-grid">--%>
<%--<div class="sk-cube sk-cube1"></div>--%>
<%--<div class="sk-cube sk-cube2"></div>--%>
<%--<div class="sk-cube sk-cube3"></div>--%>
<%--<div class="sk-cube sk-cube4"></div>--%>
<%--<div class="sk-cube sk-cube5"></div>--%>
<%--<div class="sk-cube sk-cube6"></div>--%>
<%--<div class="sk-cube sk-cube7"></div>--%>
<%--<div class="sk-cube sk-cube8"></div>--%>
<%--<div class="sk-cube sk-cube9"></div>--%>
<%--</div>--%>
<%--</div>--%>

<%--<a class="scrollToTop" href="#"><i class="fa fa-chevron-up"></i></a>--%>

<header class="main-header border-bottom">

    <a href="/" class="logo">
        <span class="logo-mini"><b>W</b>S</span>
        <span class="logo-lg"><b>Web</b>Shop <sec:authorize access="!hasRole('USER')">Admin</sec:authorize></span>
    </a>

    <nav class="navbar navbar-static-top">
        <a href="#" class="sidebar-toggle border-right" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
        </a>
        <jsp:include page="../../shop/layout/headerOption.jsp"/>
    </nav>
</header>


