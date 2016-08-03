<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<section class="content border-green-theme form-group" id="advertisementItem${advertisement.advertisementId}">
    <div class="row">
        <div class="col-md-5 invisible-scrollbar">
            <div class="aa-product-catg-body no-margin">
                <div class="aa-product-catg-body no-margin">
                    <ul class="aa-product-catg" style="width: 100%;">
                        <li style="width: 100%;" class="small-li">
                            <figure style="width: 100%;" class="height-170">
                                <a class="aa-product-img cursor-hand">
                                    <img id="advertImg${advertisement.advertisementId}"
                                         src="${advertisement.images['huge']}" style="max-width: 100%"/>
                                </a>
                                <div class="row-fluid form-group hidden-form">
                                    <input type="file" name="file" id="imageUploader${advertisement.advertisementId}"/>
                                </div>
                                <a onclick="submitUploadFileInput('imageUploader${advertisement.advertisementId}')"
                                   class="aa-add-card-btn cursor-hand"><span class="fa fa-image"></span>Change image</a>
                            </figure>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="col-md-7 added-scrollbar invisible-scrollbar" style="padding-left: 0px;" id="propertyContentInner">
            <form:form id="updateAdvertisementForm${advertisement.advertisementId}" class="aa-review-form">
                <div class="content min-height-255 no-padding-top">
                    <div class="row form-group">
                        <div class="col-md-3">
                            Title:
                        </div>
                        <div class="col-md-9 no-padding-right form-group">
                            <input type="text" name="title" class="form-control text-bold"
                                   value="${advertisement.title}"/>
                        </div>
                    </div>

                    <div class="row form-group">
                        <div class="col-md-3">
                            Body:
                        </div>
                        <div class="col-md-9 no-padding-right form-group">
                            <textarea name="body" class="form-control max-width-100p text-area-align-left">
                                    ${advertisement.body}
                            </textarea>
                        </div>
                    </div>

                    <div class="row form-group">
                        <div class="col-md-3">
                            Reference to:
                        </div>
                        <div class="col-md-9 no-padding-right form-group">
                            <div class="row-fluid">
                                <div class="col-md-4" id="ref${advertisement.advertisementId}">
                                    <a href="${advertisement.buttonHref}"> ${advertisement.buttonHref}</a>
                                </div>
                                <div class="col-md-8 no-padding-right">
                                    <select id="searchingSelect${advertisement.advertisementId}" style="width: 100%;">
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row form-group">
                        <div class="col-md-4">
                            <button type="submit" class="btn btn-default aa-review-submit">Submit
                            </button>
                        </div>
                        <div class="col-md-4 no-padding-right">
                            <button type="reset" class="btn btn-default aa-review-submit">
                                Cancel
                            </button>
                        </div>
                        <div class="col-md-4 no-padding-right">
                            <button onclick="deleteAdvertisement('${advertisement.advertisementId}')" type="button"
                                    class="btn btn-default aa-review-submit">
                                Remove
                            </button>
                        </div>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</section>
<script>

    $('#updateAdvertisementForm${advertisement.advertisementId}').submit(function (e) {
        var frm = $('#updateAdvertisementForm${advertisement.advertisementId}');
        e.preventDefault();
        var data = {};
        $.each(this, function (i, v) {
            var element = $(v);
            data[element.attr("name")] = element.val();
            delete data["undefined"];
        });
        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: "POST",
            url: "api/adv/${advertisement.advertisementId}",
            dataType: 'json',
            data: JSON.stringify(data),
            success: function (response) {
                location.reload();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(errorThrown);
            }
        });
    });

    $("#imageUploader${advertisement.advertisementId}").on('change', prepareLoad);
    var files;
    function prepareLoad(event) {
        files = event.target.files;
        uploadAdvImage('${advertisement.advertisementId}');
    }


    $("#searchingSelect${advertisement.advertisementId}").select2(
            {
                ajax: {
                    url: "/api/goods",
                    dataType: 'json',
                    delay: 200,
                    data: function (params) {
                        return {
                            q: params.term,
                            limit: 5,
                            offset: (params.page || 0) * 5
                        };
                    },
                    processResults: function (data, params) {
                        params.page = params.page || 0;
                        return {
                            results: data,
                            pagination: {
                                more: (params.page * 5) < Object.keys(data).length
                            }
                        };
                    },
                    cache: true
                },
                escapeMarkup: function (markup) {
                    return markup;
                },
                minimumInputLength: 1,
                templateResult: function (option) {
                    return '<a onclick="setHref(\'/goods/' + option.productId + '\',\'${advertisement.advertisementId}\')" class="select2-result-repository clearfix">' +
                            '<div class="col-xs-2">' +
                            '<img src="' + option.smallImage + '" ' +
                            'style="max-width:36px;max-height:36px;">' +
                            '</div>' +
                            '<div class="col-xs-10">' +
                            '<div>' + option.title +
                            '</div>' +
                            '</div>' +
                            '</a>';
                },
                templateSelection: function (option) {
                    return option.title;
                }
            }
    );
    $(document).ready();
</script>