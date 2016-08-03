<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<tr id="order${order.productOrderId}" data-toggle="collapse" data-target="#orderContent${order.productOrderId}"
    class="accordion-toggle">
    <sec:authorize access="hasAnyRole('SUPERADMIN','ADMIN')">
        <td>
            <button type="button" class="button-green-theme remove cursor-hand no-margin width-50"
                    onclick="removeOrder('${order.productOrderId}')"
                    data-toggle2="tooltip" data-placement="right"
                    title="Remove order">
                <a class="fa fa-close"></a>
            </button>
        </td>
        <td>
            <a class="cursor-hand">
                <div class="row-fluid">
                    <div class="col-md-12">
                        <img src="${order.customerByCustomerId.images['avatar_small']}">
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="col-md-12">
                        <h4>${order.customerByCustomerId.customerName}</h4>
                    </div>
                </div>
            </a>
        </td>
    </sec:authorize>
    <td>${order.productOrderDate}</td>
    <td>
        <sec:authorize access="hasAnyRole('SUPERADMIN','ADMIN')">
            <select class="select" id="statusSelect" style="width: 150px">
                <option value="${order.productOrderStatus}">${order.productOrderStatus}</option>
                <c:forEach items="${orderStatuses}" var="orderStatus" varStatus="loop">
                    <c:if test="${orderStatus!=order.productOrderStatus}">
                        <option id="option${loop.index}" value="${orderStatus}">${orderStatus}</option>
                    </c:if>
                </c:forEach>
            </select>
        </sec:authorize>
        <sec:authorize access="hasRole('USER')">
            <span class="label label-success">${order.productOrderStatus}</span>
        </sec:authorize>
    </td>
    <td>
        <c:choose>
            <c:when test="${order.paid}">
                <sec:authorize access="hasRole('USER')">
                    <span class="label label-success cursor-hand" ">PAID</span>
                </sec:authorize>
                <sec:authorize access="hasAnyRole('SUPERADMIN','ADMIN')">
                    <span class="label label-success">PAID</span>
                </sec:authorize>
            </c:when>
            <c:otherwise>
                <sec:authorize access="hasRole('USER')">
                    <span class="label label-danger cursor-hand">NO YET</span>
                </sec:authorize>
                <sec:authorize access="hasAnyRole('SUPERADMIN','ADMIN')">
                    <span class="label label-danger">NO YET</span>
                </sec:authorize>
            </c:otherwise>
        </c:choose>
    </td>
    <td>${currency} ${order.total}</td>
</tr>
<tr>
    <td colspan="12" class="hiddenRow">
        <div class="accordian-body collapse" id="orderContent${order.productOrderId}">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th></th>
                    <th>Product</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${order.productOrderProductsByProductOrderId}" var="orderProduct">
                    <tr>
                        <td>
                            <a class="cursor-hand">
                                <div class="row-fluid">
                                    <div class="col-md-12">
                                        <img src="${orderProduct.productByProductId.firstImage.images['small']}">
                                    </div>
                                </div>
                            </a>
                        </td>
                        <td>${orderProduct.productByProductId.title}</td>
                        <td>${currency} ${orderProduct.productByProductId.price}</td>
                        <td>${orderProduct.quantity}</td>
                        <td>${currency} ${orderProduct.quantity*orderProduct.productByProductId.price}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </td>
</tr>

<script>

    /**
     * Initialize select
     * and event on select
     */
    $("#statusSelect").select2();
    $("#statusSelect").on("select2:select", function (e) {
        var json = JSON.stringify(e.params, function (key, value) {
            if (value && value.nodeName) return "[DOM node]";
            if (value instanceof $.Event) return "[$.Event]";
            return value;
        });
        var obj = jQuery.parseJSON(json);
        changeOrderStatus(obj.data.text, '${order.productOrderId}');
    });

</script>

