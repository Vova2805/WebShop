<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    $(document).ready();
</script>
<link rel="stylesheet" href="/resources/plugins/select2/select2.min.css">
<script src="/resources/plugins/select2/select2.full.min.js"></script>
<section class="content-header">
    <h1>
        WebShop Admin Orders
    </h1>
    <ol class="breadcrumb">
        <li><a class="cursor-hand" href=/admin?block=dashboard><i
                class="fa fa-dashboard"></i> <span>Dashboard</span></a></li>
        <li><a class="cursor-hand" href=/admin?block=full><i
                class="fa fa-dashboard"></i> <span>Content treeview</span></a></li>
        <li class="active">Orders</li>
    </ol>
</section>
<div class="row-fluid">
    <section id="cart-view">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <c:choose>
                        <c:when test="${empty orders}">
                            <c:set var="mainHeader" value="Orders list is empty" scope="request"/>
                            <c:set var="body" value="When customers make their orders you will see them here"
                                   scope="request"/>
                            <c:set var="buttonVisible" value="false" scope="request"/>
                            <jsp:include page="../../../customer/partial/emptyListMessagePartial.jsp"/>
                        </c:when>
                        <c:otherwise>
                            <jsp:include page="../../../customer/partial/ordersList.jsp"/>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </section>
</div>