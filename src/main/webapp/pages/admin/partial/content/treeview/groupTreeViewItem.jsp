<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<li class="treeview active" id="groupLi${loopGroup.id}">
    <a class="cursor-hand">
        <i class="fa fa-object-group"></i>
        <span class="span-overflow group" onclick="changeAdminContentInnerBlock('groups',${loopGroup.id})"
              id="groupsSpan${loopGroup.id}">${loopGroup.title}</span>
                                                                <span class="pull-right-container success margin-top-5 fa-container edit"
                                                                      onclick="changeAdminContentInnerBlock('groups',${loopGroup.id})"
                                                                      data-toggle2="tooltip" data-placement="right"
                                                                      title="Edit group">
                                                                      <i class="fa fa-pencil-square-o pull-right"
                                                                         aria-hidden="true"></i>
                                                                      <i class="fa fa-pencil-square pull-right i-success"
                                                                         aria-hidden="true"></i>
                                                                </span>

                                                                <span class="pull-right-container success margin-top-5 fa-container plus"
                                                                      data-toggle2="tooltip" data-placement="right"
                                                                      onclick="addTreeViewItem('${loopGroup.id}','admin/goods','groupRootUl')"
                                                                      title="Add product">
                                                                      <i class="fa fa-plus pull-right"
                                                                         aria-hidden="true"></i>
                                                                      <i class="fa fa-plus-circle pull-right i-success"
                                                                         aria-hidden="true"></i>
                                                                </span>

                                                                 <span class="pull-right-container danger margin-top-5 fa-container remove"
                                                                       onclick="deleteTreeViewItem('${loopGroup.subcategoryBySubcategoryId.id}','${loopGroup.id}','admin/groups/','groupLi','subcategoryRootUl')"
                                                                       data-toggle2="tooltip" data-placement="right"
                                                                       title="Remove group">
                                                                    <i class="fa fa-remove pull-right"
                                                                       aria-hidden="true"></i>
                                                                    <i class="fa fa-times-circle pull-right i-danger"
                                                                       aria-hidden="true"></i>
                                                                 </span>

        <c:set var="expandeHidden" value="hidden-block"/>
        <c:if test="${loopGroup.goodsBySubcategoryGroupId.size()>0}">
            <c:set var="expandeHidden" value=""/>
        </c:if>
                                                                 <span id="groupRootUlExp${loopGroup.id}"
                                                                       class="pull-right-container margin-top-5 fa-container collapsable ${expandeHidden}"
                                                                       onclick="var $this = this;var liParrent = getParentByTagName($this, 'li');$(liParrent).toggleClass('active');">
                                                                  <i class="fa fa-angle-left pull-right"
                                                                     aria-hidden="true"></i>
                                                                  <i class="fa fa-chevron-circle-left pull-right margin-right-5"
                                                                     aria-hidden="true"></i>
                                                                 </span>
    </a>
    <ul class="treeview-menu menu-open" id="groupRootUl${loopGroup.id}">
        <c:forEach
                items="${loopGroup.goodsBySubcategoryGroupId}"
                var="product">
            <c:set var="product" value="${product}" scope="request"/>
            <jsp:include page="productTreeViewItem.jsp"/>
        </c:forEach>
    </ul>
</li>