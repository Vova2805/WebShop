<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Web shop | Print invoice</title>
    <jsp:include page="../reference/commonReferences.jsp"/>
    <jsp:include page="../reference/shopReferences.jsp"/>
</head>
<body onload="window.print();">
<jsp:include page="partial/invoiceContentPartial.jsp"/>
</body>
</html>
