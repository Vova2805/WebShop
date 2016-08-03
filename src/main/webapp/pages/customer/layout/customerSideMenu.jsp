<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    $.ready();
    $(".collapsable").click(function () {
        var $this = this;
        var liParrent = getParentByTagName($this, 'li');
        $(liParrent).toggleClass("active");
    });
</script>

<aside class="main-sidebar border-right">
    <section class="sidebar">
        <ul class="sidebar-menu">
            <jsp:include page="customerSideMenuContent.jsp"/>
        </ul>
    </section>
</aside>
