<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>

</head>
<body>
<div class="modal fade" id="uploadByURLModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3>Upload image</h3>
            </div>
            <div class="modal-body">
                <div class="row-fluid">
                    <div class="form-group">
                        <input id="imageUrl" value="http://" class="bootstrap-wysihtml5-insert-image-url form-control">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <div class="col-md-6">

                </div>
                <div class="col-md-6">
                    <div class="col-md-6">
                        <a class="btn btn-default aa-review-submit" data-dismiss="modal">Cancel</a>
                    </div>
                    <div class="col-md-6">
                        <a class="btn btn-default aa-review-submit button-active"
                           onclick="UploadImageByUrl($('#imageUrl').val())" data-dismiss="modal">Upload</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<section id="aa-product-details">
    <div class="aa-product-details-area">
        <div class="aa-product-details-content" style="width: 100%">
            <div class="row">
                <div class="col-md-5">
                    <div class="aa-product-view-slider min-height-400 no-padding-top">
                        <div id="product-view-slider" class="simpleLens-gallery-container">

                            <c:set var="currentProduct" value="${currentProduct}" scope="request"/>
                            <jsp:include page="productPreview/productImagesPreview.jsp"/>

                            <div class="row-fluid form-group">
                                <form:form method="POST" action="api/goods/${currentProduct.productId}/image"
                                           modelAttribute="imageInstance"
                                           id="productImageUploader${currentProduct.productId}"
                                           enctype="multipart/form-data" class="dropzone">
                                    <div class="fallback">
                                        <form:input multiple="multiple" type="file" path="file" id="file"
                                                    onchange="$('#productImageUploader${currentProduct.productId}').submit();"/>
                                    </div>
                                </form:form>
                            </div>
                            <div class="row-fluid">
                                <div class="col-md-12 no-padding">
                                    <button data-toggle="modal"
                                            data-target="#uploadByURLModal"
                                            class="btn btn-default aa-review-submit margin-top-30">
                                        Upload by URL
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-7">
                    <form:form method="put" id="updateProductFrom${currentProduct.productId}"
                               enctype="multipart/form-data"
                               role="form">

                        <div class="aa-product-view-content">
                            <div class="aa-product-view-content">
                                <div class="row form-group">
                                    <div class="col-md-3">
                                        Product title:
                                    </div>
                                    <div class="col-md-9 no-padding-right-left">
                                        <input required class="form-control text-bold" name="title"
                                               placeholder="Enter product title"
                                               type="text" value="${currentProduct.title}">
                                    </div>
                                </div>

                                <div class="row form-group">
                                    <div class="col-md-3">
                                        Price:
                                    </div>
                                    <div class="col-md-9 no-padding-right-left">
                                        <div class="input-group">
                                            <span class="input-group-addon">${currency}</span>
                                            <input min="0" step="0.01" name="price" value="${currentProduct.price}"
                                                   class="form-control"
                                                   type="number">
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-3">
                                        Available number:
                                    </div>
                                    <div class="col-md-9 no-padding-right-left form-group">
                                        <input min="0" name="availableAmount" value="${currentProduct.availableAmount}"
                                               class="form-control" type="number">
                                    </div>
                                </div>

                                <div class="row form-group">
                                    <div class="col-md-3">
                                        Category:
                                    </div>
                                    <div class="col-md-9 no-padding-right-left">
                                        <select id="categorySelect"
                                                name="categoryId"
                                                aria-hidden="true"
                                                tabindex="-1"
                                                class="form-control select2 select2-hidden-accessible btn-not-rounded"
                                                onchange="categorySelection('categorySelect', 'subcategorySelect', 'groupSelect');"
                                                style="width: 100%;">
                                            <c:forEach items="${categories}" var="categoryItem">
                                                <c:choose>
                                                    <c:when test="${categoryItem.id==category.id}">
                                                        <option selected="selected"
                                                                value="${categoryItem.id}">${category.title}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${categoryItem.id}">${categoryItem.title}</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="row form-group">
                                    <div class="col-md-3">
                                        Subcategory:
                                    </div>
                                    <div class="col-md-9 no-padding-right-left">
                                        <select id="subcategorySelect"
                                                name=subcategoryId"
                                                aria-hidden="true"
                                                tabindex="-1"
                                                class="form-control select2 select2-hidden-accessible btn-not-rounded"
                                                onchange="subcategorySelection('subcategorySelect', 'groupSelect')"
                                                style="width: 100%;">
                                            <c:forEach items="${category.subcategoriesByCategoryId}"
                                                       var="subcategoryItem">
                                                <c:choose>
                                                    <c:when test="${subcategoryItem.id ==subcategory.id}">
                                                        <option selected="selected"
                                                                value="${subcategoryItem.id}">${subcategory.title}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value=${subcategoryItem.id}>${subcategoryItem.title}</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="row form-group">
                                    <div class="col-md-3">
                                        Group:
                                    </div>
                                    <div class="col-md-9 no-padding-right-left">
                                        <select id="groupSelect"
                                                name="subcategoryGroupId"
                                                aria-hidden="true" tabindex="-1"
                                                class="form-control select2 select2-hidden-accessible btn-not-rounded"
                                                style="width: 100%;">
                                            <c:forEach items="${subcategory.subcategoryGroupsBySubcategoryId}"
                                                       var="groupItem">
                                                <c:choose>
                                                    <c:when test="${groupItem.id ==group.id}">
                                                        <option selected="selected"
                                                                value="${groupItem.id}">${group.title}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${groupItem.id}">${groupItem.title}</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-3">
                                        Description:
                                    </div>
                                    <div class="col-md-9 no-padding-right-left">
                                    <textarea name="generalDescr"
                                              class="form-control max-width-100p text-area-align-left">
                                            ${currentProduct.generalDescr}
                                    </textarea>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-3">
                                        Property class:
                                    </div>
                                    <div class="col-md-9 no-padding-right-left">
                                        <select class="form-control select2 btn-not-rounded" multiple="true"
                                                name="propertiesIds"
                                                data-placeholder="Select a State" style="width: 100%;">
                                            <c:forEach items="${propertyClasses}" var="propertyClass">
                                                <optgroup label="${propertyClass.propertyClassTitle}">
                                                    <c:forEach items="${propertyClass.propertiesByPropertyClassId}"
                                                               var="property">
                                                        <c:set var="selected" value="selected='selected'"/>
                                                        <c:if test="${!currentProduct.propertyProductsByProductId.contains(property)}">
                                                            <c:set var="selected" value=""/>
                                                        </c:if>
                                                        <option value="${property.propertyId}" ${selected}>${property.propertyValue}</option>
                                                    </c:forEach>
                                                </optgroup>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-3">
                                        Warranty months:
                                    </div>
                                    <div class="col-md-9 no-padding-right-left form-group">
                                        <input min="0" name="warrantyMonths" value="${currentProduct.warrantyMonths}"
                                               class="form-control" type="number">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form:form>
                    <div class="row-fluid form-group">
                        <div class="col-md-6 no-padding-left">
                            <button onclick="$('#updateProductFrom${currentProduct.productId}').submit()"
                                    class="btn btn-default aa-review-submit">Submit
                            </button>
                        </div>
                        <div class="col-md-6 no-padding-right-left"
                             onclick="changeAdminContentInnerBlock('product',${currentProduct.productId})">
                            <button class="btn btn-default aa-review-submit">Cancel</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="aa-product-details-bottom">
            <ul class="nav nav-tabs" id="myTab2">
                <li class="active-option"><a href="#description" data-toggle="tab">Description</a></li>
                <li><a href="#review" data-toggle="tab">Reviews</a></li>
            </ul>

            <div class="tab-content">
                <div class="tab-pane fade in active" id="description">
                    <div id="descriptionPartContent">
                        <c:set var="product" value="${currentProduct}" scope="request"/>
                        <jsp:include page="productDescriptionsPartial.jsp"/>
                    </div>

                    <div class="row form-group">
                        <div class="col-md-12">
                            <button id="add-description-btn" class="btn btn-default aa-review-submit"
                                    onclick="addDescription(${currentProduct.productId},'descriptionPartContent')">Add
                                description part
                            </button>
                        </div>
                    </div>
                </div>
                <div class="tab-pane fade " id="review">
                    <c:choose>
                        <c:when test="${currentProduct.commentsByProductId.size()>0}">
                            <div class="aa-product-review-area">
                                <h4>${currentProduct.commentsByProductId.size()} comment for ${product.title}</h4>
                                <ul class="aa-review-nav">
                                    <c:forEach items="${currentProduct.commentsByProductId}" var="comment">
                                        <li>
                                            <div class="media">
                                                <div class="media-left">
                                                    <a>
                                                        <img class="media-object"
                                                             src="${comment.customerByCustomerId.customerImg}">
                                                    </a>
                                                </div>
                                                <div class="media-body">
                                                    <h4 class="media-heading">
                                                        <strong>${comment.customerByCustomerId.customerName}</strong> -
                                                        <span>${comment.commentDate}</span>
                                                        <span class="remove-fade pull-right-container danger margin-5 fa-container remove cursor-hand"
                                                              data-toggle2="tooltip" data-placement="right"
                                                              data-toggle="modal"
                                                              data-target="#modalWrapper"
                                                              title="Remove comment">
                                                        <i class="fa fa-remove pull-right" aria-hidden="true"></i>
                                                        <i class="fa fa-times-circle pull-right i-danger"
                                                           aria-hidden="true"></i>
                                                        </span>
                                                    </h4>
                                                    <div class="aa-product-rating">
                                                        <span class="fa fa-star"></span>
                                                        <span class="fa fa-star"></span>
                                                        <span class="fa fa-star"></span>
                                                        <span class="fa fa-star"></span>
                                                        <span class="fa fa-star-o"></span>
                                                    </div>
                                                    <p>${comment.commentBody}</p>
                                                </div>
                                            </div>
                                        </li>
                                    </c:forEach>

                                </ul>
                            </div>
                        </c:when>
                    </c:choose>
                </div>
            </div>

        </div>
    </div>

