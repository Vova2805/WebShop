<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<a class="dropdown-toggle" data-toggle="dropdown">
    <i class="fa fa-exchange" id="comparisonMenu"></i>
    <span class="label label-info" id="comparedProductSpan">${menuComparisons.size()}</span>
</a>
<ul class="dropdown-menu">
    <c:choose>
        <c:when test="${menuComparisons.size()==0}">
            <li class="header">You have not compared goods yet</li>
        </c:when>
        <c:otherwise>
            <li class="header">You have ${menuComparisons.size()} compared goods
            </li>
            <li>
                <c:set var="items" value="${menuComparisons}" scope="request"/>
                <c:set var="href" value="/profile?block=comparisons" scope="request"/>
                <jsp:include page="userMenuContent.jsp"/>
            </li>
        </c:otherwise>
    </c:choose>
    <li class="footer"><a href="/profile?block=comparisons">View all comparisons</a></li>
</ul>