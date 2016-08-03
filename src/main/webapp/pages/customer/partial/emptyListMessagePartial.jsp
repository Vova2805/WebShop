<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section id="aa-error">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="aa-error-area">
                    <span>${mainHeader}</span>
                    <p>${body}</p>
                    <c:set var="visibility" value=""/>
                    <c:if test="${not empty buttonVisible and buttonVisible==false}">
                        <c:set var="visibility" value="style='display:none;'"/>
                    </c:if>
                    <a ${visibility} href="/"> Go Shopping</a>
                </div>
            </div>
        </div>
    </div>
</section>