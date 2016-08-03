<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    $(document).ready();
</script>
<section class="content-header">
    <h1>
        My orders
    </h1>
</section>
<section class="content">
    <div class="row-fluid">
        <div class="col-xs-12">
            <c:choose>
                <c:when test="${empty activeUser.productOrdersByCustomerId}">
                    <c:set var="mainHeader" value="Your did not order anything" scope="request"/>
                    <c:set var="body" value="Make some order and it will be displayed here" scope="request"/>
                    <jsp:include page="emptyListMessagePartial.jsp"/>
                </c:when>
                <c:otherwise>
                    <jsp:include page="ordersList.jsp"/>
                </c:otherwise>
            </c:choose>

        </div>
    </div>

</section>
