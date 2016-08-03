<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    $.ready();
</script>

<aside class="main-sidebar border-right min-height-100p">
    <section class="sidebar">
        <ul class="sidebar-menu">
            <c:set var="showSubTree" value="true" scope="request"/>
            <jsp:include page="adminSideMenuContent.jsp"/>
        </ul>
    </section>
</aside>
