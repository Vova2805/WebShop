<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="cart-view-area">
    <div class="cart-view-table">
        <form action="">
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <sec:authorize access="hasAnyRole('SUPERADMIN','ADMIN')">
                            <th></th>
                            <th>Customer</th>
                        </sec:authorize>
                        <th>Date</th>
                        <th>Status</th>
                        <th>Paid</th>
                        <th>Order Total</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${orders}" var="order">
                        <c:set var="order" value="${order}" scope="request"/>
                        <c:set var="currency" value="${currency}" scope="request"/>
                        <jsp:include page="ordersContent.jsp"/>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </form>
    </div>
</div>