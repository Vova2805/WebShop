<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<a class="simpleLens-thumbnail-wrapper cursor-hand"
   onclick="selectImage('${image.productId}','${image.imageId}')">
    <div class="small-simpleLens background-img"
         style="background-image: url(${image.images['small']});"></div>
</a>

