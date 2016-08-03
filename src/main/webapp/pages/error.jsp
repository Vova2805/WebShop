<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Web shop | Opps</title>
</head>
<body>
<jsp:include page="reference/commonReferences.jsp"/>
<jsp:include page="reference/adminReferences.jsp"/>
<jsp:include page="shop/layout/header.jsp"/>

<section id="aa-error">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="aa-error-area">
                    <h2>404</h2>
                    <span>Sorry! Page Not Found or Access is denied</span>
                    <p>Sorry but your request is invalid. Check url and try again</p>
                    <a href="/"> Go to Homepage</a>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="shop/layout/footer.jsp"/>
</body>
</html>
