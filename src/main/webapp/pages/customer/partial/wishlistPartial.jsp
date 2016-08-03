<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    $(document).ready();
</script>

<section class="content-header">
    <h1>
        Wishlist
    </h1>
</section>

<section class="content">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="cart-view-area">
                    <div class="aa-wishlist-table">
                        <form action="">
                            <c:choose>
                                <c:when test="${empty activeUser.wishesByCustomerId}">
                                    <c:set var="mainHeader" value="Your wish list is empty now." scope="request"/>
                                    <c:set var="body" value="Add something to wish list and come back" scope="request"/>
                                    <jsp:include page="emptyListMessagePartial.jsp"/>
                                </c:when>
                                <c:otherwise>
                                    <div class="table-responsive">
                                        <table class="table">
                                            <thead>
                                            <tr>
                                                <th></th>
                                                <th></th>
                                                <th>Product</th>
                                                <th>Price</th>
                                                <th>Stock Status</th>
                                                <th></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${activeUser.wishesByCustomerId}" var="wish">
                                                <tr>
                                                    <td><a class="remove cursor-hand">
                                                        <fa class="fa fa-close"></fa>
                                                    </a></td>
                                                    <td><a href="#"><img src="resources/img/man/polo-shirt-1.png"
                                                                         alt="img"></a>
                                                    </td>
                                                    <td><a class="aa-cart-title" href="#"></a></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td><a href="#" class="aa-add-to-cart-btn">Add To Cart</a></td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>


