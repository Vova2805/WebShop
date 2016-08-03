<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script>
    $(document).ready();
</script>

<section class="content-header">
    <h1>
        WebShop Admin Dashboard
    </h1>
    <ol class="breadcrumb">
        <li><a class="cursor-hand"><i
                class="fa fa-dashboard"></i> <span>Dashboard</span></a></li>
        <li class="active">Categories</li>
    </ol>
</section>


<section class="content">
    <div class="row">
        <div class="col-md-3 invisible-scrollbar padding-top-10" id="adminContentTreeviewContainer">
            <c:set var="categories" value="${categories}" scope="request"/>
            <jsp:include page="/pages/admin/partial/content/treeview/adminContentTreeview.jsp"/>
        </div>
        <div class="col-md-9 added-scrollbar invisible-scrollbar" style="padding-left: 0px;">
            <section id="aa-product-category">
                <div class="row-fluid">
                    <div class="col-md-12" style="padding-left: 0px;">
                        <div class="aa-product-catg-content no-padding" id="contentInner">
                            <c:if test="${not empty type}">
                                <c:choose>
                                    <c:when test="${!type.equals('goods')}">
                                        <jsp:include page="fullContentSingleItem.jsp"/>
                                    </c:when>
                                    <c:otherwise>
                                        <jsp:include page="fullContentSingleProduct.jsp"/>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </div>
</section>




