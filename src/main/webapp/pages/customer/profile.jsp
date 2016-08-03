<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Web Shop | Profile</title>
    <script>
        $(document).ready();
    </script>
    <jsp:include page="../reference/commonReferences.jsp"/>
    <jsp:include page="../reference/adminReferences.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div>
    <div class="wrapper container-fluid min-height-100p no-padding" style="position: relative;height: 100%;">

        <jsp:include page="../admin/layout/adminHeader.jsp"/>
        <jsp:include page="layout/customerSideMenu.jsp"/>

        <div class="invisible-scrollbar min-height-1000">
            <div class='content-wrapper' style="min-height: 100%" id="profileBody">
                <jsp:include page="partial/${page}"/>
            </div>
        </div>
    </div>
</div>
</body>
</html>
