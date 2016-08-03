<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Web shop | Authorization</title>
</head>

<body class="productPage">
<jsp:include page="reference/commonReferences.jsp"/>
<jsp:include page="reference/adminReferences.jsp"/>
<jsp:include page="shop/layout/header.jsp"/>

<section id="aa-myaccount">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="aa-myaccount-area">
                    <div class="row">
                        <div class="col-md-3"></div>
                        <div class="col-md-6">
                            <div class="row">
                                <jsp:include page="/pages/shop/layout/validation.jsp"/>
                            </div>
                            <div class="row" id="dinamicLoginArea">
                                <c:choose>
                                    <c:when test="${block == 'login'}">
                                        <jsp:include page="shop/partial/loginPartial.jsp"/>
                                    </c:when>
                                    <c:otherwise>
                                        <jsp:include page="shop/partial/registerPartial.jsp"/>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <div class="col-md-3"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="shop/layout/footer.jsp"/>

</body>
</html>
