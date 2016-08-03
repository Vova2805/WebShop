<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    $(document).ready();
    treeviewInit();
</script>

<section class="sidebar">
    <ul class="inner-treeview" id="mainRootUl">
        <li class="treeview active">
            <a class="active-option cursor-hand">
                <i class="fa fa-sitemap"></i>
                <span onclick="$('#contentInner').html('');">Content multilevel</span>

                                    <span class="pull-right-container success margin-top-5 fa-container refresh"
                                          data-toggle2="tooltip" data-placement="right"
                                          onclick="updateContentTreeView(); "
                                          title="Refresh treeview">
                                               <i class="fa fa-refresh pull-right" aria-hidden="true"></i>
                                     </span>
                                    <span class="pull-right-container success margin-top-5 fa-container edit"
                                          data-toggle2="tooltip" data-placement="right"
                                          onclick="$('.sidebar li :not(:first-child)').addClass('active')"
                                          title="Expand">
                                          <i class="fa fa-expand pull-right" aria-hidden="true"></i>
                                     </span>
                                    <span class="pull-right-container success margin-top-5 fa-container plus"
                                          data-toggle2="tooltip" data-placement="right"
                                          onclick="$('.sidebar li :not(:first-child)').removeClass('active')"
                                          title="Collapse">
                                            <i class="fa fa-compress pull-right" aria-hidden="true"></i>
                                     </span>
                         <span class="pull-right-container success margin-top-5 fa-container remove"
                               data-toggle2="tooltip" data-placement="right"
                               onclick="addCategory()"
                               title="Add category">
                                            <i class="fa fa-plus pull-right" aria-hidden="true"></i>
                                            <i class="fa fa-plus-circle pull-right i-success" aria-hidden="true"></i>
                         </span>
                         <span class="pull-right-container margin-top-5 fa-container collapsable"
                               onclick="var $this = this;var liParrent = getParentByTagName($this, 'li');$(liParrent).toggleClass('active');">
                          <i class="fa fa-angle-left pull-right" aria-hidden="true"></i>
                          <i class="fa fa-chevron-circle-left pull-right margin-right-5" aria-hidden="true"></i>
                         </span>
            </a>
            <ul class="treeview-menu menu-open" id="categoryRootUl">
                <c:forEach items="${categories}" var="category">
                    <c:set var="loopCategory" value="${category}" scope="request"/>
                    <jsp:include page="categoryTreeViewItem.jsp"/>
                </c:forEach>
            </ul>
        </li>
    </ul>
</section>

