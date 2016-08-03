<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    //    $(document).ready();
</script>
<c:forEach items="${product.descriptionPartsByProductId}" var="description">
    <c:set var="description" value="${description}" scope="request"/>
    <c:set var="product" value="${product}" scope="request"/>
    <c:set var="editable" value="false"/>
    <jsp:include page="descriptionPartPartial.jsp"/>
</c:forEach>