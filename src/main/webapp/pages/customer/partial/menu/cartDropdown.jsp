<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<a class="dropdown-toggle" data-toggle="dropdown">
    <i class="fa fa-cart-plus" id="cartMenu2"></i>
    <span id="cartMenuSpan" class="label label-success">${menuCarts.size()}</span>
</a>
<ul class="dropdown-menu">
    <c:choose>
        <c:when test="${menuCarts.size()==0}">
            <li class="header">Your cart is empty</li>
        </c:when>
        <c:otherwise>
            <li class="header">You have ${menuCarts.size()} goods in cart</li>
            <li>
                <c:set var="items" value="${menuCarts}" scope="request"/>
                <c:set var="isCart" value="${true}" scope="request"/>
                <c:set var="href" value="/profile?block=cart" scope="request"/>
                <jsp:include page="userMenuContent.jsp"/>
            </li>
        </c:otherwise>
    </c:choose>
    <li class="footer"><a href="/profile?block=cart">View all carts</a></li>
</ul>