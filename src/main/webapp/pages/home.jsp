<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <c:set var="url">${req.requestURL}</c:set>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/">
    <link rel="shortcut icon" type="image/icon" href="/resources/img/res/logo_small.png"/>
    <title>WebShop | Welcome</title>
</head>
<body>
<script>
    $(document).ready(function (event) {
        changeContent('login', 'login-li');
    })
</script>
<jsp:include page="reference/commonReferences.jsp"/>
<jsp:include page="reference/adminReferences.jsp"/>
<jsp:include page="shop/layout/header.jsp"/>
<jsp:include page="shop/layout/advertisementSlider.jsp"/>

<section class="aa-popular-category">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="row">
                    <div class="aa-popular-category-area">
                        <ul class="nav nav-tabs aa-goods-tab">
                            <c:forEach items="${categoriesProductMap.keySet()}" var="categoryItem" varStatus="loop">
                                <c:choose>
                                    <c:when test="${loop.first}">
                                        <c:set var="liClass" scope="request" value="active"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="liClass" scope="request" value=""/>
                                    </c:otherwise>
                                </c:choose>
                                <li class="${liClass}"><a href="#paneTop${categoryItem.id}"
                                                          data-toggle="tab">${categoryItem.title}</a></li>
                            </c:forEach>
                        </ul>

                        <div class="tab-content">
                            <c:forEach items="${categoriesProductMap.keySet()}" var="categoryItem" varStatus="loop">
                                <c:choose>
                                    <c:when test="${loop.first}">
                                        <c:set var="contentClasses" scope="request" value="tab-pane fade in active "/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="contentClasses" scope="request" value="tab-pane fade"/>
                                    </c:otherwise>
                                </c:choose>
                                <div class="${contentClasses}" id="paneTop${categoryItem.id}">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <a class="pull-right left fa fa-chevron-left btn btn-success btn-not-rounded margin-top--40 margin-right-20"
                                               href="#carousel${categoryItem.id}" data-slide="prev"></a>
                                            <a class="pull-right right fa fa-chevron-right btn btn-success btn-not-rounded margin-top--40"
                                               href="#carousel${categoryItem.id}" data-slide="next"></a>
                                        </div>
                                    </div>
                                    <div id="carousel${categoryItem.id}" class="carousel slide hidden-xs"
                                         data-ride="carousel">
                                        <div class="carousel-inner">
                                            <c:set var="blockCount"
                                                   value="${categoriesProductMap[categoryItem].size()/4}"/>
                                            <c:if test="${blockCount*4<categoriesProductMap[categoryItem].size() && categoriesProductMap[categoryItem].size()>0}">
                                                <c:set var="blockCount" value="${blockCount-1}"/>
                                            </c:if>
                                            <c:forEach var="i" begin="0" end="${blockCount}">
                                                <c:set var="activeClass" value=""/>
                                                <c:if test="${i==0}">
                                                    <c:set var="activeClass" value="active"/>
                                                </c:if>
                                                <div class="item ${activeClass}">
                                                    <div class="row no-margin padding-center">
                                                        <ul class="aa-product-catg aa-popular-slider">

                                                            <c:if test="${0<categoriesProductMap[categoryItem].size()}">
                                                                <c:set var="endIndex" value="${4*i+3}"/>
                                                                <c:if test="${endIndex>=categoriesProductMap[categoryItem].size() }">
                                                                    <c:set var="endIndex"
                                                                           value="${categoriesProductMap[categoryItem].size()-1}"/>
                                                                </c:if>
                                                                <c:forEach var="index" begin="${4*i}" end="${endIndex}">
                                                                    <c:set var="productItem"
                                                                           value="${categoriesProductMap[categoryItem][index]}"/>
                                                                    <c:set var="productItem" value="${productItem}"
                                                                           scope="request"/>
                                                                    <jsp:include page="shop/partial/productView.jsp"/>
                                                                </c:forEach>
                                                            </c:if>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <a class="aa-browse-btn" href="/categories/${categoryItem.id}/subcategories">Browse
                                            all Product <span class="fa fa-long-arrow-right"></span></a>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<c:if test="${showBottom==true}">
    <section class="aa-popular-category">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="row">
                        <div class="aa-popular-category-area">
                            <ul class="nav nav-tabs aa-goods-tab">
                                <c:forEach items="${popularNewestProductMap.keySet()}" var="title"
                                           varStatus="loop">
                                    <c:choose>
                                        <c:when test="${loop.first}">
                                            <c:set var="liClass" scope="request" value="active"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="liClass" scope="request" value=""/>
                                        </c:otherwise>
                                    </c:choose>
                                    <li class="${liClass}"><a href="#paneBottom${title}"
                                                              data-toggle="tab">${title}</a></li>
                                </c:forEach>
                            </ul>

                            <div class="tab-content">
                                <c:forEach items="${popularNewestProductMap.keySet()}" var="title" varStatus="loop">
                                    <c:choose>
                                        <c:when test="${loop.first}">
                                            <c:set var="contentClasses" scope="request"
                                                   value="tab-pane fade in active "/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="contentClasses" scope="request" value="tab-pane fade"/>
                                        </c:otherwise>
                                    </c:choose>
                                    <div class="${contentClasses}" id="paneBottom${title}">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <a class="pull-right left fa fa-chevron-left btn btn-success btn-not-rounded margin-top--40 margin-right-20"
                                                   href="#carousel${title}" data-slide="prev"></a>
                                                <a class="pull-right right fa fa-chevron-right btn btn-success btn-not-rounded margin-top--40"
                                                   href="#carousel${title}" data-slide="next"></a>
                                            </div>
                                        </div>
                                        <div id="carousel${title}" class="carousel slide hidden-xs"
                                             data-ride="carousel">
                                            <div class="carousel-inner">
                                                <c:set var="blockCount"
                                                       value="${popularNewestProductMap[title].size()/4}"/>
                                                <c:if test="${blockCount*4<popularNewestProductMap[title].size() && popularNewestProductMap[title].size()>0}">
                                                    <c:set var="blockCount" value="${blockCount-1}"/>
                                                </c:if>
                                                <c:forEach var="i" begin="0" end="${blockCount}">
                                                    <c:set var="activeClass" value=""/>
                                                    <c:if test="${i==0}">
                                                        <c:set var="activeClass" value="active"/>
                                                    </c:if>
                                                    <div class="item ${activeClass}">
                                                        <div class="row no-margin padding-center">
                                                            <ul class="aa-product-catg aa-popular-slider no-border">
                                                                <c:if test="${0<popularNewestProductMap[title].size()}">
                                                                    <c:set var="endIndex" value="${4*i+3}"/>
                                                                    <c:if test="${endIndex>=popularNewestProductMap[title].size() }">
                                                                        <c:set var="endIndex"
                                                                               value="${popularNewestProductMap[title].size()-1}"/>
                                                                    </c:if>
                                                                    <c:forEach var="index" begin="${4*i}"
                                                                               end="${endIndex}">
                                                                        <c:set var="productItem"
                                                                               value="${popularNewestProductMap[title][index]}"/>
                                                                        <c:set var="productItem" value="${productItem}"
                                                                               scope="request"/>
                                                                        <jsp:include
                                                                                page="shop/partial/productView.jsp"/>
                                                                    </c:forEach>
                                                                </c:if>

                                                            </ul>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</c:if>

<jsp:include page="shop/layout/subscribe.jsp"/>
<jsp:include page="shop/layout/footer.jsp"/>
<script src="/resources/js/sequence.js"></script>
<script src="/resources/js/sequence-theme.modern-slide-in.js"></script>
</body>
</html>