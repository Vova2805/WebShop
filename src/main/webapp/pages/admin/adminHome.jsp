<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Web Shop Admin panel</title>
    <jsp:include page="../reference/commonReferences.jsp"/>
    <jsp:include page="../reference/adminReferences.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div>
    <div class="wrapper container-fluid min-height-100p no-padding" style="position: relative;height: 100%;">
        <jsp:include page="layout/adminHeader.jsp"/>

        <jsp:include page="layout/adminSideMenu.jsp"/>

        <div class="invisible-scrollbar min-height-1000">
            <div class='content-wrapper' id="adminContentArea" style="min-height: 100%">
                <c:set var="categories" value="${categories}" scope="request"/>
                <c:set var="category" value="${category}" scope="request"/>
                <jsp:include page="${page}"/>
            </div>
        </div>
    </div>
</div>
</body>
</html>
