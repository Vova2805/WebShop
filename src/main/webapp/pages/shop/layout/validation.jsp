<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty error}">
    <div class="bs-example">
        <div class="alert alert-danger alert-error">
            <a href="#" class="close" data-dismiss="alert">&times;</a>
            <strong>Error!</strong> <c:out value="${error}"/>
        </div>
    </div>
</c:if>

<c:if test="${not empty success}">
    <div class="bs-example">
        <div class="alert alert-success">
            <a href="#" class="close" data-dismiss="alert">&times;</a>
            <strong>Success!</strong> <c:out value="${success}"/>
        </div>
    </div>
</c:if>

<c:if test="${not empty logout}">
    <div class="bs-example">
        <div class="alert alert-success">
            <a href="#" class="close" data-dismiss="alert">&times;</a>
            <strong>Success!</strong> <c:out value="${logout}"/>
        </div>
    </div>
</c:if>
