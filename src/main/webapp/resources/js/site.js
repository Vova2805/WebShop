//region General methods

/**
 * Change products view to list view
 */
jQuery("#list-catg").click(function (e) {
    e.preventDefault(e);
    jQuery(".aa-product-catg").addClass("list");
});
jQuery("#grid-catg").click(function (e) {
    e.preventDefault(e);
    jQuery(".aa-product-catg").removeClass("list");
});


/**
 * Return first parrent node with such tagname
 * @param node
 * @param tagname
 * @returns {*|Node}
 */
function getParentByTagName(node, tagname) {
    var parent;
    if (node === null || tagname === '') return;
    parent = node.parentNode;
    tagname = tagname.toUpperCase();

    while (parent.tagName !== "HTML") {
        if (parent.tagName === tagname) {
            return parent;
        }
        parent = parent.parentNode;
    }
    return parent;
}

/**
 * Using for submitting file uploading
 * @param inputFileId
 */
function submitUploadFileInput(inputFileId) {
    $("#" + inputFileId).click();
}

/**
 * Uploading image for some item (category,sub,group)
 * @param type
 * @param objectId
 */
function uploadItemImage(type, objectId) {
    var oMyForm = new FormData();
    oMyForm.append("file", files[0]);
    $.ajax({
        url: "api/" + type + "/" + objectId + "/image",
        data: oMyForm,
        type: "POST",
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        dataType: "json",
        success: function (response) {
            var d = new Date();
            var src = response.images['standart_128'] + "?time=" + d.getTime();
            $('#itemImage').prop('src', src + '?' + Math.random());
        }
    });
}
//endregion

//region Admin
/**
 *
 * @param blockTitle
 */
function changeContent(blockTitle) {
    $.ajax({
        type: "GET",
        url: "login/changeBlock",
        data: {
            block: blockTitle
        },
        success: function (response) {
            $("#dinamicLoginArea").html(response);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(errorThrown);
        }
    });
}
/**
 * Changing inner content by reloading page
 * @param type
 * @param id
 */
function changeAdminContentInnerBlock(type, id) {
    window.location.href = "/admin?block=full&type=" + type + "&id=" + id;
}

/**
 * AJAX changing content
 * @param type
 * @param id
 */
function changeAdminPropertiesInnerBlock(type, id) {
    $.ajax({
        type: "GET",
        url: "admin/properties/" + type + "/" + id + "",
        success: function (response) {
            $("#propertyContentInner").html(response);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(errorThrown);
        }
    });
}

/**
 * Hide/Show element with elementId identifier
 * @param elementId
 */
function toogleVisibility(elementId) {
    $("#" + elementId).toggleClass("hidden-form");
}

/**
 * Toggle visibility for pair
 * @param id1
 * @param id2
 */
function tooglePartsVisibility(id1, id2) {
    toogleVisibility(id1);
    toogleVisibility(id2);
}

//region Product

/**
 * Selecting smal image and refreshing large
 * @param productId
 * @param imageId
 */
function selectImage(productId, imageId) {
    $.ajax({
        type: "GET",
        url: "api/goods/" + productId + "/images/" + imageId + "",
        dataType: "json",
        success: function (response) {
            document.getElementById("largeImageIMG").src = response.images['medium'];
            document.getElementById("largeImageBTN").onclick = function () {
                deleteImage(productId, imageId);
            };
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(errorThrown);
        }
    });
}

/**
 * AJAX deleting image with ui changing
 * @param productId
 * @param imageId
 */
