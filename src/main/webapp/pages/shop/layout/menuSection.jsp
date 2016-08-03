<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<section id="menu">
    <div class="container">
        <div class="menu-area">
            <div class="navbar navbar-default" role="navigation">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>
                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="/">Home</a></li>
                        <c:if test="${categories.size() > 0}">
                            <c:forEach items="${categories}" var="categoryItem">
                                <li>
                                    <a href="categories/${categoryItem.id}/subcategories">${categoryItem.title}
                                        <c:if test="${categoryItem.subcategoriesByCategoryId.size() > 0}">
                                            <span class="caret"></span>
                                        </c:if>
                                    </a>
                                    <c:if test="${categoryItem.subcategoriesByCategoryId.size() > 0}">
                                        <ul class="dropdown-menu">
                                            <c:forEach items="${categoryItem.subcategoriesByCategoryId}"
                                                       var="subcategoryItem">
                                                <li>
                                                    <a href="subcategories/${subcategoryItem.id}/groups">${subcategoryItem.title}
                                                        <c:if test="${subcategoryItem.subcategoryGroupsBySubcategoryId.size() > 0}">
                                                            <span class="caret"></span>
                                                        </c:if>
                                                    </a>
                                                    <c:if test="${subcategoryItem.subcategoryGroupsBySubcategoryId.size() > 0}">
                                                        <ul class="dropdown-menu">
                                                            <c:forEach
                                                                    items="${subcategoryItem.subcategoryGroupsBySubcategoryId}"
                                                                    var="groupItem">
                                                                <li>
                                                                    <a href="groups/${groupItem.id}/goods">${groupItem.title}</a>
                                                                </li>
                                                            </c:forEach>
                                                        </ul>
                                                    </c:if>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </c:if>
                                </li>
                            </c:forEach>
                        </c:if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</section>

