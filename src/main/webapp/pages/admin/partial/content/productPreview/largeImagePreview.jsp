<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    //    $(document).ready();
</script>
<li class="preview-large no-margin-top">
    <figure>
        <a href="/goods/${currentProduct.productId}"
           class="aa-product-img cursor-hand">
            <img id="largeImageIMG" src="${currentProduct.firstImage.images['medium']}"
                 style="padding: 30px;"
                 class="product-image">
            </img>
        </a>
        <c:set var="hiddenClass" value="hidden-block"/>
        <c:if test="${currentProduct.imagesByProductId.size()>0}">
            <c:set var="hiddenClass" value=""/>
        </c:if>
        <a id="largeImageBTN${currentProduct.productId}" class="aa-add-card-btn cursor-hand ${hiddenClass}"
           onclick="deleteImage('${currentProduct.productId}', '${currentProduct.firstImage.imageId}')">
                    <span class="fa fa-remove">
                    </span>Delete image</a>

    </figure>
</li>