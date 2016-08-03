<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=utf-8" %>
<div class="row">
    <div class="col-md-6 col-sm-6 col-xs-12">
        <div class="aa-product-view-slider">
            <div id="product-view-slider" class="simpleLens-gallery-container">
                <div class="simpleLens-container">
                    <div class="simpleLens-big-image-container"><a
                            data-lens-image="${product.firstImage.images['large']}"
                            class="simpleLens-lens-image"><img id="largeImage${product.productId}"
                                                               src="${product.firstImage.images['medium']}"
                                                               class="simpleLens-big-image"></a></div>
                </div>
                <div class="simpleLens-thumbnails-container">
                    <c:forEach items="${product.imagesByProductId}" var="image">
                        <a class="simpleLens-thumbnail-wrapper"
                           onclick="$('#largeImage${product.productId}').attr('src','${image.images['medium']}');">
                            <img src="${image.images['small']}">
                        </a>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>

    <div class="col-md-6 col-sm-6 col-xs-12">
        <div class="aa-product-view-content">
            <h3>${product.title}</h3>
            <div class="row-fluid aa-price-block">
                <p>
                    Price:<span class="aa-product-view-price">   ${currency} ${product.price}</span></p>
            </div>
            <div class="row-fluid aa-price-block">
                <p>
                    Availability: <span>${product.availability}</span></p>
            </div>
            <p>Description: <span>${product.generalDescr}</span></p>
            <p>Properties:</p>
            <div class="aa-prod-quantity">
                <c:forEach items="${product.propertyProductsByProductId}" var="property">
                    <div class="row-fluid">
                        <p class="aa-prod-category">
                                ${property.propertyClassByPropertyClassId.propertyClassTitle}:
                            <a>${property.propertyValue}</a>
                        </p>
                    </div>
                </c:forEach>
            </div>


            <div class="aa-prod-quantity">
                <p class="aa-prod-category">
                    Category: <a
                        href="/categories/${category.id}/subcategories">${category.title}</a>
                </p>
                <p class="aa-prod-category">
                    Subcategory: <a
                        href="/subcategories/${subcategory.id}/groups">${subcategory.title}</a>
                </p>
                <p class="aa-prod-category">
                    Group: <a href="/groups/${group.id}/goods">${group.title}</a>
                </p>
            </div>
            <div class="row-fluid aa-prod-view-bottom">
                <c:choose>
                    <c:when test="${empty pageContext.request.userPrincipal}">
                        <a class="aa-add-to-cart-btn" data-toggle="modal"
                           data-target="#loginModal" data-toggle2="modal"
                           data-target="#modalWrapper">Add To Cart</a>
                        <a class="aa-add-to-cart-btn" data-toggle="modal"
                           data-target="#loginModal" data-toggle2="modal"
                           data-target="#modalWrapper">Wishlist</a>
                        <a class="aa-add-to-cart-btn" data-toggle="modal"
                           data-target="#loginModal" data-toggle2="modal"
                           data-target="#modalWrapper">Compare</a>
                    </c:when>
                    <c:otherwise>
                        <sec:authorize access="hasRole('USER')">
                            <a class="aa-add-to-cart-btn" data-toggle="modal"
                               data-target="#modalWrapper"
                               onclick="changeViewModalContent('goods/addToCart','${product.productId}')">Add To
                                Cart</a>
                            <a class="aa-add-to-cart-btn" href="#">Wishlist</a>
                            <a class="aa-add-to-cart-btn" href="#">Compare</a>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ADMIN')">
                            <a class="aa-add-to-cart-btn" href="/admin?block=full&type=goods&id=${product.productId}">Edit
                                product</a>
                        </sec:authorize>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>


