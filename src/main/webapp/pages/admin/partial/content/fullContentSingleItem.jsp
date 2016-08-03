<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<section id="aa-product-details">
    <div class="aa-product-details-area">
        <div class="aa-product-details-content" style="width: 100%">
            <div class="row">
                <div class="col-md-3">
                    <div class="aa-product-catg-body no-margin">
                        <ul class="aa-product-catg" style="width: 100%;">
                            <li style="width: 100%;" class="small-li">
                                <figure style="width: 100%;" class="height-170">
                                    <a class="aa-product-img cursor-hand">
                                        <img id="itemImage" src="${object.images['standart_128']}"
                                        ></a>
                                    <div class="row-fluid form-group hidden-form">
                                        <input type="file" name="file" id="contentItem${objectId}"/>
                                    </div>
                                    <a onclick="submitUploadFileInput('contentItem${objectId}')"
                                       class="aa-add-card-btn cursor-hand"><span class="fa fa-image"></span>Change image</a>
                                </figure>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-9">
                    <form:form method="post" id="updateObjectForm" action="api/${type}/${objectId}"
                               modelAttribute="object" role="form">
                        <div class="aa-product-view-content">
                            <div class="aa-product-view-content">
                                <div class="row form-group">
                                    <div class="col-md-3">
                                            ${objectType} title:
                                    </div>
                                    <div class="col-md-9 no-padding-right">
                                        <input class="form-control text-bold" name="title"
                                               placeholder="Enter product title"
                                               type="text" value="${object.title}">
                                    </div>
                                </div>

                                <c:if test="${level>0}">
                                    <div class="row form-group">
                                        <div class="col-md-3">
                                            Category:
                                        </div>
                                        <div class="col-md-9 no-padding-right">
                                            <select id="categorySelect" name="categoryId" aria-hidden="true"
                                                    tabindex="-1"
                                                    class="form-control select2 select2-hidden-accessible"
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
                                    <c:if test="${level>1}">
                                        <div class="row form-group">
                                            <div class="col-md-3">
                                                Subcategory:
                                            </div>
                                            <div class="col-md-9 no-padding-right">
                                                <select id="subcategorySelect" name="subcategoryId" aria-hidden="true"
                                                        tabindex="-1"
                                                        class="form-control select2 select2-hidden-accessible"
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
                                    </c:if>
                                </c:if>
                            </div>
                        </div>
                    </form:form>
                    <div class="row-fluid form-group">
                        <div class="col-md-6 no-padding-left">
                            <button type="button" class="btn btn-default aa-review-submit"
                                    onclick="$('#updateObjectForm').submit()">Submit
                            </button>
                        </div>
                        <div class="col-md-6 no-padding-right">
                            <button type="button" class="btn btn-default aa-review-submit"
                                    onclick="$('#contentInner').html('');">
                                Cancel
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<script>

    /**
     * Initialize select 2 controls
     */
    $(".select2").select2();

    /**
     * Uploading images
     */
    $("#categorySelect").change(function () {
        categorySelection('categorySelect', 'subcategorySelect', 'groupSelect');
    });
    $('#updateObjectForm').submit(function (e) {
        e.preventDefault();
        var data = {};
        $.each(this, function (i, v) {
            var element = $(v);
            data[element.attr("name")] = element.val();
            delete data["undefined"];
        });
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "api/${type}/${objectId}",
            data: JSON.stringify(data),
            dataType: "json",
            success: function (response) {
                document.getElementById('${type}Span${objectId}').innerHTML = response.title;
                location.reload();
            }
        });
    });

    $("#contentItem${objectId}").on('change', prepareLoad);
    var files;
    function prepareLoad(event) {
        files = event.target.files;
        uploadItemImage('${type}', '${objectId}');
    }

    $(document).ready();
</script>