function deleteImage(productId, imageId) {
    $.ajax({
        type: "DELETE",
        url: "api/goods/" + productId + "/images/" + imageId,
        dataType: "text",
        success: function (response) {
            $.ajax({
                type: "GET",
                url: "admin/goods/" + productId + "/images",
                dataType: "html",
                success: function (response) {
                    document.getElementById('previewContainer').innerHTML = response;
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(errorThrown);
                }
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(errorThrown);
        }
    });
}

/**
 * AJAX append html of new description
 * @param productId
 * @param containerId
 */
function addDescription(productId, containerId) {
    $.ajax({
        type: "GET",
        url: "admin/goods/" + productId + "/descriptions",
        success: function (response) {
            $("#" + containerId).append(response);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(errorThrown);
        }
    });
}

/**
 * Removing description using AJAX and admin controller
 * @param containerId
 * @param productId
 * @param descriptionId
 */
function removeDescription(containerId, productId, descriptionId) {
    $.ajax({
        type: "GET",
        url: "admin/goods/" + productId + "/descriptions/" + descriptionId + "/delete",
        success: function (response) {
            $("#" + containerId).html(response);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(errorThrown);
        }
    });
}

/**
 * Asynchronous uploading image
 * @param productId
 * @param descriptionId
 */
function uploadDescriptionImage(productId, descriptionId) {
    var oMyForm = new FormData();
    oMyForm.append("file", files[0]);
    $.ajax({
        url: "api/goods/" + productId + "/descriptions/" + descriptionId + "/image",
        data: oMyForm,
        type: "POST",
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        dataType: "json",
        success: function (response) {
            var d = new Date();
            var src = response.imageSrc + "?time=" + d.getTime();
            $("#descriptionImg" + descriptionId + "preview").prop('src', src + '?' + Math.random());
            $("#descriptionImg" + descriptionId + "editable").prop('src', src + '?' + Math.random());
            $("#descrEditable" + descriptionId + "Img").removeClass("hidden-block");
            $("#descrPreview" + descriptionId + "Img").removeClass("hidden-block");
            $("#removeDescrImg" + descriptionId).removeClass("hidden-block");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(errorThrown);
        }
    });
}

/**
 * Removing image Asynchronously
 * @param productId
 * @param descriptionId
 */
function removeDescriptionImage(productId, descriptionId) {
    $.ajax({
        url: "api/goods/" + productId + "/descriptions/" + descriptionId + "/image",
        type: "DELETE",
        enctype: 'multipart/form-data',
        dataType: "json",
        success: function (response) {
            $("#descrEditable" + descriptionId + "Img").addClass("hidden-block");
            $("#descrPreview" + descriptionId + "Img").addClass("hidden-block");
            $("#removeDescrImg" + descriptionId).addClass("hidden-block");
            $("#descriptionImg" + descriptionId + "preview").prop('src', "");
            $("#descriptionImg" + descriptionId + "editable").prop('src', "");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(errorThrown);
        }
    });
}

/**
 * Generic function for inserting subitem (treeview)
 * @param parrentId
 * @param requestPath
 * @param containerId
 */
function addTreeViewItem(parrentId, requestPath, containerId) {
    $.ajax({
        type: "POST",
        url: requestPath,
        dataType: "html",
        data: {parrentId: parrentId},
        success: function (response) {
            document.getElementById(containerId + parrentId).innerHTML += response;
            $("#" + containerId + "Exp" + parrentId).removeClass("hidden-block");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(errorThrown);
            location.reload();
        }
    });
}

/**
 * Generic function for deleting subitem
 * @param parrentId
 * @param itemId
 * @param requestPath
 * @param itemContainerId
 * @param containerId
 */
function deleteTreeViewItem(parrentId, itemId, requestPath, itemContainerId, containerId) {
    $.ajax({
        type: "DELETE",
        url: requestPath + itemId,
        dataType: "html",
        success: function (response) {
            window.location.href = "/admin?block=full";
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(errorThrown);
            location.reload();
        }
    });
}

//endregion

//region Categories

/**
 * Init tree view
 */
function treeviewInit() {
    $(".sidebar a").on("click", function () {
        $(".sidebar a").removeClass("active-option");
        $(".sidebar a").removeClass("added-option");
        $(this).addClass("active-option");
    });
}

//reloading page
function updateContentTreeView() {
    location.reload();
}

/**
 * Change content fo subcategory dropdown if category is changed
 */

function categorySelection(categorySelectId, subcategorySelectId, groupSelectId) {
    var selectedId = $('#' + categorySelectId + ' :selected').val();
    $('#' + subcategorySelectId).html("");
    $('#' + groupSelectId).html("");
    $.ajax({
        type: "GET",
        url: "api/subcategories",
        data: {categoryId: selectedId},
        success: function (response) {
            var jsonObject = jQuery.parseJSON(response);

            var output = "";
            for (var i in jsonObject) {
                if (i == 0) {
                    output += "<option selected='selected'' value='" + jsonObject[i].id + "'>" + jsonObject[i].title + "</option>\n";
                }
                else {
                    output += "<option value='" + jsonObject[i].id + "'>" + jsonObject[i].title + "</option>\n";
                }
            }
            $('#' + subcategorySelectId).html(output);
            subcategorySelection(subcategorySelectId, groupSelectId);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(errorThrown);
        },
        dataType: "text"
    });
}

/**
 * Change content of group dropdown if subcategory is changed
 */
function subcategorySelection(subcategorySelectId, groupSelectId) {
    var selected = $('#' + subcategorySelectId + ' :selected').val();
    $('#' + groupSelectId).html("");
    $.ajax({
        type: "GET",
        url: "api/groups",
        data: {subcategoryId: selected},
        success: function (response) {
            var jsonObject = jQuery.parseJSON(response);
            var output = "";
            for (var i in jsonObject) {
                if (i == 0) {
                    output += "<option selected='selected'' value='" + jsonObject[i].id + "'>" + jsonObject[i].title + "</option>\n";
                }
                else {
                    output += "<option value='" + jsonObject[i].id + "'>" + jsonObject[i].title + "</option>\n";
                }
            }
            $('#' + groupSelectId).html(output);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(errorThrown);
        },
        dataType: "text"
    });
}

/**
 * Adding category Asynchronously
 */
function addCategory() {
    $.ajax({
        type: "POST",
        url: "admin/full/categories",
        dataType: "html",
        success: function (response) {
            location.reload();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(errorThrown);
            location.reload();
        }
    });
}

/**
 * REmoving category
 * @param categoryId
 */
function removeCategory(categoryId) {
    $.ajax({
        type: "DELETE",
        url: "admin/full/categories/" + categoryId + "",
        dataType: "html",
        success: function (response) {
            window.location.href = "/admin?block=full";
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(errorThrown);
            location.reload();
        }
    });
}

//endregion

//region Properties

function addPropertyClass() {
    $.ajax({
        type: "POST",
        url: "admin/properties/propertyClasses",
        contentType: 'html',
        success: function (response) {
            document.getElementById('propertyClassRootUl').innerHTML += response;
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(errorThrown);
            location.reload();
        }
    });
}

function deletePropertyClass(propertyClassId) {
    $.ajax({
        type: "GET",
        url: "api/propertyClasses/" + propertyClassId + "/delete",
        dataType: "text",
        success: function (response) {
            var jsonObject = jQuery.parseJSON(response);
            var treeViewItem = document.getElementById('propertyClassLi' + jsonObject.id);
            document.getElementById('propertyClassRootUl').removeChild(treeViewItem);
            $(document).ready();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(errorThrown);
            location.reload();
        }
    });

}

function deleteProperty(propertyClassId, propertyId) {
    $.ajax({
        type: "GET",
        url: "api/properties/" + propertyId + "/delete",
        dataType: "text",
        success: function (response) {
            var jsonObject = jQuery.parseJSON(response);
            var treeViewItem = document.getElementById('propertyLi' + jsonObject.id);
            document.getElementById('propertyClassRootUl' + propertyClassId).removeChild(treeViewItem);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(errorThrown);
            location.reload();
        }
    });
}

function updatePropertiesTreeView() {
    $.ajax({
        type: "GET",
        url: "admin/properties",
        dataType: "html",
        data: {view: "adminPropertiesTreeview"},
        success: function (response) {
            document.getElementById("propertyTreeview").innerHTML = response;
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(errorThrown);
            location.reload();
        }
    });
}

/**
 * Clear block with editing property details
 */
function clearPropertyContentBlock() {
    $('#propertyContentInner').html('');
}

//endregion

//region Orders

/**
 * Remove order and change view
 * @param orderId
 */
function removeOrder(orderId) {
    $.ajax({
        type: "GET",
        url: "api/orders/" + orderId + "/delete",
        dataType: "html",
        success: function (response) {
            location.reload();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(errorThrown);
            location.reload();
        }
    });
}
//endregion

//region Users

/**
 * Lock/Unlock user
 * @param userId
 */
function toggleUserLock(userId) {
    $.ajax({
        type: "PUT",
        url: "admin/users/" + userId + "/toggleEnabled",
        dataType: "html",
        success: function (response) {
            if (response == 'true' || response == true) {
                document.getElementById('lockButton' + userId).innerHTML = 'Lock ' + '<i class="fa fa-lock"></i>';
                $("#lockButtonIcon" + userId).addClass("hidden-block");
                $('#profView' + userId).removeClass("locked");
            }
            else {
                document.getElementById('lockButton' + userId).innerHTML = 'Unlock ' + '<i class="fa fa-unlock"></i>';
                $('#profView' + userId).addClass("locked");
            }
            $(document).ready();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(errorThrown);
        }
    });
}
//endregion
//endregion

// region Customer

/**
 * Main function for uploading user image
 * @param customerId
 */
function uploadProfileImage(customerId) {
    var oMyForm = new FormData();
    oMyForm.append("file", files[0]);
    $.ajax({
        url: "api/customers/" + customerId + "/image",
        data: oMyForm,
        type: "POST",
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        dataType: "json",
        success: function (response) {
            var d = new Date();
            var src = response.images['standart_128'] + "?time=" + d.getTime();
            $('#customerImg' + customerId).prop('src', src + '?' + Math.random());
            location.reload();
        }
    });
}

//endregion

//region Header

/**
 * Open header user menu after submitting (for example after adding to cart Menu my cart is sliding down)
 * @param menuId
 */
function openMenu(menuId) {
    $('#' + menuId + "2").click();
    setTimeout(function () {
        $('#' + menuId).click();
    }, 5000);
}

/**
 * Updating menu content
 * @param openMenuId
 */
function updateUserMenuItems(openMenuId) {
    $.ajax({
        type: "GET",
        url: "webshop/updateMenu",
        dataType: "html",
        success: function (response) {
            document.getElementById('headerOptionsContainer').innerHTML = response;
            openMenu(openMenuId);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(errorThrown);
        }
    });
}
//endregion

//region Orders

/**
 * Call to controller and changing order status by AJAX
 * @param status
 * @param orderId
 */
function changeOrderStatus(status, orderId) {
    $.ajax({
        type: "POST",
        url: 'api/orders/' + orderId + '',
        contentType: 'application/json; charset=utf-8',
        dataType: "json",
        data: JSON.stringify({productOrderStatus: status}),
        success: function (response) {
            location.reload();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(errorThrown);
        }
    });
}

//endregion

//region Advertisement

/**
 * Common actions with advertisements
 * @constructor
 */
function AddNewAdvertisement() {
    $.ajax({
        type: "POST",
        url: "api/adv",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({title: "New advertisement", body: "Advertisement body"}),
        success: function (response) {
            $.ajax({
                type: "GET",
                url: "admin/adv/" + response.advertisementId,
                dataType: "html",
                success: function (response) {
                    document.getElementById('advertisementContainer').innerHTML += response;
                    location.reload();
                },
                error: function (xhr, ajaxOptions, thrownError) {
                    location.reload();
                }
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(errorThrown);
        }
    });
}

/**
 * Deleting advertisement
 * @param advertId
 */
function deleteAdvertisement(advertId) {
    $.ajax({
        type: "DELETE",
        url: "api/adv/" + advertId,
        dataType: "json",
        success: function (response) {
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(errorThrown);
        }
    });
    location.reload();
}

/**
 * Upload image Asynchronously
 * @param objectId
 */
function uploadAdvImage(objectId) {
    var oMyForm = new FormData();
    oMyForm.append("file", files[0]);
    $.ajax({
        url: "api/adv/" + objectId + "/image",
        data: oMyForm,
        type: "POST",
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        dataType: "json",
        success: function (response) {
            var d = new Date();
            var src = response.images['huge'] + "?time=" + d.getTime();
            $('#advertImg' + objectId).prop('src', src + '?' + Math.random());
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(errorThrown);
        }
    });
}

/**
 * Setting href to product
 * @param href
 * @param advId
 */
function setHref(href, advId) {
    $.ajax({
        contentType: 'application/json; charset=utf-8',
        type: "POST",
        url: "api/adv/" + advId,
        dataType: 'json',
        data: JSON.stringify({buttonHref: href}),
        success: function (response) {
            document.getElementById('ref' + advId).innerHTML = '<a  href="' + href + '"> ' + href + '</a>';
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(errorThrown);
        }
    });
}

//endregion

//region Cart

/**
 * Live total changing
 * @param cartId
 * @param amount
 */
function changeCartQuantity(cartId, amount) {
    $.ajax({
        type: "POST",
        url: 'api/cart/' + cartId,
        dataType: "json",
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify({amount: amount}),
        success: function (response) {
            $.ajax({
                type: "GET",
                url: 'api/cart/total',
                dataType: "json",
                success: function (response) {
                    $("#total").text(response.total);
                },
                error: function (code) {
                    console.log(code);
                }
            });
        },
        error: function (code) {
            console.log(code);
        }
    });
}

/**
 * Removing cart item
 * @param cartId
 */
function removeCartItem(cartId) {
    $.ajax({
        type: "DELETE",
        url: 'api/cart/' + cartId,
        dataType: "json",
        success: function (response) {
            location.reload();
        },
        error: function (code) {
            console.log(code);
        }
    });
}

/**
 * Adding new cart item
 * @param productId
 * @param amount
 */
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

//endregion

//region View Content

/**
 * Reloading page with new request
 * @param block
 */
function changeAdminContent(block) {
    window.location.href = "/admin?block=" + block;
}

/**
 *Changing modal view content before it is faded in
 * @param href
 * @param goodId
 */
function changeViewModalContent(href, goodId) {
    $.ajax({
        type: "GET",
        url: href,
        data: {
            id: goodId
        },
        success: function (response) {
            $("#productDetailsModalPart").html(response);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(errorThrown);
            location.reload();
        }
    });
}
//endregion



