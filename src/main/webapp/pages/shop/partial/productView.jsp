<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<li class="no-border">
    <figure>
        <a href="/goods/${productItem.productId}"
           class="aa-product-img cursor-hand">
            <img style="background:url(${productItem.firstImage.images['medium']}) no-repeat center center;border: 0px"
                 class="product-image no-border">
            </img>
        </a>
        <c:choose>
            <c:when test="${empty pageContext.request.userPrincipal}">
                <a class="aa-add-card-btn cursor-hand" data-toggle="modal"
                   data-target="#loginModal"><span
                        class="fa fa-shopping-cart"></span>Add To Cart</a>
            </c:when>
            <c:otherwise>
                <sec:authorize access="hasRole('USER')">
                    <a class="aa-add-card-btn cursor-hand"
                       data-toggle="modal"
                       data-target="#modalWrapper"
                       onclick="changeViewModalContent('goods/addToCart',${productItem.productId})">
                    <span class="fa fa-shopping-cart">
                    </span>Add To Cart</a>
                </sec:authorize>
                <sec:authorize access="hasRole('ADMIN')">
                    <a class="aa-add-card-btn cursor-hand"
                       href="/admin?block=full&type=goods&id=${productItem.productId}">
                    <span class="fa fa-pencil-square-o">
                    </span>Edit product</a>
                </sec:authorize>
            </c:otherwise>
        </c:choose>

        <figcaption>
            <h4 class="aa-product-title"><a class="cursor-hand">${productItem.title}</a>
            </h4>
            <div class="row horizontal-padding">
                <div class="price col-md-6 no-padding">
                    <span class="aa-product-price">${currency} ${productItem.price}</span>
                    <c:if test="${productItem.showDiscount==true}">
                        <span class="aa-product-price"><del>${currency} ${productItem.discount}</del></span>
                    </c:if>
                </div>
                <p class="aa-product-descrip">${productItem.generalDescr}</p>
                <div class="rating col-md-6 no-padding ">
                    <sec:authorize access="hasRole('USER')">
                        <c:forEach var="i" begin="0"
                                   end="4">
                            <c:set var="iClasses"
                                   value="fa-star-o"/>
                            <c:choose>
                                <c:when test="${productItem.rating >= 5-i}">
                                    <c:set var="iClasses"
                                           value="fa-star"/>
                                </c:when>
                                <c:otherwise>
                                    <c:choose>
                                        <c:when test="${productItem.rating > 4-i}">
                                            <c:set var="iClasses"
                                                   value="fa-star-half-o"/>
                                        </c:when>
                                    </c:choose>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${empty pageContext.request.userPrincipal}">
                                <span id="${productItem.productId}${i}" data-toggle="modal"
                                      data-target="#loginModal"><i class="fa gold-color ${iClasses}"></i></span>
                                </c:when>
                                <c:otherwise>
                                    <span id="${productItem.productId}${i}"><i
                                            class="fa gold-color ${iClasses}"></i></span>
                                </c:otherwise>
                            </c:choose>

                        </c:forEach>
                    </sec:authorize>
                </div>
            </div>
        </figcaption>
    </figure>
    <div class="aa-product-hvr-content">
        <c:choose>
            <c:when test="${empty pageContext.request.userPrincipal}">
                <a href="#" data-toggle2="tooltip"
                   data-placement="top"
                   title="Add to Wishlist"
                   data-toggle="modal"
                   data-target="#loginModal"><span
                        class="fa fa-heart-o"></span></a>
                <a href="#" data-toggle2="tooltip"
                   data-placement="top"
                   title="Compare"
                   data-toggle="modal"
                   data-target="#loginModal"><span
                        class="fa fa-exchange"></span></a>
            </c:when>
            <c:otherwise>
                <sec:authorize access="hasRole('USER')">
                    <a href="" data-toggle="tooltip"
                       data-placement="top"
                       title="Add to Wishlist"><span
                            class="fa fa-heart-o"></span></a>
                    <a href="" data-toggle="tooltip"
                       data-placement="top"
                       title="Compare"><span
                            class="fa fa-exchange"></span></a>
                </sec:authorize>
            </c:otherwise>
        </c:choose>
        <a href="#" data-toggle2="tooltip"
           data-placement="top"
           title="Quick View" data-toggle="modal"
           data-target="#modalWrapper"
           onclick="changeViewModalContent('goods/quickView',${productItem.productId})">
            <span class="fa fa-search"></span>
        </a>
    </div>
    <span class="aa-badge aa-sale cursor-hand">SALE!</span>
</li>