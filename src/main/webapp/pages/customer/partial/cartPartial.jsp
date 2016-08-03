<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    $(document).ready();
</script>

<section class="content-header">
    <h1>
        My cart
    </h1>
</section>

<section id="cart-view">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <c:choose>
                    <c:when test="${empty activeUser.cartsByCustomerId}">
                        <c:set var="mainHeader" value="Your cart is empty" scope="request"/>
                        <c:set var="body" value="Add something to cart and come back" scope="request"/>
                        <jsp:include page="emptyListMessagePartial.jsp"/>
                    </c:when>
                    <c:otherwise>
                        <div class="cart-view-area">
                            <div class="cart-view-table">
                                <form action="">
                                    <div class="table-responsive">
                                        <table class="table">
                                            <thead>
                                            <tr>
                                                <th style="width: 50px"></th>
                                                <th></th>
                                                <th>Product</th>
                                                <th>Price</th>
                                                <th>Quantity</th>
                                                <th>Total</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${activeUser.cartsByCustomerId}" var="cart">
                                                <c:set var="product" value="${cart.productByProductId}"/>
                                                <tr>
                                                    <td style="width: 50px">
                                                        <a class="remove cursor-hand" data-toggle="tooltip"
                                                           data-placement="right"
                                                           title="Remove cart item"
                                                           onclick="removeCartItem('${cart.cartProductId}')">
                                                            <fa class="fa fa-close"></fa>
                                                        </a>
                                                    </td>
                                                    <td><a href="/goods/${product.productId}"><img
                                                            src="${product.firstImage.images['small']}"></a>
                                                    </td>
                                                    <td><a class="aa-cart-title"
                                                           href="/goods/${product.productId}">${product.title}</a></td>
                                                    <td>${currency} ${product.price}</td>
                                                    <td><input id="amount${cart.cartProductId}" class="aa-cart-quantity"
                                                               type="number" min="1" max="1000" step="1"
                                                               placeholder="Amount" value="${cart.productAmount}"></td>
                                                    <td id="result${cart.cartProductId}">${currency} ${cart.productAmount * product.price}</td>
                                                </tr>
                                                <script>
                                                    $('#amount${cart.cartProductId}').on("input", function () {
                                                        var price = ${cart.productByProductId.price};
                                                        var amount = ($("#amount${cart.cartProductId}").val());
                                                        var total = price * amount;
                                                        var outputStr = "${currency}" + total;
                                                        $("#result${cart.cartProductId}").text(outputStr);
                                                        changeCartQuantity('${cart.cartProductId}', amount);
                                                        checkAmount();
                                                    });
                                                    function checkAmount() {
                                                        if (amount >${cart.productByProductId.availableAmount}) {
                                                            $("#amount${cart.cartProductId}").css("color", "red");
                                                        }
                                                        else {
                                                            $("#amount${cart.cartProductId}").css("color", "black");
                                                        }
                                                    }
                                                    checkAmount();
                                                </script>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </form>
                                <div class="cart-view-total">
                                    <h4>Cart Totals</h4>
                                    <table class="aa-totals-table">
                                        <tbody>
                                        <tr>
                                            <th>Total</th>
                                            <td id="total">${currency} ${total}</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                    <a href="/profile/checkout" class="aa-cart-view-btn">Proceed to Checkout</a>
                                </div>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</section>
<script>

    function changeCartQuantity(cartId, amount) {
        $.ajax({
            type: "POST",
            url: 'api/cart/' + cartId,
            dataType: "json",
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify({amount: amount}),
            success: function (response) {
                $.ajax({
                    type: "GET",
                    url: 'api/cart/total',
                    dataType: "json",
                    success: function (response) {
                        $("#total").text("${currency} " + response.total);
                    },
                    error: function (code) {
                        console.log(code);
                    }
                });
            },
            error: function (code) {
                console.log(code);
            }
        });
    }
    function removeCartItem(cartId) {
        $.ajax({
            type: "DELETE",
            url: 'api/cart/' + cartId,
            dataType: "json",
            success: function (response) {
                location.reload();
            },
            error: function (code) {
                console.log(code);
            }
        });
    }
</script>




