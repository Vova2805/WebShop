<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=utf-8" %>

<a class="scrollToTop" href="#"><i class="fa fa-chevron-up"></i></a>

<header class="main-header border-bottom">

    <a href="/" class="logo">
        <span class="logo-mini"><b>W</b>S</span>
        <span class="logo-lg"><b>Web</b>Shop</span>
    </a>

    <nav class="navbar navbar-static-top" id="headerOptionsContainer">
        <jsp:include page="headerOption.jsp"/>
    </nav>

</header>

