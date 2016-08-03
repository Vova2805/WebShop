<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container margin-bottom-100">
    <div class="wrapper">
        <section class="invoice">
            <div class="row">
                <div class="col-xs-12">
                    <h2 class="page-header">
                        <img src="/resources/img/res/logo_small.png"> WebShop.
                        <small class="pull-right">Date: ${productOrder.productOrderDate}</small>
                    </h2>
                </div>
            </div>

            <div class="row invoice-info">
                <div class="col-sm-4 invoice-col">
                    From
                    <address>
                        <strong>WebShop, Inc.</strong><br>
                        40 Lazarenka Str<br>
                        Lviv Ukraine<br>
                        Phone: (123) 123-1231<br>
                        Email: support@webshop.com
                    </address>
                </div>

                <div class="col-sm-4 invoice-col">
                    To
                    <address>
                        <strong>${productOrder.customerByCustomerId.customerName}</strong><br>
                        <c:if test="${activeUser.addressByAddressId !=null}">
                            <c:if test="${not empty address.country && address.country!=''}">
                                ${address.country},
                            </c:if>
                            <c:if test="${not empty address.region && address.region!=''}">
                                ${address.region} region,
                            </c:if>
                            <c:if test="${not empty address.city && address.city!=''}">
                                ${address.city},
                            </c:if><br>
                            <c:if test="${not empty address.street && address.street!=''}">
                                ${address.street} street
                            </c:if>
                            <c:if test="${not empty address.buildingNumber && address.buildingNumber!=''}">
                                #${address.buildingNumber}
                            </c:if>
                        </c:if><br>
                        Phone: ${productOrder.customerByCustomerId.phone}<br>
                        Email: ${productOrder.customerByCustomerId.email}
                    </address>
                </div>

                <div class="col-sm-4 invoice-col">
                    <b>Invoice #${productOrder.productOrderId}</b><br>
                    <br>
                    <b>Order ID:</b> ${productOrder.productOrderId}<br>
                </div>

            </div>

            <div class="row">
                <div class="col-xs-12 table-responsive">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Qty</th>
                            <th>Product</th>
                            <th>Description</th>
                            <th>Subtotal</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${productOrder.productOrderProductsByProductOrderId}" var="orderItem">
                            <c:set var="orderedProduct" value="${orderItem.productByProductId}" scope="request"/>
                            <tr>
                                <td>${orderItem.quantity}</td>
                                <td>${orderedProduct.title}</td>
                                <td>${orderedProduct.generalDescr}</td>
                                <td>${currency} ${orderedProduct.price*orderItem.quantity}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-6">
                    <p class="lead">Payment Methods:</p>
                    <img src="/resources/img/res/visa.png" alt="Visa">
                    <img src="/resources/img/res/mastercard.png" alt="Mastercard">
                    <img src="/resources/img/res/paypal.png" alt="Paypal">
                </div>
                <div class="col-xs-6">
                    <p class="lead">Amount</p>

                    <div class="table-responsive">
                        <table class="table">
                            <tr>
                                <th>Total:</th>
                                <td>${currency} ${productOrder.total}</td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </section>
    </div>
</div>
