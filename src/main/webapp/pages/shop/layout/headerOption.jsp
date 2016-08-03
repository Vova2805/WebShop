<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<script>
    $(document).ready();
</script>
<div class="navbar-custom-menu">
    <c:choose>
        <c:when test="${empty pageContext.request.userPrincipal}">
            <ul class="nav navbar-nav">
                <li>
                    <a class="dropdown-toggle cursor-hand" href="login">
                        <i class="fa fa-sign-in"></i> Login
                    </a>
                </li>
                <li>
                    <a href="signup" class="dropdown-toggle cursor-hand">
                        <i class="fa fa-key"></i> Sign up
                    </a>
                </li>
            </ul>
        </c:when>
        <c:otherwise>
            <ul class="nav navbar-nav">
                <sec:authorize access="isAuthenticated()">
                    <sec:authorize access="hasRole('USER')">
                        <c:set var="activeUser" value="${activeUser}" scope="request"/>
                        <li class="dropdown messages-menu">
                            <jsp:include page="../../customer/partial/menu/comparisonDropdown.jsp"/>
                        </li>

                        <li class="dropdown messages-menu">
                            <jsp:include page="../../customer/partial/menu/wishesDropdown.jsp"/>
                        </li>

                        <li class="dropdown messages-menu" id="cartMenu">
                            <jsp:include page="../../customer/partial/menu/cartDropdown.jsp"/>
                        </li>
                    </sec:authorize>

                    <li class="dropdown user user-menu">
                        <a class="dropdown-toggle cursor-hand" data-toggle="dropdown">
                            <img src="${activeUser.images['avatar_small']}" class="user-image">
                            <span class="hidden-xs">${pageContext.request.userPrincipal.name}</span>
                        </a>
                        <ul class="dropdown-menu">
                            <li class="user-header">
                                <img src="${activeUser.images['standart_128']}" class="img-circle">
                            </li>
                            <sec:authorize access="hasRole('USER')">
                                <jsp:include page="../../customer/layout/customerSideMenuContent.jsp"/>
                            </sec:authorize>

                            <sec:authorize access="hasAnyRole('SUPERADMIN','ADMIN')">
                                <c:set var="showSubTree" value="false" scope="request"/>
                                <jsp:include page="../../admin/layout/adminSideMenuContent.jsp"/>
                            </sec:authorize>

                            <c:url value="/j_spring_security_logout" var="logoutUrl"/>
                            <form action="${logoutUrl}" method="post" id="logoutForm">
                                <input type="hidden" name="${_csrf.parameterName}"
                                       value="${_csrf.token}"/>
                            </form>
                            <li class="user-footer">
                                <div class="pull-left">
                                    <sec:authorize access="hasRole('USER')">
                                        <c:set var="profileHref" value="profile"/>
                                    </sec:authorize>

                                    <sec:authorize access="hasAnyRole('SUPERADMIN','ADMIN')">
                                        <c:set var="profileHref" value="/admin?block=profile"/>
                                    </sec:authorize>
                                    <a href="${profileHref}" class="btn btn-default btn-flat">Profile</a>
                                </div>
                                <div class="pull-right">
                                    <a onclick="document.getElementById('logoutForm').submit();"
                                       class="btn btn-default btn-flat">Log out</a>
                                </div>
                            </li>
                        </ul>
                    </li>
                </sec:authorize>
            </ul>
        </c:otherwise>
    </c:choose>
</div>