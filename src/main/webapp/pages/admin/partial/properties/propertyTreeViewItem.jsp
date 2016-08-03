<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<li class="treeview active" id="propertyLi${loopProperty.propertyId}">
    <a class="cursor-hand" onclick="changeAdminPropertiesInnerBlock('property',${loopProperty.propertyId})">
        <i class="fa fa-folder"></i>
        <span class="span-overflow" id="propertyItem${loopProperty.propertyId}">${loopProperty.propertyValue}</span>
                                            <span class="pull-right-container success margin-top-5 fa-container plus"
                                                  data-toggle2="tooltip" data-placement="right"
                                                  onclick="changeAdminPropertiesInnerBlock('property',${loopProperty.propertyId})"
                                                  title="Edit property">
                                            <i class="fa fa-pencil-square-o pull-right"
                                               aria-hidden="true"></i>
                                            <i class="fa fa-pencil-square pull-right i-success"
                                               aria-hidden="true"></i>
                                            </span>

                                            <span class="pull-right-container danger margin-top-5 fa-container remove"
                                                  data-toggle2="tooltip" data-placement="right"
                                                  onclick="deleteProperty(${loopPropertyClass.propertyClassId},${loopProperty.propertyId})"
                                                  title="Remove property">
                                            <i class="fa fa-remove pull-right" aria-hidden="true"></i>
                                            <i class="fa fa-times-circle pull-right i-danger"
                                               aria-hidden="true"></i>
                                            </span>
    </a>
</li>