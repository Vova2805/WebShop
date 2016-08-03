$.ready(function () {

});
jQuery("#list-catg").click(function (e) {
    e.preventDefault(e);
    jQuery(".aa-product-catg").addClass("list");
});
jQuery("#grid-catg").click(function (e) {
    e.preventDefault(e);
    jQuery(".aa-product-catg").removeClass("list");
});


/**
 *
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

function submitUploadFileInput(inputFileId) {
    $("#" + inputFileId).click();
}

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

function formSubmit(id) {
    document.getElementById(id).click();
}


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

function changeAdminContentInnerBlock(type, id) {
    window.location.href = "/admin?block=full&type=" + type + "&id=" + id;
}

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

function toogleVisibility(elementId) {
    $("#" + elementId).toggleClass("hidden-form");
}

function tooglePartsVisibility(id1, id2) {
    toogleVisibility(id1);
    toogleVisibility(id2);
}
//region Product
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

function treeviewInit() {
    $(".sidebar a").on("click", function () {
        $(".sidebar a").removeClass("active-option");
        $(".sidebar a").removeClass("added-option");
        $(this).addClass("active-option");
    });
}

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

function clearPropertyContentBlock() {
    $('#propertyContentInner').html('');
}

//endregion

//region Orders

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

function openMenu(menuId) {
    $('#' + menuId + "2").click();
    setTimeout(function () {
        $('#' + menuId).click();
    }, 5000);
}

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

