<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    $(document).ready();
</script>
<li class="treeview active" id="subcategoryLi${loopSubcategory.id}">
    <a class="cursor-hand">
        <i class="fa fa-folder"></i>
        <span class="span-overflow" onclick="changeAdminContentInnerBlock('subcategories',${loopSubcategory.id})"
              id="subcategoriesSpan${loopSubcategory.id}">${loopSubcategory.title}</span>
                                                <span class="pull-right-container success margin-top-5 fa-container edit"
                                                      data-toggle2="tooltip" data-placement="right"
                                                      onclick="changeAdminContentInnerBlock('subcategories',${loopSubcategory.id})"
                                                      title="Edit subcategory">
                                                      <i class="fa fa-pencil-square-o pull-right"
                                                         aria-hidden="true"></i>
                                                      <i class="fa fa-pencil-square pull-right i-success"
                                                         aria-hidden="true"></i>
                                                </span>
                                                <span class="pull-right-container success margin-top-5 fa-container plus"
                                                      data-toggle2="tooltip" data-placement="right"
                                                      onclick="addTreeViewItem('${loopSubcategory.id}','admin/groups','subcategoryRootUl')"
                                                      title="Add group">
                                                      <i class="fa fa-plus pull-right" aria-hidden="true"></i>
                                                      <i class="fa fa-plus-circle pull-right i-success"
                                                         aria-hidden="true"></i>
                                                </span>

                                                 <span class="pull-right-container danger margin-top-5 fa-container remove"
                                                       data-toggle2="tooltip" data-placement="right"
                                                       onclick="deleteTreeViewItem('${loopSubcategory.categoryByCategoryId.id}','${loopSubcategory.id}','admin/subcategories/','subcategoryLi','categoryRootUl')"
                                                       title="Remove subcategory">
                                                    <i class="fa fa-remove pull-right" aria-hidden="true"></i>
                                                    <i class="fa fa-times-circle pull-right i-danger"
                                                       aria-hidden="true"></i>
                                                 </span>

        <c:set var="expandeHidden" value="hidden-block"/>
        <c:if test="${loopSubcategory.subcategoryGroupsBySubcategoryId.size()>0}">
            <c:set var="expandeHidden" value=""/>
        </c:if>
                                                 <span id="subcategoryRootUlExp${loopSubcategory.id}"
                                                       class="pull-right-container margin-top-5 fa-container collapsable ${expandeHidden}"
                                                       onclick="var $this = this;var liParrent = getParentByTagName($this, 'li');$(liParrent).toggleClass('active');">
                                                  <i class="fa fa-angle-left pull-right" aria-hidden="true"></i>
                                                  <i class="fa fa-chevron-circle-left pull-right margin-right-5"
                                                     aria-hidden="true"></i>
                                                 </span>
    </a>
    <ul class="treeview-menu menu-open" id="subcategoryRootUl${loopSubcategory.id}">
        <c:forEach items="${loopSubcategory.subcategoryGroupsBySubcategoryId}"
                   var="group">
            <c:set var="loopGroup" value="${group}" scope="request"/>
            <jsp:include page="groupTreeViewItem.jsp"/>
        </c:forEach>
    </ul>
</li>