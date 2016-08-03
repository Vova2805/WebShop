<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Web shop | ${currentParentItem.title}</title>
    <jsp:include page="../reference/commonReferences.jsp"/>
    <jsp:include page="../reference/adminReferences.jsp"/>
</head>
<body class="productPage">

<jsp:include page="layout/header.jsp"/>

<section id="aa-product-category">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-10 col-md-10 col-sm-9 col-md-push-2">
                <div class="aa-sidebar">
                    <div class="aa-sidebar-widget">
                        <h3 class="cursor-hand no-border no-margin-bottom no-padding-bottom"
                            onclick="location.href='${parentHref}'">&lt; ${parent}</h3>
                    </div>
                    <div class="aa-sidebar-widget">
                        <h3 class="cursor-hand no-border padding-left-40 no-margin-top"
                            onclick="location.href='${hrefPrefixAside}${currentParentItem.id}${hrefProductSuffix}'">${currentParentItem.title}
                            &gt;</h3>
                    </div>
                </div>
                <div class="aa-product-catg-content  no-padding">
                    <div class="aa-product-catg-body no-margin">
                        <ul class="aa-product-catg">
                            <c:forEach items="${items}" var="item">
                                <li onclick="location.href='${hrefParentPrefix}${item.id}${hrefChildSuffix}'"
                                    class="small-li stretch">
                                    <figure class="height-130">
                                        <a class="aa-product-img cursor-hand">
                                            <img src="${item.images['standart_128']}" class="max-height-128"></a>
                                    </figure>
                                    <figcaption>
                                        <h4 class="aa-product-title"><a
                                                class="cursor-hand">${item.title}</a>
                                        </h4>
                                    </figcaption>
                                    <span class="aa-badge aa-sale cursor-hand">${contentType}</span>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-3 col-md-pull-10">
                <aside class="aa-sidebar">
                    <div class="aa-sidebar-widget">
                        <h3>${title}</h3>
                        <ul class="aa-catg-nav ${title}">
                            <c:forEach items="${asideItems}" var="item">
                                <c:choose>
                                    <c:when test="${item.id==currentParentItem.id}">
                                        <li><a class="active-option"
                                               href="${hrefPrefixAside}${item.id}${hrefSuffixAside}">${item.title}</a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><a href="${hrefPrefixAside}${item.id}${hrefSuffixAside}">${item.title}</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </ul>
                    </div>
                    <script>
                        $(".aa-catg-nav a").on("click", function () {
                            $(".aa-catg-nav a").removeClass("active-option");
                            $(".aa-catg-nav a").removeClass("added-option");
                            $(this).addClass("active-option");
                        });
                    </script>
                </aside>
            </div>
        </div>
    </div>
</section>

<jsp:include page="layout/subscribe.jsp"/>
<jsp:include page="layout/footer.jsp"/>
<script>
    treeviewInit();
</script>

</body>
</html>
