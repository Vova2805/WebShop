<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="menu">
    <c:forEach items="${items}" var="item">
        <li>
            <c:set var="product" value="${item.productByProductId}"/>
            <a class="cursor-hand" href="${href}">
                <div class="pull-left">
                    <img src="${product.firstImage.images['small']}">
                </div>
                <h4>
                        ${product.title}
                </h4>
                <p>Price : ${currency} ${product.price}</p>
                <c:if test="${not empty isCart}">
                    <p>Quantity : ${item.productAmount}</p>
                </c:if>
            </a>
        </li>
    </c:forEach>
</ul>