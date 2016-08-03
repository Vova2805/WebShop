<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
    $(document).ready();
</script>

<section class="content-header">
    <h1>
        WebShop Admin Properties
    </h1>
    <ol class="breadcrumb">
        <li onclick="changeAdminContent('dashboard')"><a class="cursor-hand"><i
                class="fa fa-dashboard"></i> <span>Dashboard</span></a></li>
        <li onclick="changeAdminContent('full')"><a class="cursor-hand"><i
                class="fa fa-dashboard"></i> <span>Content treeview</span></a></li>
        <li class="active">Properties</li>
    </ol>
</section>


<section class="content">
    <div class="row">
        <c:set var="propertiesClasses" value="${propertiesClasses}" scope="request"/>
        <div class="col-md-3 invisible-scrollbar" id="propertyTreeview">
            <jsp:include page="adminPropertiesTreeview.jsp"/>
        </div>
        <div class="col-md-9 added-scrollbar invisible-scrollbar" style="padding-left: 0px;" id="propertyContentInner">

        </div>
    </div>
</section>



