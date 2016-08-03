<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Web shop | Products</title>
    <jsp:include page="../reference/commonReferences.jsp"/>
    <jsp:include page="../reference/adminReferences.jsp"/>
    <script>
        $(document).ready();
        $('#topFilterForm').submit(function (e) {
            e.preventDefault();
            submitForm('${page}');
        });
        $('#propertiesFilterForm').submit(function (e) {
            e.preventDefault();
            submitForm('${page}');
        });
        function submitForm(page) {
            var json = {};
            var input = $('#priceTo');
            json[input.attr("name")] = input.val();
            var input = $('#priceFrom');
            json[input.attr("name")] = input.val();

            var input = $('#limit');
            json[input.attr("name")] = input.val();
            var input = $('#sortDirection');
            json[input.attr("name")] = input.val();
            var input = $('#sortBy');
            json[input.attr("name")] = input.val();
            var properties = [];
            $("input[type=checkbox].propertyId:checked").each(function (i) {
                properties.push($(this).val());
            });
            var domain = location.protocol + '//' + location.host + location.pathname;
            if (page == null || page < 0)page = 0;
            if (page >${pageCount})page = ${pageCount};
            window.location.href = domain + "?" + jQuery.param(json) + "&page=" + page + "&properties=" + properties.join();
        }
    </script>
</head>
<body class="productPage">

<jsp:include page="layout/header.jsp"/>

<section id="aa-product-category">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-9 col-md-9 col-sm-8 col-md-push-3">
                <div class="aa-product-catg-content">
                    <div class="aa-product-catg-head">
                        <div class="aa-product-catg-head-left">
                            <form:form id="topFilterForm" class="aa-sort-form">
                                <div class="aa-sort-form">
                                    <label for="sortBy">Sort by</label>
                                    <select name="sortBy" id="sortBy" onchange="submitForm('${page}')">
                                        <c:forEach items="${sortByItems}" var="sortByItem">
                                            <c:choose>
                                                <c:when test="${sortByItem.equals(sortBy)}">
                                                    <option value="${sortByItem.toLowerCase()}"
                                                            selected>${sortByItem}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${sortByItem.toLowerCase()}">${sortByItem}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="aa-sort-form margin-left-25">
                                    <label for="sortDirection">Sort direction</label>
                                    <select name="sortDirection" id="sortDirection" onchange="submitForm('${page}')">
                                        <c:forEach items="${sortDirectionItems}" var="sortDirectionItem">
                                            <c:choose>
                                                <c:when test="${sortDirectionItem.equals(sortDirection)}">
                                                    <option value="${sortDirectionItem.toLowerCase()}"
                                                            selected>${sortDirectionItem}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${sortDirectionItem.toLowerCase()}">${sortDirectionItem}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="aa-show-form">
                                    <label for="limit">Show</label>
                                    <select name="limit" id="limit" onchange="submitForm('${page}')">
                                        <c:forEach items="${limitItems}" var="limitItem">
                                            <c:choose>
                                                <c:when test="${limitItem.equals(limit.toString())}">
                                                    <option value="${limitItem.toLowerCase()}"
                                                            selected>${limitItem}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${limitItem.toLowerCase()}">${limitItem}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>
                            </form:form>
                        </div>
                        <div class="aa-product-catg-head-right">
                            <a id="grid-catg" class="cursor-hand"><span class="fa fa-th"></span></a>
                            <a id="list-catg" class="cursor-hand"><span class="fa fa-list"></span></a>
                        </div>
                    </div>
                    <div class="aa-product-catg-body">
                        <ul class="aa-product-catg">
                            <c:forEach items="${goods}" var="productItem">
                                <c:set var="productItem" value="${productItem}" scope="request"/>
                                <jsp:include page="partial/productView.jsp"/>
                            </c:forEach>
                        </ul>
                    </div>
                    <c:if test="${pageCount>0 && page>0}">
                        <div class="aa-product-catg-pagination">
                            <nav>
                                <ul class="pagination">
                                    <li>
                                        <a onclick="submitForm('${i-1}')" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                    <c:forEach var="i" begin="1" end="${pageCount}">
                                        <c:set var="activeClass" value=""/>
                                        <c:if test="${page == i}">
                                            <c:set var="activeClass" value="active"/>
                                        </c:if>
                                        <li class="${activeClass}" onclick="submitForm('${i}')"><a>${i}</a></li>
                                    </c:forEach>
                                    <li>
                                        <a onclick="submitForm('${i+1}')" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                    </c:if>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-4 col-md-pull-9">
                <aside class="aa-sidebar">
                    <div class="aa-sidebar-widget">
                        <h3>Hierarchy</h3>
                        <ul class="aa-catg-nav">
                            <c:if test="${not empty category}">
                                <li class="padding-left-10"><a href="/categories/${category.id}/subcategories">
                                    &lt; ${category.title}</a></li>
                            </c:if>

                            <c:if test="${not empty subcategory}">
                                <li class="padding-left-40"><a href="/subcategories/${subcategory.id}/groups">
                                    &lt; ${subcategory.title}</a></li>
                            </c:if>

                            <c:if test="${not empty group}">
                                <li class="padding-left-70"><a href="/groups/${group.id}/goods">${group.title}</a></li>
                            </c:if>
                        </ul>
                    </div>
                    <form:form id="propertiesFilterForm">
                        <div class="aa-sidebar-widget">
                            <h3>Shop By Price</h3>
                            <c:set var="priceTo" value="${priceTo}" scope="request"/>
                            <jsp:include page="partial/shopByPricePartial.jsp"/>
                        </div>
                        <c:if test="${propertyClassMap.size()>0}">
                            <div class="aa-sidebar-widget">
                                <h3>Shop by properties</h3>
                                <c:forEach items="${propertyClassMap}" var="entry">
                                    <c:set var="entry" value="${entry}" scope="request"/>
                                    <jsp:include page="partial/relatedPropertyView.jsp"/>
                                </c:forEach>
                            </div>
                        </c:if>
                    </form:form>
                </aside>
            </div>

        </div>
    </div>
</section>

<jsp:include page="layout/subscribe.jsp"/>
<jsp:include page="layout/footer.jsp"/>


</body>
</html>
<script src="/resources/js/custom.js"></script>
