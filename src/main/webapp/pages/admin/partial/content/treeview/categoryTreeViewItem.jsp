<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<li class="treeview active" id="loopCategoryLi${loopCategory.id}">
    <a class="cursor-hand">
        <i class="fa fa-tag"></i>
        <span class="span-overflow" onclick="changeAdminContentInnerBlock('categories',${loopCategory.id})"
              id="categoriesSpan${loopCategory.id}">${loopCategory.title}</span>

                                    <span class="pull-right-container success margin-top-5 fa-container edit"
                                          data-toggle2="tooltip" data-placement="right"
                                          onclick="changeAdminContentInnerBlock('categories',${loopCategory.id})"
                                          title="Edit loopCategory">
                                          <i class="fa fa-pencil-square-o pull-right" aria-hidden="true"></i>
                                          <i class="fa fa-pencil-square pull-right i-success" aria-hidden="true"></i>
                                     </span>
                                     <span class="pull-right-container success margin-top-5 fa-container plus"
                                           data-toggle2="tooltip" data-placement="right"
                                           onclick="addTreeViewItem('${loopCategory.id}','admin/full/subcategories','loopCategoryRootUl')"
                                           title="Add subloopCategory">
                                          <i class="fa fa-plus pull-right" aria-hidden="true"></i>
                                          <i class="fa fa-plus-circle pull-right i-success" aria-hidden="true"></i>
                                     </span>

                                     <span class="pull-right-container danger margin-top-5 fa-container remove"
                                           data-toggle2="tooltip" data-placement="right"
                                           onclick="removeCategory('${loopCategory.id}')"
                                           title="Remove loopCategory">
                                        <i class="fa fa-remove pull-right" aria-hidden="true"></i>
                                        <i class="fa fa-times-circle pull-right i-danger" aria-hidden="true"></i>
                                     </span>
        <c:set var="expandeHidden" value="hidden-block"/>
        <c:if test="${loopCategory.subcategoriesByCategoryId.size()>0}">
            <c:set var="expandeHidden" value=""/>
        </c:if>
                                     <span class="pull-right-container margin-top-5 fa-container collapsable ${expandeHidden}"
                                           onclick="var $this = this;var liParrent = getParentByTagName($this, 'li');$(liParrent).toggleClass('active');"
                                           id="loopCategoryRootUlExp${loopCategory.id}">
                                      <i class="fa fa-angle-left pull-right" aria-hidden="true"></i>
                                      <i class="fa fa-chevron-circle-left pull-right margin-right-5"
                                         aria-hidden="true"></i>
                                     </span>

    </a>
    <ul class="treeview-menu menu-open" id="loopCategoryRootUl${loopCategory.id}">
        <c:forEach items="${loopCategory.subcategoriesByCategoryId}" var="subcategory">
            <c:set var="loopSubcategory" value="${subcategory}" scope="request"/>
            <jsp:include page="subcategoryTreeViewItem.jsp"/>
        </c:forEach>
    </ul>
</li>