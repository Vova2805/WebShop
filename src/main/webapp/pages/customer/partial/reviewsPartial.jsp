<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    $(document).ready();
</script>

<section class="content-header">
    <h1>
        Reviewed goods
    </h1>
</section>

<section id="aa-product-category">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <c:choose>
                    <c:when test="${empty activeUser.viewedProductsByCustomerId}">
                        <c:set var="mainHeader" value="Your did not review any product" scope="request"/>
                        <c:set var="body" value="Any product you are interested will be displayed here"
                               scope="request"/>
                        <jsp:include page="emptyListMessagePartial.jsp"/>
                    </c:when>
                    <c:otherwise>
                        <div class="aa-product-catg-content">
                            <div class="aa-product-catg-head">
                                <div class="aa-product-catg-head-left">
                                    <form action="" class="aa-sort-form">
                                        <label for="">Sort by</label>
                                        <select name="">
                                            <option value="1" selected="Default">Default</option>
                                            <option value="2">Name</option>
                                            <option value="3">Price</option>
                                            <option value="4">Date</option>
                                        </select>
                                    </form>
                                    <form action="" class="aa-show-form">
                                        <label for="">Show</label>
                                        <select name="">
                                            <option value="1" selected="12">12</option>
                                            <option value="2">24</option>
                                            <option value="3">36</option>
                                        </select>
                                    </form>
                                </div>
                                <div class="aa-product-catg-head-right">
                                    <a id="grid-catg" href="#"><span class="fa fa-th"></span></a>
                                    <a id="list-catg" href="#"><span class="fa fa-list"></span></a>
                                </div>
                            </div>
                            <div class="aa-product-catg-body">
                                <ul class="aa-product-catg">
                                    <!-- start single product item -->
                                    <li>
                                        <figure>
                                            <a class="aa-product-img" href="#"><img
                                                    src="/resources/img/women/girl-1.png"
                                            ></a>
                                            <a class="aa-add-card-btn" href="#"><span
                                                    class="fa fa-shopping-cart"></span>Add
                                                To Cart</a>
                                            <figcaption>
                                                <h4 class="aa-product-title"><a href="#">This is Title</a></h4>
                                                <span class="aa-product-price">$45.50</span><span
                                                    class="aa-product-price"><del>$65.50</del></span>
                                                <p class="aa-product-descrip">Lorem ipsum dolor sit amet, consectetur
                                                    adipisicing elit. Numquam accusamus facere iusto, autem soluta amet
                                                    sapiente ratione inventore nesciunt a, maxime quasi consectetur,
                                                    rerum
                                                    illum.</p>
                                            </figcaption>
                                        </figure>
                                        <div class="aa-product-hvr-content">
                                            <a href="#" data-toggle="tooltip" data-placement="top"
                                               title="Add to Wishlist"><span class="fa fa-heart-o"></span></a>
                                            <a href="#" data-toggle="tooltip" data-placement="top" title="Compare"><span
                                                    class="fa fa-exchange"></span></a>
                                            <a href="#" data-toggle2="tooltip" data-placement="top" title="Quick View"
                                               data-toggle="modal" data-target="#modalWrapper"><span
                                                    class="fa fa-search"></span></a>
                                        </div>
                                        <!-- product badge -->
                                        <span class="aa-badge aa-sale" href="#">SALE!</span>
                                    </li>
                                    <!-- start single product item -->
                                    <li>
                                        <figure>
                                            <a class="aa-product-img" href="#"><img
                                                    src="/resources/img/women/girl-2.png"
                                            ></a>
                                            <a class="aa-add-card-btn" href="#"><span
                                                    class="fa fa-shopping-cart"></span>Add
                                                To Cart</a>
                                            <figcaption>
                                                <h4 class="aa-product-title"><a href="#">Lorem ipsum doller</a></h4>
                                                <span class="aa-product-price">$45.50</span>
                                                <p class="aa-product-descrip">Lorem ipsum dolor sit amet, consectetur
                                                    adipisicing elit. Numquam accusamus facere iusto, autem soluta amet
                                                    sapiente ratione inventore nesciunt a, maxime quasi consectetur,
                                                    rerum
                                                    illum.</p>
                                            </figcaption>
                                        </figure>
                                        <div class="aa-product-hvr-content">
                                            <a href="#" data-toggle="tooltip" data-placement="top"
                                               title="Add to Wishlist"><span class="fa fa-heart-o"></span></a>
                                            <a href="#" data-toggle="tooltip" data-placement="top" title="Compare"><span
                                                    class="fa fa-exchange"></span></a>
                                            <a href="#" data-toggle2="tooltip" data-placement="top" title="Quick View"
                                               data-toggle="modal" data-target="#modalWrapper"><span
                                                    class="fa fa-search"></span></a>
                                        </div>
                                        <!-- product badge -->
                                        <span class="aa-badge aa-sold-out" href="#">Sold Out!</span>
                                    </li>
                                    <!-- start single product item -->
                                    <li>
                                        <figure>
                                            <a class="aa-product-img" href="#"><img
                                                    src="/resources/img/women/girl-3.png"
                                            ></a>
                                            <a class="aa-add-card-btn" href="#"><span
                                                    class="fa fa-shopping-cart"></span>Add
                                                To Cart</a>
                                            <figcaption>
                                                <h4 class="aa-product-title"><a href="#">Lorem ipsum doller</a></h4>
                                                <span class="aa-product-price">$45.50</span><span
                                                    class="aa-product-price"><del>$65.50</del></span>
                                                <p class="aa-product-descrip">Lorem ipsum dolor sit amet, consectetur
                                                    adipisicing elit. Numquam accusamus facere iusto, autem soluta amet
                                                    sapiente ratione inventore nesciunt a, maxime quasi consectetur,
                                                    rerum
                                                    illum.</p>
                                            </figcaption>
                                        </figure>
                                        <div class="aa-product-hvr-content">
                                            <a href="#" data-toggle="tooltip" data-placement="top"
                                               title="Add to Wishlist"><span class="fa fa-heart-o"></span></a>
                                            <a href="#" data-toggle="tooltip" data-placement="top" title="Compare"><span
                                                    class="fa fa-exchange"></span></a>
                                            <a href="#" data-toggle2="tooltip" data-placement="top" title="Quick View"
                                               data-toggle="modal" data-target="#modalWrapper"><span
                                                    class="fa fa-search"></span></a>
                                        </div>
                                    </li>
                                    <!-- start single product item -->
                                    <li>
                                        <figure>
                                            <a class="aa-product-img" href="#"><img
                                                    src="/resources/img/women/girl-4.png"
                                            ></a>
                                            <a class="aa-add-card-btn" href="#"><span
                                                    class="fa fa-shopping-cart"></span>Add
                                                To Cart</a>
                                            <figcaption>
                                                <h4 class="aa-product-title"><a href="#">Lorem ipsum doller</a></h4>
                                                <span class="aa-product-price">$45.50</span><span
                                                    class="aa-product-price"><del>$65.50</del></span>
                                                <p class="aa-product-descrip">Lorem ipsum dolor sit amet, consectetur
                                                    adipisicing elit. Numquam accusamus facere iusto, autem soluta amet
                                                    sapiente ratione inventore nesciunt a, maxime quasi consectetur,
                                                    rerum
                                                    illum.</p>
                                            </figcaption>
                                        </figure>
                                        <div class="aa-product-hvr-content">
                                            <a href="#" data-toggle="tooltip" data-placement="top"
                                               title="Add to Wishlist"><span class="fa fa-heart-o"></span></a>
                                            <a href="#" data-toggle="tooltip" data-placement="top" title="Compare"><span
                                                    class="fa fa-exchange"></span></a>
                                            <a href="#" data-toggle2="tooltip" data-placement="top" title="Quick View"
                                               data-toggle="modal" data-target="#modalWrapper"><span
                                                    class="fa fa-search"></span></a>
                                        </div>
                                        <!-- product badge -->
                                        <span class="aa-badge aa-hot" href="#">HOT!</span>
                                    </li>
                                    <!-- start single product item -->
                                    <li>
                                        <figure>
                                            <a class="aa-product-img" href="#"><img
                                                    src="/resources/img/women/girl-5.png"
                                            ></a>
                                            <a class="aa-add-card-btn" href="#"><span
                                                    class="fa fa-shopping-cart"></span>Add
                                                To Cart</a>
                                            <figcaption>
                                                <h4 class="aa-product-title"><a href="#">Lorem ipsum doller</a></h4>
                                                <span class="aa-product-price">$45.50</span>
                                                <p class="aa-product-descrip">Lorem ipsum dolor sit amet, consectetur
                                                    adipisicing elit. Numquam accusamus facere iusto, autem soluta amet
                                                    sapiente ratione inventore nesciunt a, maxime quasi consectetur,
                                                    rerum
                                                    illum.</p>
                                            </figcaption>
                                        </figure>
                                        <div class="aa-product-hvr-content">
                                            <a href="#" data-toggle="tooltip" data-placement="top"
                                               title="Add to Wishlist"><span class="fa fa-heart-o"></span></a>
                                            <a href="#" data-toggle="tooltip" data-placement="top" title="Compare"><span
                                                    class="fa fa-exchange"></span></a>
                                            <a href="#" data-toggle2="tooltip" data-placement="top" title="Quick View"
                                               data-toggle="modal" data-target="#modalWrapper"><span
                                                    class="fa fa-search"></span></a>
                                        </div>
                                    </li>
                                    <!-- start single product item -->
                                    <li>
                                        <figure>
                                            <a class="aa-product-img" href="#"><img
                                                    src="/resources/img/women/girl-6.png"
                                            ></a>
                                            <a class="aa-add-card-btn" href="#"><span
                                                    class="fa fa-shopping-cart"></span>Add
                                                To Cart</a>
                                            <figcaption>
                                                <h4 class="aa-product-title"><a href="#">Lorem ipsum doller</a></h4>
                                                <span class="aa-product-price">$45.50</span><span
                                                    class="aa-product-price"><del>$65.50</del></span>
                                                <p class="aa-product-descrip">Lorem ipsum dolor sit amet, consectetur
                                                    adipisicing elit. Numquam accusamus facere iusto, autem soluta amet
                                                    sapiente ratione inventore nesciunt a, maxime quasi consectetur,
                                                    rerum
                                                    illum.</p>
                                            </figcaption>
                                        </figure>
                                        <div class="aa-product-hvr-content">
                                            <a href="#" data-toggle="tooltip" data-placement="top"
                                               title="Add to Wishlist"><span class="fa fa-heart-o"></span></a>
                                            <a href="#" data-toggle="tooltip" data-placement="top" title="Compare"><span
                                                    class="fa fa-exchange"></span></a>
                                            <a href="#" data-toggle2="tooltip" data-placement="top" title="Quick View"
                                               data-toggle="modal" data-target="#modalWrapper"><span
                                                    class="fa fa-search"></span></a>
                                        </div>
                                    </li>
                                    <!-- start single product item -->
                                    <li>
                                        <figure>
                                            <a class="aa-product-img" href="#"><img
                                                    src="/resources/img/women/girl-7.png"
                                            ></a>
                                            <a class="aa-add-card-btn" href="#"><span
                                                    class="fa fa-shopping-cart"></span>Add
                                                To Cart</a>
                                            <figcaption>
                                                <h4 class="aa-product-title"><a href="#">Lorem ipsum doller</a></h4>
                                                <span class="aa-product-price">$45.50</span><span
                                                    class="aa-product-price"><del>$65.50</del></span>
                                                <p class="aa-product-descrip">Lorem ipsum dolor sit amet, consectetur
                                                    adipisicing elit. Numquam accusamus facere iusto, autem soluta amet
                                                    sapiente ratione inventore nesciunt a, maxime quasi consectetur,
                                                    rerum
                                                    illum.</p>
                                            </figcaption>
                                        </figure>
                                        <div class="aa-product-hvr-content">
                                            <a href="#" data-toggle="tooltip" data-placement="top"
                                               title="Add to Wishlist"><span class="fa fa-heart-o"></span></a>
                                            <a href="#" data-toggle="tooltip" data-placement="top" title="Compare"><span
                                                    class="fa fa-exchange"></span></a>
                                            <a href="#" data-toggle2="tooltip" data-placement="top" title="Quick View"
                                               data-toggle="modal" data-target="#modalWrapper"><span
                                                    class="fa fa-search"></span></a>
                                        </div>
                                        <!-- product badge -->
                                        <span class="aa-badge aa-sale" href="#">SALE!</span>
                                    </li>
                                    <!-- start single product item -->
                                    <li>
                                        <figure>
                                            <a class="aa-product-img" href="#"><img
                                                    src="/resources/img/women/girl-1.png"
                                            ></a>
                                            <a class="aa-add-card-btn" href="#"><span
                                                    class="fa fa-shopping-cart"></span>Add
                                                To Cart</a>
                                            <figcaption>
                                                <h4 class="aa-product-title"><a href="#">Lorem ipsum doller</a></h4>
                                                <span class="aa-product-price">$45.50</span>
                                                <p class="aa-product-descrip">Lorem ipsum dolor sit amet, consectetur
                                                    adipisicing elit. Numquam accusamus facere iusto, autem soluta amet
                                                    sapiente ratione inventore nesciunt a, maxime quasi consectetur,
                                                    rerum
                                                    illum.</p>
                                            </figcaption>
                                        </figure>
                                        <div class="aa-product-hvr-content">
                                            <a href="#" data-toggle="tooltip" data-placement="top"
                                               title="Add to Wishlist"><span class="fa fa-heart-o"></span></a>
                                            <a href="#" data-toggle="tooltip" data-placement="top" title="Compare"><span
                                                    class="fa fa-exchange"></span></a>
                                            <a href="#" data-toggle2="tooltip" data-placement="top" title="Quick View"
                                               data-toggle="modal" data-target="#modalWrapper"><span
                                                    class="fa fa-search"></span></a>
                                        </div>
                                        <!-- product badge -->
                                        <span class="aa-badge aa-sold-out" href="#">Sold Out!</span>
                                    </li>
                                    <!-- start single product item -->
                                    <li>
                                        <figure>
                                            <a class="aa-product-img" href="#"><img
                                                    src="/resources/img/women/girl-1.png"
                                            ></a>
                                            <a class="aa-add-card-btn" href="#"><span
                                                    class="fa fa-shopping-cart"></span>Add
                                                To Cart</a>
                                            <figcaption>
                                                <h4 class="aa-product-title"><a href="#">This is Title</a></h4>
                                                <span class="aa-product-price">$45.50</span><span
                                                    class="aa-product-price"><del>$65.50</del></span>
                                                <p class="aa-product-descrip">Lorem ipsum dolor sit amet, consectetur
                                                    adipisicing elit. Numquam accusamus facere iusto, autem soluta amet
                                                    sapiente ratione inventore nesciunt a, maxime quasi consectetur,
                                                    rerum
                                                    illum.</p>
                                            </figcaption>
                                        </figure>
                                        <div class="aa-product-hvr-content">
                                            <a href="#" data-toggle="tooltip" data-placement="top"
                                               title="Add to Wishlist"><span class="fa fa-heart-o"></span></a>
                                            <a href="#" data-toggle="tooltip" data-placement="top" title="Compare"><span
                                                    class="fa fa-exchange"></span></a>
                                            <a href="#" data-toggle2="tooltip" data-placement="top" title="Quick View"
                                               data-toggle="modal" data-target="#modalWrapper"><span
                                                    class="fa fa-search"></span></a>
                                        </div>
                                        <!-- product badge -->
                                        <span class="aa-badge aa-sale" href="#">SALE!</span>
                                    </li>
                                    <!-- start single product item -->
                                    <li>
                                        <figure>
                                            <a class="aa-product-img" href="#"><img
                                                    src="/resources/img/women/girl-2.png"
                                            ></a>
                                            <a class="aa-add-card-btn" href="#"><span
                                                    class="fa fa-shopping-cart"></span>Add
                                                To Cart</a>
                                            <figcaption>
                                                <h4 class="aa-product-title"><a href="#">Lorem ipsum doller</a></h4>
                                                <span class="aa-product-price">$45.50</span>
                                                <p class="aa-product-descrip">Lorem ipsum dolor sit amet, consectetur
                                                    adipisicing elit. Numquam accusamus facere iusto, autem soluta amet
                                                    sapiente ratione inventore nesciunt a, maxime quasi consectetur,
                                                    rerum
                                                    illum.</p>
                                            </figcaption>
                                        </figure>
                                        <div class="aa-product-hvr-content">
                                            <a href="#" data-toggle="tooltip" data-placement="top"
                                               title="Add to Wishlist"><span class="fa fa-heart-o"></span></a>
                                            <a href="#" data-toggle="tooltip" data-placement="top" title="Compare"><span
                                                    class="fa fa-exchange"></span></a>
                                            <a href="#" data-toggle2="tooltip" data-placement="top" title="Quick View"
                                               data-toggle="modal" data-target="#modalWrapper"><span
                                                    class="fa fa-search"></span></a>
                                        </div>
                                        <!-- product badge -->
                                        <span class="aa-badge aa-sold-out" href="#">Sold Out!</span>
                                    </li>
                                    <!-- start single product item -->
                                    <li>
                                        <figure>
                                            <a class="aa-product-img" href="#"><img
                                                    src="/resources/img/women/girl-3.png"
                                            ></a>
                                            <a class="aa-add-card-btn" href="#"><span
                                                    class="fa fa-shopping-cart"></span>Add
                                                To Cart</a>
                                            <figcaption>
                                                <h4 class="aa-product-title"><a href="#">Lorem ipsum doller</a></h4>
                                                <span class="aa-product-price">$45.50</span><span
                                                    class="aa-product-price"><del>$65.50</del></span>
                                                <p class="aa-product-descrip">Lorem ipsum dolor sit amet, consectetur
                                                    adipisicing elit. Numquam accusamus facere iusto, autem soluta amet
                                                    sapiente ratione inventore nesciunt a, maxime quasi consectetur,
                                                    rerum
                                                    illum.</p>
                                            </figcaption>
                                        </figure>
                                        <div class="aa-product-hvr-content">
                                            <a href="#" data-toggle="tooltip" data-placement="top"
                                               title="Add to Wishlist"><span class="fa fa-heart-o"></span></a>
                                            <a href="#" data-toggle="tooltip" data-placement="top" title="Compare"><span
                                                    class="fa fa-exchange"></span></a>
                                            <a href="#" data-toggle2="tooltip" data-placement="top" title="Quick View"
                                               data-toggle="modal" data-target="#modalWrapper"><span
                                                    class="fa fa-search"></span></a>
                                        </div>
                                    </li>
                                    <!-- start single product item -->
                                    <li>
                                        <figure>
                                            <a class="aa-product-img" href="#"><img
                                                    src="/resources/img/women/girl-4.png"
                                            ></a>
                                            <a class="aa-add-card-btn" href="#"><span
                                                    class="fa fa-shopping-cart"></span>Add
                                                To Cart</a>
                                            <figcaption>
                                                <h4 class="aa-product-title"><a href="#">Lorem ipsum doller</a></h4>
                                                <span class="aa-product-price">$45.50</span><span
                                                    class="aa-product-price"><del>$65.50</del></span>
                                                <p class="aa-product-descrip">Lorem ipsum dolor sit amet, consectetur
                                                    adipisicing elit. Numquam accusamus facere iusto, autem soluta amet
                                                    sapiente ratione inventore nesciunt a, maxime quasi consectetur,
                                                    rerum
                                                    illum.</p>
                                            </figcaption>
                                        </figure>
                                        <div class="aa-product-hvr-content">
                                            <a href="#" data-toggle="tooltip" data-placement="top"
                                               title="Add to Wishlist"><span class="fa fa-heart-o"></span></a>
                                            <a href="#" data-toggle="tooltip" data-placement="top" title="Compare"><span
                                                    class="fa fa-exchange"></span></a>
                                            <a href="#" data-toggle2="tooltip" data-placement="top" title="Quick View"
                                               data-toggle="modal" data-target="#modalWrapper"><span
                                                    class="fa fa-search"></span></a>
                                        </div>
                                        <!-- product badge -->
                                        <span class="aa-badge aa-hot" href="#">HOT!</span>
                                    </li>
                                    <!-- start single product item -->
                                    <li>
                                        <figure>
                                            <a class="aa-product-img" href="#"><img
                                                    src="/resources/img/women/girl-5.png"
                                            ></a>
                                            <a class="aa-add-card-btn" href="#"><span
                                                    class="fa fa-shopping-cart"></span>Add
                                                To Cart</a>
                                            <figcaption>
                                                <h4 class="aa-product-title"><a href="#">Lorem ipsum doller</a></h4>
                                                <span class="aa-product-price">$45.50</span>
                                                <p class="aa-product-descrip">Lorem ipsum dolor sit amet, consectetur
                                                    adipisicing elit. Numquam accusamus facere iusto, autem soluta amet
                                                    sapiente ratione inventore nesciunt a, maxime quasi consectetur,
                                                    rerum
                                                    illum.</p>
                                            </figcaption>
                                        </figure>

                                        <div class="aa-product-hvr-content">
                                            <a href="#" data-toggle="tooltip" data-placement="top"
                                               title="Add to Wishlist"><span class="fa fa-heart-o"></span></a>
                                            <a href="#" data-toggle="tooltip" data-placement="top" title="Compare"><span
                                                    class="fa fa-exchange"></span></a>
                                            <a href="#" data-toggle2="tooltip" data-placement="top" title="Quick View"
                                               data-toggle="modal" data-target="#modalWrapper"><span
                                                    class="fa fa-search"></span></a>
                                        </div>
                                    </li>
                                    <!-- start single product item -->
                                    <li>
                                        <figure>
                                            <a class="aa-product-img" href="#"><img
                                                    src="/resources/img/women/girl-6.png"
                                            ></a>
                                            <a class="aa-add-card-btn" href="#"><span
                                                    class="fa fa-shopping-cart"></span>Add
                                                To Cart</a>
                                            <figcaption>
                                                <h4 class="aa-product-title"><a href="#">Lorem ipsum doller</a></h4>
                                                <span class="aa-product-price">$45.50</span><span
                                                    class="aa-product-price"><del>$65.50</del></span>
                                                <p class="aa-product-descrip">Lorem ipsum dolor sit amet, consectetur
                                                    adipisicing elit. Numquam accusamus facere iusto, autem soluta amet
                                                    sapiente ratione inventore nesciunt a, maxime quasi consectetur,
                                                    rerum
                                                    illum.</p>
                                            </figcaption>
                                        </figure>
                                        <div class="aa-product-hvr-content">
                                            <a href="#" data-toggle="tooltip" data-placement="top"
                                               title="Add to Wishlist"><span class="fa fa-heart-o"></span></a>
                                            <a href="#" data-toggle="tooltip" data-placement="top" title="Compare"><span
                                                    class="fa fa-exchange"></span></a>
                                            <a href="#" data-toggle2="tooltip" data-placement="top" title="Quick View"
                                               data-toggle="modal" data-target="#modalWrapper"><span
                                                    class="fa fa-search"></span></a>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                            <div class="aa-product-catg-pagination">
                                <nav>
                                    <ul class="pagination">
                                        <li>
                                            <a href="#" aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                            </a>
                                        </li>
                                        <li><a href="#">1</a></li>
                                        <li><a href="#">2</a></li>
                                        <li><a href="#">3</a></li>
                                        <li><a href="#">4</a></li>
                                        <li><a href="#">5</a></li>
                                        <li>
                                            <a href="#" aria-label="Next">
                                                <span aria-hidden="true">&raquo;</span>
                                            </a>
                                        </li>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>

            </div>
        </div>
    </div>
</section>



