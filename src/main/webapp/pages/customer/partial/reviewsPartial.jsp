<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    $(document).ready();
</script>

<section class="content-header">
    <h1>
        Reviewed goods
    </h1>
</section>

<section id="aa-product-category">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <c:choose>
                    <c:when test="${empty activeUser.viewedProductsByCustomerId}">
                        <c:set var="mainHeader" value="Your did not review any product" scope="request"/>
                        <c:set var="body" value="Any product you are interested will be displayed here"
                               scope="request"/>
                        <jsp:include page="emptyListMessagePartial.jsp"/>
                    </c:when>
                    <c:otherwise>

                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</section>



