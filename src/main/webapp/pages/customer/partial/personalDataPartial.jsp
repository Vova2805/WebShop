<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    $(document).ready();
</script>

<section class="content-header">
    <h1>
        Personal data
    </h1>
</section>

<section class="content">
    <div class="aa-product-details-area">
        <div class="aa-product-details-content" style="width: 100%">
            <div class="row">
                <div class="col-md-4">
                    <div class="aa-product-view-slider">
                        <div id="product-view-slider" class="simpleLens-gallery-container faded-buttons-area">
                            <div class="simpleLens-container">
                                <div class="simpleLens-big-image-container faded-buttons-area">
                                    <a class="simpleLens-lens-image">
                                        <img src="${activeUser.images['standart_128']}"
                                             id="customerImg${activeUser.customerId}"
                                             class="simpleLens-big-image customer-big-image">
                                    </a>
                                    <div class="row-fluid form-group hidden-form">
                                        <input type="file" name="file" id="imageUploader${activeUser.customerId}"/>
                                    </div>
                                    <a class="aa-add-card-btn cursor-hand"
                                       onclick="submitUploadFileInput('imageUploader${activeUser.customerId}')">
                                        <span class="fa fa-image"> </span>Change
                                        image</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-8">
                    <c:set var="activeUser" value="${activeUser}" scope="request"/>
                    <c:set var="address" value="${address}" scope="request"/>
                    <jsp:include page="personalDataEditablePartPartial.jsp"/>
                </div>
            </div>
        </div>
    </div>
</section>

<script>
    /**
     * For profile image uploading. Fired when user choose his image
     */
    $("#imageUploader${activeUser.customerId}").on('change', prepareLoad);
    var files;
    function prepareLoad(event) {
        files = event.target.files;
        uploadProfileImage('${activeUser.customerId}');
    }
</script>



