<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<li><a href="/admin?block=dashboard" class="cursor-hand"><i
        class="fa fa-dashboard"></i> <span>Dashboard</span></a></li>
<li><a href="/admin?block=profile"><i
        class="fa fa-user"></i> <span>Personal data</span></a></li>

<c:choose>
    <c:when test="${showSubTree=='true'}">
        <li class="treeview active">
            <a class="cursor-hand">
                <i class="fa fa-sitemap"></i> <span>Content treeview</span>
                    <span class="pull-right-container collapsable"
                          onclick="var $this = this;var liParrent = getParentByTagName($this, 'li');$(liParrent).toggleClass('active');">
                      <i class="fa fa-angle-left pull-right"></i>
                    </span>
            </a>
            <ul class="treeview-menu menu-open">
                <li><a class="cursor-hand" href="/admin?block=full"><i
                        class="fa fa-circle-o"></i> <span>Full</span></a></li>
                <li><a href="/admin?block=advertisements" class="cursor-hand"><i
                        class="fa fa-circle-o"></i> <span>Advertisement</span></a></li>
                <li><a href="/admin?block=properties" class="cursor-hand"><i
                        class="fa fa-circle-o"></i> <span>Properties</span></a></li>
            </ul>
        </li>
    </c:when>
    <c:otherwise>
        <li><a href="/admin?block=full" class="cursor-hand"><i
                class="fa fa-sitemap"></i> <span>Content treeview</span></a></li>
    </c:otherwise>
</c:choose>

<li><a href="/admin?block=users" class="cursor-hand"><i
        class="fa fa-users"></i> <span>Users</span></a></li>
<li><a href="/admin?block=orders" class="cursor-hand"><i
        class="fa fa-list"></i> <span>Orders</span></a></li>

