<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    $(document).ready();
</script>
<section class="content-header">
    <h1>
        WebShop Admin Dashboard
    </h1>
    <ol class="breadcrumb">
        <li onclick="changeAdminContent('dashboard')" class="active"><a class="cursor-hand"><i
                class="fa fa-dashboard"></i> <span>Dashboard</span></a></li>
    </ol>
</section>


<section class="content">
    <div class="row">
        <div class="col-lg-3 col-xs-6">
            <div class="small-box bg-aqua">
                <div class="inner">
                    <h3>${usersCount}</h3>
                    <p>Users</p>
                </div>
                <div class="icon">
                    <i class="ion ion-person-add"></i>
                </div>
                <a href="/admin?block=users" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
            </div>
        </div>
        <div class="col-lg-3 col-xs-6">
            <div class="small-box bg-yellow">
                <div class="inner">
                    <h3>${ordersCount}</h3>
                    <p>Orders</p>
                </div>
                <div class="icon">
                    <i class="ion ion-ios-cart"></i>
                </div>
                <a href="/admin?block=orders" class="small-box-footer">More info <i
                        class="fa fa-arrow-circle-right"></i></a>
            </div>
        </div>
        <div class="col-lg-3 col-xs-6">
            <div class="small-box bg-red">
                <div class="inner">
                    <h3>${productsCount}</h3>
                    <p>Products</p>
                </div>
                <div class="icon">
                    <i class="ion ion-bag"></i>
                </div>
                <a href="/admin?block=full" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
            </div>
        </div>
        <div class="col-lg-3 col-xs-6">
            <div class="small-box bg-green">
                <div class="inner">
                    <h3>${revenueCount}</h3>
                    <p>Total revenue</p>
                </div>
                <div class="icon">
                    <i class="fa fa-money "></i>
                </div>
                <a href="/admin?block=orders" class="small-box-footer">More info <i
                        class="fa fa-arrow-circle-right"></i></a>
            </div>
        </div>
    </div>
    <div class="row">
        <c:if test="${not empty orders}">
            <div class="col-md-8">
                <div class="box box-info">
                    <div class="box-header with-border">
                        <h3 class="box-title">Latest Orders</h3>

                        <div class="box-tools pull-right">
                            <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                                    class="fa fa-minus"></i>
                            </button>
                            <button type="button" class="btn btn-box-tool" data-widget="remove"><i
                                    class="fa fa-times"></i>
                            </button>
                        </div>
                    </div>
                    <div class="box-body box-body-scrollable">
                        <div class="table-responsive">
                            <table class="table no-margin">
                                <thead>
                                <tr>
                                    <th>Order ID</th>
                                    <th>Order date</th>
                                    <th>Status</th>
                                    <th>Total</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${orders}" var="order">
                                    <tr>
                                        <td><a href="/admin?block=orders">${order.productOrderId}</a></td>
                                        <td>${order.productOrderDate}</td>
                                        <td><span class="label label-success">${order.productOrderStatus}</span></td>
                                        <td>
                                                ${order.total}
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="box-footer clearfix">
                        <a href="/admin?block=orders" class="cursor-hand btn btn-sm btn-default btn-flat pull-right">View
                            All Orders</a>
                    </div>
                </div>
            </div>
        </c:if>

        <c:if test="${not empty goods}">
        <div class="col-md-4">
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">Recently Added Products</h3>

                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                                class="fa fa-minus"></i>
                        </button>
                        <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i>
                        </button>
                    </div>
                </div>
                <div class="box-body box-body-scrollable">
                    <ul class="goods-list product-list-in-box">
                        <c:forEach items="${goods}" var="good">
                            <li class="item">
                                <div class="product-img">
                                    <img src="${good.firstImage.images['small']}">
                                </div>
                                <div class="product-info">
                                    <a href="javascript:void(0)" class="product-title">${good.title}
                                        <span class="label label-warning pull-right">${currency} ${good.price}</span></a>
                        <span class="product-description">
                                ${good.generalDescr}
                        </span>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="box-footer text-center">
                    <a href="/admin?block=full" class="uppercase">View All Products</a>
                </div>
            </div>
        </div>
    </div>
    </c:if>
</section>
