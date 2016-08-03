<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<a class="dropdown-toggle" data-toggle="dropdown">
    <i class="fa fa-heart-o" id="wishesMenu"></i>
    <span class="label label-primary">${menuWishes.size()}</span>
</a>
<ul class="dropdown-menu">
    <c:choose>
        <c:when test="${menuWishes.size()==0}">
            <li class="header">You have not wishes yet</li>
        </c:when>
        <c:otherwise>
            <li class="header">You have ${menuWishes.size()} wishes</li>
            <li>
                <c:set var="items" value="${menuWishes}}" scope="request"/>
                <c:set var="href" value="/profile?block=wishes" scope="request"/>
                <jsp:include page="userMenuContent.jsp"/>
            </li>
        </c:otherwise>
    </c:choose>
    <li class="footer"><a href="/profile?block=wishes">View all wishes</a></li>
</ul>