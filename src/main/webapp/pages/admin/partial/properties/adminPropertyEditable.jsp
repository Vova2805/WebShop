<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script>
    $('#updatePropertyForm').submit(function (e) {
        var frm = $('#updatePropertyForm');
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
            url: "api/properties/${property.propertyId}",
            dataType: 'json',
            data: JSON.stringify(data),
            success: function (response) {
                document.getElementById('propertyItem${property.propertyId}').innerHTML = response.propertyValue;
//                location.reload();
            }
        });
    });
    $(".select2").select2();
</script>
<form:form id="updatePropertyForm" class="aa-review-form">
    <div class="content min-height-255 no-padding-top">
        <div class="row form-group">
            <div class="col-md-3">
                Property title:
            </div>
            <div class="col-md-9 no-padding-right form-group">
                <input type="text" name="propertyValue" class="form-control text-bold"
                       value="${property.propertyValue}"/>
            </div>
        </div>

        <div class="row form-group">
            <div class="col-md-3">
                Property class:
            </div>
            <div class="col-md-9 no-padding-right form-group">
                <select aria-hidden="true" tabindex="-1" name="propertyClassId"
                        class="form-control select2 select2-hidden-accessible"
                        style="width: 100%;">
                    <c:forEach var="propertyClass" items="${propertyClasses}">
                        <c:choose>
                            <c:when test="${propertyClass.propertyClassTitle.equals(property.propertyClassByPropertyClassId.propertyClassTitle)}">
                                <option value="${propertyClass.propertyClassId}"
                                        selected="selected">${propertyClass.propertyClassTitle}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${propertyClass.propertyClassId}">${propertyClass.propertyClassTitle}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="row form-group">
            <div class="col-md-6">
                <button type="submit" class="btn btn-default aa-review-submit">Submit
                </button>
            </div>
            <div class="col-md-6 no-padding-right">
                <button type="reset" class="btn btn-default aa-review-submit">
                    Cancel
                </button>
            </div>
        </div>
    </div>
</form:form>