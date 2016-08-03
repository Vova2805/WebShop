<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:choose>
    <c:when test="${description.hasImg==true}">
        <c:set var="hiddenClass" value=""/>
    </c:when>
    <c:otherwise>
        <c:set var="hiddenClass" value="hidden-block"/>
    </c:otherwise>
</c:choose>
<div class="form-group">
    <div id="descrPreview${description.descriptionPartId}">
        <div class="box">
            <div class="box-header with-border">
                <h3 class="box-title"
                    id="descrPreviewHeader${description.descriptionPartId}">${description.descriptionPartHeader}</h3>
                <div class="box-tools pull-right">
                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                            class="fa fa-minus"></i>
                    </button>
                </div>
            </div>
            <div class="box-body">
                <div class="faded-buttons-area description-part">
                    <figure id="descrPreviewBody${description.descriptionPartId}">
                        ${description.descriptionPartBody}
                    </figure>
                    <div class="aa-product-hvr-content relative">
                        <a class="cursor-hand"
                           onclick="tooglePartsVisibility('descrPreview${description.descriptionPartId}','descrEditable${description.descriptionPartId}')"
                           data-toggle="tooltip"
                           data-placement="top" title="Edit">
                            <span class="fa fa-pencil"></span></a>
                        <a class="cursor-hand"
                           onclick="removeDescription('descriptionPartContent',${product.productId},${description.descriptionPartId})"
                           data-toggle2="tooltip" data-placement="top" title="Remove"><span
                                class="fa fa-remove"></span></a>
                    </div>
                </div>
                <div id="descrPreview${description.descriptionPartId}Img" class="${hiddenClass}">
                    <div class="row">
                        <img id="descriptionImg${description.descriptionPartId}preview" src="${description.imageSrc}"
                             class="description-img">
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div id="descrEditable${description.descriptionPartId}" class="aa-review-form hidden-form">
        <div class="box">
            <form:form method="POST"
                       action="admin/goods/${product.productId}/descriptions/${description.descriptionPartId}"
                       modelAttribute="description"
                       cssClass="no-padding" id="descriptionPartForm${description.descriptionPartId}">

                <div class="box-header with-border">
                    <input name="descriptionPartHeader" class="form-control text-bold"
                           value="${description.descriptionPartHeader}"/>
                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                                class="fa fa-minus"></i>
                        </button>
                    </div>
                </div>
                <div class="box-body">
                    <div class="form-group">
                                                        <textarea id="text-area-${description.descriptionPartId}"
                                                                  name="descriptionPartBody" class="textarea editor"
                                                                  placeholder="Place some text here">${description.descriptionPartBody}
                                                        </textarea>
                    </div>
                </div>
            </form:form>
            <div class="box-body no-padding-top">
                <div class="row-fluid form-group hidden-form">
                    <input type="file" name="file" id="descriptionFileUploader${description.descriptionPartId}"/>
                </div>
                <div class="form-group display-block">
                    <a class="btn btn-app btn-not-rounded btn-default no-margin button-green-theme"
                       onclick="submitUploadFileInput('descriptionFileUploader${description.descriptionPartId}')">
                        <i class="fa fa-upload"></i> Upload image
                    </a>

                    <a id="removeDescrImg${description.descriptionPartId}"
                       class="btn btn-app btn-not-rounded btn-default no-margin button-green-theme ${hiddenClass}"
                       onclick="removeDescriptionImage('${product.productId}','${description.descriptionPartId}')">
                        <i class="fa fa-times"></i> Remove image
                    </a>
                </div>
                <div id="descrEditable${description.descriptionPartId}Img" class="${hiddenClass}">
                    <div class="row">
                        <img id="descriptionImg${description.descriptionPartId}editable" src="${description.imageSrc}"
                             class="description-img">
                    </div>
                </div>
            </div>

            <div class="box-footer">
                <div class="row form-group">
                    <div class="col-md-6">
                        <button type="submit"
                                onclick="$('#descriptionPartForm${description.descriptionPartId}').submit()"
                                class="btn btn-default aa-review-submit">Submit
                        </button>
                    </div>
                    <div class="col-md-6 no-padding-right">
                        <button class="btn btn-default aa-review-submit"
                                onclick="tooglePartsVisibility('descrPreview${description.descriptionPartId}','descrEditable${description.descriptionPartId}')">
                            Cancel
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $('#descriptionPartForm${description.descriptionPartId}').submit(function (e) {
        var frm = $('#descriptionPartForm${description.descriptionPartId}');
        e.preventDefault();
        var data = {};
        $.each(this, function (i, v) {
            var element = $(v);
            data[element.attr("name")] = element.val();
            delete data["undefined"];
        });
        $.ajax({
            type: "PUT",
            contentType: "application/json; charset=utf-8",
            url: "api/goods/${product.productId}/descriptions/${description.descriptionPartId}",
            data: JSON.stringify(data),
            dataType: "json",
            success: function (response) {
                document.getElementById('descrPreviewHeader${description.descriptionPartId}').innerHTML = response.descriptionPartHeader;
                document.getElementById('descrPreviewBody${description.descriptionPartId}').innerHTML = response.descriptionPartBody;
                tooglePartsVisibility('descrPreview${description.descriptionPartId}', 'descrEditable${description.descriptionPartId}')
            }
        });
    });

    $("#descriptionFileUploader${description.descriptionPartId}").on('change', prepareLoad);
    var files;
    function prepareLoad(event) {
        files = event.target.files;
        uploadDescriptionImage(${product.productId}, ${description.descriptionPartId});
    }
</script>