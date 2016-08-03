<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section class="content-header">
    <h1>
        My compared goods
    </h1>
</section>

<section id="aa-product-category">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <c:choose>
                    <c:when test="${empty activeUser.comparisonsByCustomerId}">
                        <c:set var="mainHeader" value="Your comparisons list is empty" scope="request"/>
                        <c:set var="body" value="Add something to comparison list and come back" scope="request"/>
                        <jsp:include page="emptyListMessagePartial.jsp"/>
                    </c:when>
                    <c:otherwise>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</section>




