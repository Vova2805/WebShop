<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=utf-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Web shop | ${product.title}</title>
    <jsp:include page="../reference/commonReferences.jsp"/>
    <jsp:include page="../reference/adminReferences.jsp"/>
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/resources/css/jquery.simpleLens.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/slick.css">
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <link href="/resources/css/style.css" rel="stylesheet">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="/resources/js/bootstrap.js"></script>
    <script src="/resources/js/sequence-theme.modern-slide-in.js"></script>
    <script type="text/javascript" src="/resources/js/jquery.simpleGallery.js"></script>
    <script type="text/javascript" src="/resources/js/jquery.simpleLens.js"></script>
    <script src="/resources/js/custom.js"></script>

    <script type="text/javascript" src="/resources/js/jquery.smartmenus.js"></script>
    <script type="text/javascript" src="/resources/js/jquery.smartmenus.bootstrap.js"></script>
</head>

<body class="productPage">
<jsp:include page="../modal/modalWrapper.jsp"/>
<jsp:include page="layout/header.jsp"/>
<section id="aa-product-details">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="aa-product-details-area">
                    <div class="aa-product-details-content">
                        <div class="row">
                            <div class="col-md-5 col-sm-5 col-xs-12">
                                <div class="aa-product-view-slider">
                                    <div id="product-view-slider" class="simpleLens-gallery-container">
                                        <div class="simpleLens-container">
                                            <div class="simpleLens-big-image-container"><a
                                                    data-lens-image="${product.firstImage.images['large']}"
                                                    class="simpleLens-lens-image"><img
                                                    src="${product.firstImage.images['medium']}"
                                                    class="simpleLens-big-image"></a></div>
                                        </div>
                                        <div class="simpleLens-thumbnails-container">
                                            <c:forEach items="${product.imagesByProductId}" var="image">
                                                <a data-big-image="${image.images['medium']}"
                                                   data-lens-image="${image.images['large']}"
                                                   class="simpleLens-thumbnail-wrapper" href="#">
                                                    <img src="${image.images['small']}">
                                                </a>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-7 col-sm-7 col-xs-12">
                                <div class="aa-product-view-content">
                                    <h3>${product.title}</h3>
                                    <div class="aa-price-block">
                                        <p class="aa-product-avilability">
                                            Price:<span
                                                class="aa-product-view-price">   ${currency} ${product.price}</span></p>
                                        <p class="aa-product-avilability">
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
                                    <div class="aa-prod-view-bottom">
                                        <c:choose>
                                            <c:when test="${empty pageContext.request.userPrincipal}">

                                                <a class="aa-add-to-cart-btn" data-toggle="modal"
                                                   data-target="#loginModal">Add To Cart</a>
                                                <a class="aa-add-to-cart-btn" data-toggle="modal"
                                                   data-target="#loginModal">Wishlist</a>
                                                <a class="aa-add-to-cart-btn" data-toggle="modal"
                                                   data-target="#loginModal">Compare</a>
                                            </c:when>
                                            <c:otherwise>
                                                <sec:authorize access="hasRole('USER')">
                                                    <a class="aa-add-to-cart-btn" data-toggle="modal"
                                                       data-target="#modalWrapper"
                                                       onclick="changeViewModalContent('goods/addToCart',${product.productId})">Add
                                                        To Cart</a>
                                                    <a class="aa-add-to-cart-btn" href="#">Wishlist</a>
                                                    <a class="aa-add-to-cart-btn" href="#">Compare</a>
                                                </sec:authorize>
                                                <sec:authorize access="hasRole('ADMIN')">
                                                    <a class="aa-add-to-cart-btn"
                                                       href="/admin?block=full&type=goods&id=${product.productId}">Edit
                                                        product</a>
                                                </sec:authorize>
                                            </c:otherwise>
                                        </c:choose>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="aa-product-details-bottom">
                        <ul class="nav nav-tabs" id="myTab2">
                            <c:if test="${product.descriptionPartsByProductId.size()>0}">
                                <li><a href="#description" data-toggle="tab">Description</a></li>
                            </c:if>
                            <li><a href="#review" data-toggle="tab">Reviews</a></li>
                        </ul>

                        <div class="tab-content">
                            <c:if test="${product.descriptionPartsByProductId.size()>0}">
                                <div class="tab-pane fade in active" id="description">
                                    <c:forEach items="${product.descriptionPartsByProductId}" var="description">
                                        <div class="row">
                                            <h3 class="text-bold">${description.descriptionPartHeader}</h3>
                                        </div>
                                        <div class="row">
                                            <p>${description.descriptionPartBody}</p>
                                        </div>
                                        <c:choose>
                                            <c:when test="${description.hasImg==true}">
                                                <c:set var="hiddenClass" value=""/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:set var="hiddenClass" value="hidden-block"/>
                                            </c:otherwise>
                                        </c:choose>
                                        <div class="row ${hiddenClass}">
                                            <img src="${description.imageSrc}" class="description-img">
                                        </div>
                                    </c:forEach>
                                </div>
                            </c:if>

                            <div class="tab-pane fade " id="review">
                                <div class="aa-product-review-area">
                                    <h4>${product.commentsByProductId.size()} Reviews for ${product.title}</h4>
                                    <ul class="aa-review-nav">
                                        <c:forEach items="${product.commentsByProductId}" var="comment">
                                            <li>
                                                <div class="media">
                                                    <div class="media-left">
                                                        <a class="cursor-hand">
                                                            <img class="media-object"
                                                                 src="${comment.customerByCustomerId.images['avatar_small']}">
                                                        </a>
                                                    </div>
                                                    <div class="media-body">
                                                        <h4 class="media-heading">
                                                            <strong>${comment.customerByCustomerId.customerName}</strong>
                                                            - <span>${comment.commentDate}</span>
                                                        </h4>
                                                        <p>${comment.commentBody}</p>
                                                    </div>
                                                </div>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                    <h4>Add a review</h4>

                                    <form action="" class="aa-review-form">
                                        <div class="form-group">
                                            <label for="message">Your Review</label>
                                            <textarea name="commentBody" class="form-control" rows="3"
                                                      id="message"></textarea>
                                        </div>
                                        <button type="submit" class="btn btn-default aa-review-submit">Submit</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="layout/subscribe.jsp"/>
<jsp:include page="layout/footer.jsp"/>

</body>
</html>
