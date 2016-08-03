<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
    $(document).ready();
</script>

<section class="content-header">
    <h1>
        WebShop Admin Advertisements
    </h1>
    <ol class="breadcrumb">
        <li><a class="cursor-hand"><i
                class="fa fa-dashboard"></i> <span>Dashboard</span></a></li>
        <li onclick="changeAdminContent('full')"><a class="cursor-hand"><i
                class="fa fa-sitemap"></i> <span>Content treeview</span></a></li>
        <li class="active">Advertisements</li>
    </ol>
</section>

<section class="content">
    <div class="row form-group">
        <div class="col-md-12 invisible-scrollbar" id="advertisementContainer">
            <c:forEach items="${advertisements}" var="advertisement">
                <c:set var="advertisement" value="${advertisement}" scope="request"/>
                <jsp:include page="advertisementItem.jsp"/>
            </c:forEach>
        </div>
        <div class="row-fluid">
            <div class="col-md-12">
                <button class="btn btn-default aa-review-submit" onclick="AddNewAdvertisement()">
                    Add new advertisement
                </button>
            </div>
        </div>
    </div>
</section>


