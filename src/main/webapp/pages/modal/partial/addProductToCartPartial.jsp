<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<div class="row">
    <div class="col-md-6 col-sm-6 col-xs-12">
        <div class="aa-product-view-slider">
            <div id="product-view-slider" class="simpleLens-gallery-container">
                <div class="simpleLens-container">
                    <div class="simpleLens-big-image-container"><a
                            data-lens-image="${product.firstImage.images['large']}"
                            class="simpleLens-lens-image" style="width: 270px">
                        <img src="${product.firstImage.images['medium']}"
                             class="simpleLens-big-image"></a></div>
                </div>
                <div class="simpleLens-thumbnails-container">
                    <c:forEach items="${product.imagesByProductId}" var="image">
                        <a data-big-image="${image.images['medium']}"
                           data-lens-image="${image.images['large']}"
                           class="simpleLens-thumbnail-wrapper" href="#">
                            <img src="${image.images['small']}">
                        </a>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>

    <div class="col-md-6 col-sm-6 col-xs-12 no-padding-left">
        <div class="aa-product-view-content">
            <h3>${product.title}</h3>
            <div class="row-fluid aa-price-block">
                <p>
                    Price for one:<span class="aa-product-view-price">  ${currency} ${product.price}</span></p>
            </div>
            <div class="row-fluid aa-price-block">
                <p>
                    Availability: <span>${product.availability}</span></p>
            </div>
            <input type="number" id="amount${product.productId}"
                   class="form-control"
                   min="1" max="1000" value="1"
                   step="1" placeholder="Amount">
            <div>
                <h4>Price for one ${currency} ${product.price}</h4>
                <h4 id="result${product.productId}"></h4>
            </div>

            <div class="aa-prod-view-bottom row-fluid">
                <div class="col-md-6 no-padding-left">
                    <a class="btn btn-default aa-review-submit" data-dismiss="modal"
                       onclick="addProductToCart('${product.productId}',$('#amount${product.productId}').val())">Add</a>
                </div>
                <div class="col-md-6 no-padding-right">
                    <a class="btn btn-default aa-review-submit" data-dismiss="modal" id="hideModal">Cancel</a>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $('#amount${product.productId}').on("input", function () {
        var price = ${product.price};
        var amount = ($("#amount${product.productId}").val());
        var total = price * amount;
        var outputStr = "Total : ${currency} " + total;
        $("#result${product.productId}").text(outputStr);
    });

    function addProductToCart(productId, amount) {
        var json = {
            productId: productId,
            amount: amount
        };
        $.ajax({
            type: "POST",
            url: 'api/cart',
            dataType: "json",
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(json),
            success: function (response) {
                //change menu
                updateUserMenuItems('cartMenu');
            },
            error: function (code) {
                console.log(code);
            }
        });
    }
</script>


