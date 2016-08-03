<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="aa-product-view-content">
    <div id="previewData">
        <div class="content min-height-255 no-padding-top">
            <div class="row form-group">
                <div class="col-md-3">
                    Name:
                </div>
                <div class="col-md-9">
                    ${activeUser.customerName}
                </div>
            </div>

            <div class="row form-group">
                <div class="col-md-3">
                    Email:
                </div>
                <div class="col-md-9">
                    ${activeUser.email}
                </div>
            </div>

            <div class="row form-group">
                <div class="col-md-3">
                    Phone number:
                </div>
                <div class="col-md-9">
                    ${activeUser.phone}
                </div>
            </div>

            <div class="row form-group">
                <div class="col-md-3">
                    Address:
                </div>
                <div class="col-md-9">
                    <c:if test="${activeUser.addressByAddressId !=null}">
                        <c:if test="${not empty address.country && address.country!=''}">
                            ${address.country},
                        </c:if>
                        <c:if test="${not empty address.region && address.region!=''}">
                            ${address.region} region,
                        </c:if>
                        <c:if test="${not empty address.city && address.city!=''}">
                            ${address.city},
                        </c:if>
                        <c:if test="${not empty address.street && address.street!=''}">
                            ${address.street} street
                        </c:if>
                        <c:if test="${not empty address.buildingNumber && address.buildingNumber!=''}">
                            #${address.buildingNumber}
                        </c:if>
                    </c:if>
                </div>
            </div>


        </div>
        <div class="row">
            <div class="col-md-6">
                <button type="submit" class="btn btn-default aa-review-submit"
                        onclick="tooglePartsVisibility('previewData','editableData')">Edit Profile
                </button>
            </div>
            <div class="col-md-6">
                <button class="btn btn-default aa-review-submit"
                        onclick="tooglePartsVisibility('previewData','changePassword')">Change
                    password
                </button>
            </div>
        </div>
    </div>


    <div id="editableData" class="aa-review-form hidden-form form">
        <form:form id="editProfileForm">
            <c:set var="address" value="${address}" scope="request"/>
            <jsp:include page="editUserInfoPart.jsp"/>
            <div class="row">
                <div class="col-md-6">
                    <button type="submit" class="btn btn-default aa-review-submit">Submit</button>
                </div>
                <div class="col-md-6">
                    <button type="reset" class="btn btn-default aa-review-submit"
                            onclick="tooglePartsVisibility('previewData','editableData')">Cancel
                    </button>
                </div>
            </div>
        </form:form>
    </div>

    <div id="changePassword" class="aa-review-form hidden-form form">
        <form:form id="changePasswordForm">
            <div class="content min-height-255 no-padding-top">
                <div class="bs-example hidden-block" id="changePasswordError">
                    <div class="alert alert-error">
                        <a class="close" onclick="$('#changePasswordError').addClass('hidden-block');">&times;</a>
                        <strong>Error!</strong> Password is not changed. Check your data and try again.
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-3">
                        Old password: <span>*</span>
                    </div>
                    <div class="col-md-9 no-padding-right">
                        <input required autocomplete="off" type="password" name="oldPassword" class="form-control"/>
                    </div>
                </div>

                <div class="row form-group">
                    <div class="col-md-3">
                        New password: <span>*</span>
                    </div>
                    <div class="col-md-9 no-padding-right">
                        <input required autocomplete="off" type="password" name="newPassword" class="form-control"/>
                    </div>
                </div>

                <div class="row form-group">
                    <div class="col-md-3">
                        Password confirmation: <span>*</span>
                    </div>
                    <div class="col-md-9 no-padding-right">
                        <input required autocomplete="off" type="password" name="confirmedPassword"
                               class="form-control"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <button type="submit" class="btn btn-default aa-review-submit">Submit</button>
                </div>
                <div class="col-md-6">
                    <button type="reset" class="btn btn-default aa-review-submit"
                            onclick="tooglePartsVisibility('previewData','changePassword')">Cancel
                    </button>
                </div>
            </div>
        </form:form>
    </div>
</div>

<script>
    $('#editProfileForm').submit(function (e) {
        var frm = $('#editProfileForm');
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
            url: "api/customers/${activeUser.customerId}",
            dataType: 'json',
            data: JSON.stringify(data),
            success: function (response) {
                location.reload();
            }
        });
    });

    $('#changePasswordForm').submit(function (e) {
        var frm = $('#changePasswordForm');
        e.preventDefault();
        var data = {};
        $.each(this, function (i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });
        $.ajax({
            type: "POST",
            url: "api/customers/${activeUser.customerId}/password",
            dataType: 'json',
            data: {
                oldPassword: data['oldPassword'],
                newPassword: data['newPassword'],
                confirmedPassword: data['confirmedPassword']
            },
            success: function (response) {
                if (response.password == "false") {
                    $('#changePasswordError').removeClass("hidden-block");
                }
                else {
                    document.getElementById("logoutForm").submit();
                }
            }
        });
    });
</script>