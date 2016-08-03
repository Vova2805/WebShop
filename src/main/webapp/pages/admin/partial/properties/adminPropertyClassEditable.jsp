<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script>
    $('#updatePropertyClassForm').submit(function (e) {
        var frm = $('#updatePropertyClassForm');
        e.preventDefault();
        var data = {};
        $.each(this, function (i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });
        $.ajax({
            contentType: 'application/json; charset=utf-8',
            type: "PUT",
            url: "api/propertyClasses/${propertyClass.propertyClassId}",
            dataType: 'json',
            data: JSON.stringify(data),
            success: function (response) {
                document.getElementById('propertyClassItem${propertyClass.propertyClassId}').innerHTML = response.propertyClassTitle;
            }
        });
    });
</script>
<div class="aa-review-form">
    <div class="content min-height-255 no-padding-top">
        <form:form commandName="propertyClass" id="updatePropertyClassForm">
            <div class="row form-group">
                <div class="col-md-3">
                    Property class title:
                </div>
                <div class="col-md-9 no-padding-right form-group">
                    <input name="propertyClassTitle" id="propertyClassTitle" type="text" class="form-control text-bold"
                           value="${propertyClass.propertyClassTitle}" required="true"/></p>
                </div>
            </div>

            <div class="row form-group">
                <div class="col-md-3">
                    Class description:
                </div>
                <div class="col-md-9 no-padding-right form-group">
                    <textarea name="propertyClassDescr" id="propertyClassDescr" class="form-control max-width-100p"
                              rows="3">${propertyClass.propertyClassDescr}</textarea>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-4">
                    <button type="submit" class="btn btn-default aa-review-submit">Submit
                    </button>
                </div>
                <div class="col-md-4">
                    <button class="btn btn-default aa-review-submit"
                            onclick="deletePropertyClass(${propertyClass.propertyClassId});">
                        Remove
                    </button>
                </div>
                <div class="col-md-4 no-padding-right">
                    <button class="btn btn-default aa-review-submit"
                            onclick="addTreeViewItem(${propertyClass.propertyClassId},'admin/properties','propertyClassRootUl')">
                        Add property
                    </button>
                </div>
            </div>
        </form:form>


        <div class="row form-group">
            <div class="box box-widget form-group">
                <div class="box-header with-border">
                    <div class="user-block">
                        <span class="username"><a class="cursor-hand">Properties review</a></span>
                    </div>
                    <div class="box-tools">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                                class="fa fa-minus"></i>
                        </button>
                        <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i>
                        </button>
                    </div>
                </div>

                <div class="box-body">
                    <c:forEach items="${propertyClass.propertiesByPropertyClassId}" var="property">
                        <div class="box-body">
                            <strong>${property.propertyValue}</strong>

                                                    <span class="remove-fade pull-right-container margin-5 fa-container remove cursor-hand"
                                                          onclick="deleteProperty(${propertyClass.propertyClassId},${property.propertyId})">
                                                        <i class="fa fa-remove pull-right" aria-hidden="true"></i>
                                                        <i class="fa fa-times-circle pull-right i-danger"
                                                           aria-hidden="true"></i>
                                                    </span>

                                                    <span class="remove-fade pull-right-container success margin-5 fa-container edit cursor-hand"
                                                          onclick="changeAdminPropertiesInnerBlock('property',${property.propertyId})">
                                                        <i class="fa fa-pencil-square-o pull-right"
                                                           aria-hidden="true"></i>
                                                         <i class="fa fa-pencil-square pull-right i-success"
                                                            aria-hidden="true"></i>
                                                    </span>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

    </div>
</div>
