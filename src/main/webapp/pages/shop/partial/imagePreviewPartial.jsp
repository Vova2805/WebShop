<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:forEach items="${currentProduct.imagesByProductId}" var="image">
    <c:set var="image" value="${image}" scope="request"/>
    <jsp:include page="smallImagePreviewItem.jsp"/>
</c:forEach>

