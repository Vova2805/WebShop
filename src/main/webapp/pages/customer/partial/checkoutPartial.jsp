<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section class="content-header">
    <h1>
        Checkout
    </h1>
</section>
<section id="checkout">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="checkout-area">
                    <c:choose>
                        <c:when test="${carts.size()==0}">
                            <c:set var="mainHeader" value="Your cart is empty" scope="request"/>
                            <c:set var="body" value="So you can not place an order. Fill your cart first"
                                   scope="request"/>
                            <jsp:include page="emptyListMessagePartial.jsp"/>
                        </c:when>
                        <c:otherwise>
                            <form id="checkoutOrderForm" class="aa-review-form form">
                                <div class="row">
                                    <div class="col-md-8">
                                        <div class="checkout-left">
                                            <div class="panel-group" id="accordion">
                                                <div class="panel panel-default aa-checkout-coupon">
                                                    <div class="panel-heading">
                                                        <h4 class="panel-title">
                                                            <a data-toggle="collapse" data-parent="#accordion"
                                                               href="#collapseOne">
                                                                Have a Coupon?
                                                            </a>
                                                        </h4>
                                                    </div>
                                                    <div id="collapseOne" class="panel-collapse collapse">
                                                        <div class="panel-body">
                                                            <input type="text" name="cuponCode"
                                                                   placeholder="Coupon Code"
                                                                   class="aa-coupon-code">
                                                            <input type="submit" value="Apply Coupon"
                                                                   class="aa-browse-btn">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="panel panel-default aa-checkout-billaddress">
                                                    <div class="panel-heading">
                                                        <h4 class="panel-title">
                                                            <a data-toggle="collapse" data-parent="#accordion"
                                                               href="#collapseFour">
                                                                Shippping Address
                                                            </a>
                                                        </h4>
                                                    </div>
                                                    <div id="collapseFour" class="aa-review-form form">
                                                        <div class="panel-body">
                                                            <c:set var="address" value="${address}" scope="request"/>
                                                            <jsp:include page="editUserInfoPart.jsp"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="checkout-right">
                                            <h4>Order Summary</h4>
                                            <div class="aa-order-summary-area">
                                                <table class="table table-responsive">
                                                    <thead>
                                                    <tr>
                                                        <th>Product</th>
                                                        <th>Total</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach items="${carts}" var="orderItem">
                                                        <tr>
                                                            <td>${orderItem.productByProductId.title} <strong>
                                                                x ${orderItem.productAmount}</strong></td>
                                                            <td>${currency} ${orderItem.productByProductId.price*orderItem.productAmount}</td>
                                                        </tr>
                                                    </c:forEach>
                                                    </tbody>
                                                    <tfoot>
                                                    <tr>
                                                        <th>Total</th>
                                                        <td>${currency} ${total}</td>
                                                    </tr>
                                                    </tfoot>
                                                </table>
                                            </div>
                                            <h4>Payment Method</h4>
                                            <div class="aa-payment-method">
                                                <label for="cashdelivery"><input type="radio" id="cashdelivery"
                                                                                 name="optionsRadios"> Cash on Delivery
                                                </label>
                                                <label for="paypal"><input type="radio" id="paypal" name="optionsRadios"
                                                                           checked> Via Paypal </label>
                                                <img src="https://www.paypalobjects.com/webstatic/mktg/logo/AM_mc_vs_dc_ae.jpg"
                                                     border="0" alt="PayPal Acceptance Mark">
                                                <input type="submit" value="Place Order" class="aa-browse-btn">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</section>
<script>

    /**
     * Submit form
     * Generating invoice
     */
    $('#checkoutOrderForm').submit(function (e) {
        var frm = $('#checkoutOrderForm');
        e.preventDefault();
        var data = {};
        $.each(this, function (i, v) {
            var element = $(v);
            data[element.attr("name")] = element.val();
            delete data["undefined"];
        });
        $.ajax({
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            url: 'api/orders',
            dataType: "json",
            data: JSON.stringify(data),
            success: function (response) {
//                var obj = jQuery.parseJSON(response);
//                var productOrderId = obj.productOrderId;
                window.location.href = "/profile/invoice";
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(errorThrown);
            }
        });
    });
</script>




