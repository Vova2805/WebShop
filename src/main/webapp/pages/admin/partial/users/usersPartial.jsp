<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section class="content-header">
    <h1>
        WebShop Admin Users
    </h1>
    <ol class="breadcrumb">
        <li><a class="cursor-hand" href="admin?block=dashboard"><i
                class="fa fa-dashboard"></i> <span>Dashboard</span></a></li>
        <li><a class="cursor-hand" href="admin?block=full"><i
                class="fa fa-dashboard"></i> <span>Content treeview</span></a></li>
        <li class="active">Users</li>
    </ol>
</section>
<div class="row-fluid">
    <div class="col-md-12 contacts-container">
        <c:forEach items="${customers}" var="customer">
            <div class="col-md-4 col-sm-4 col-xs-12 profile_details">
                <c:set var="lockedClass" value=""/>
                <c:if test="${!customer.enabled}">
                    <c:set var="lockedClass" value="locked"/>
                </c:if>
                <div class="well profile_view ${lockedClass}" id="profView${customer.customerId}">
                    <div class="col-sm-12">
                        <h4 class="brief">
                            <i>Customer</i>
                            <i class="fa fa-lock fa-pull-right font-35 invisible"
                               id="lockIcon${customer.customerId}"></i>
                        </h4>
                        <div class="left col-xs-6">
                            <h2>${customer.customerName}</h2>
                            <ul class="list-unstyled">
                                <c:if test="${not empty customer.email}">
                                    <li><i class="fa fa-envelope"></i> Email: ${customer.email}</li>
                                </c:if>
                                <c:if test="${not empty customer.phone}">
                                    <li><i class="fa fa-phone"></i> Phone : ${customer.phone}</li>
                                </c:if>
                                <c:if test="${not empty customer.addressByAddressId}">
                                    <li><i class="fa fa-building"></i>
                                        Address: ${customer.addressByAddressId.country},${customer.addressByAddressId.city},${customer.addressByAddressId.street}
                                        street
                                    </li>
                                </c:if>
                            </ul>
                        </div>
                        <div class="right col-xs-6 text-center">
                            <a data-toggle2="tooltip" data-placement="right" title="View profile">
                                <img src="${customer.images['standart_128']}"
                                     class="img-circle img-responsive cursor-hand">
                            </a>
                            <div class="col-xs-12 col-sm-12 pull-right no-padding">
                                <c:set var="buttonTitle" value="Lock"/>
                                <c:if test="${!customer.enabled}">
                                    <c:set var="buttonTitle" value="Unlock"/>
                                </c:if>
                                <button id="lockButton${customer.customerId}"
                                        type="button"
                                        class="btn btn-default button-green-theme btn-not-rounded margin-top-30"
                                        onclick="toggleUserLock('${customer.customerId}','${!customer.enabled}')">
                                        ${buttonTitle}
                                    <c:choose>
                                        <c:when test="${!customer.enabled}">
                                            <i class="fa fa-unlock"></i>
                                        </c:when>
                                        <c:otherwise>
                                            <i class="fa fa-lock"></i>
                                        </c:otherwise>
                                    </c:choose>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>