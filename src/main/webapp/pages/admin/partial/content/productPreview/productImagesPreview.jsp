<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="previewContainer">
    <div class="aa-product-catg" id="largeImagePreview">
        <c:set var="currentProduct" value="${currentProduct}" scope="request"/>
        <jsp:include page="largeImagePreview.jsp"/>
    </div>
    <div class="simpleLens-thumbnails-container form-group" id="thumbnailsContainer">
        <c:set var="currentProduct" value="${currentProduct}" scope="request"/>
        <jsp:include page="/pages/shop/partial/imagePreviewPartial.jsp"/>
    </div>
</div>