</section>

<script>

    /**
     * Main method fired when form is submited
     */
    $('#updateProductFrom${product.productId}').submit(function (e) {
        var frm = $('#updateProductFrom${product.productId}');
        e.preventDefault();
        var json = {};
        $.each(this, function (i, v) {
            var input = $(v);
            json[input.attr("name")] = input.val();
            delete json["undefined"];
        });
        $.ajax({
            type: "POST",
            dataType: "json",
            contentType: 'application/json; charset=utf-8',
            url: "api/goods/${product. productId}",
            data: JSON.stringify(json),
            success: function (response) {
                location.reload();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(errorThrown);
                location.reload();
            }
        });

    });

    /**
     * After image is successfully uploaded
     * Change view of image block-list
     */
    function addPreview() {
        $("#largeImageBTN${product.productId}").removeClass("hidden-block");
        $.ajax({
            type: "GET",
            url: "admin/goods/${product.productId}/imagesPreview",
            success: function (response) {
                document.getElementById("thumbnailsContainer").innerHTML += response;
                location.reload();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(errorThrown);
            }
            , dataType: "html"
        });
    }

    /**
     * Initialize dropzone for uploading images using drag and drop or default method
     */
    var myDropzone = new Dropzone(document.querySelector("#productImageUploader${currentProduct.productId}"), {url: "api/goods/${product.productId}/image"});
    myDropzone.on("complete", function (file) {
        addPreview();
    });

    /**
     * Uploading image by Url
     * @param url
     * @constructor
     */
    function UploadImageByUrl(url) {
        $.ajax({
            type: "POST",
            url: "api/goods/${product.productId}/image",
            data: {url: url},
            success: function (response) {
                location.reload();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(errorThrown);
                location.reload();
            }
            , dataType: "json"
        });
    }

    /**
     * Initializing select for choosing product properties and category, sub, group
     */
    $(".select2").select2();

</script>

