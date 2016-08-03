<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<li class="treeview active" id="propertyClassLi${loopPropertyClass.propertyClassId}">
    <a class="cursor-hand">
        <i class="fa fa-tag"></i>
                            <span onclick="changeAdminPropertiesInnerBlock('class',${loopPropertyClass.propertyClassId})"
                                  class="span-overflow"
                                  id="propertyClassItem${loopPropertyClass.propertyClassId}">${loopPropertyClass.propertyClassTitle}</span>
                                            <span class="pull-right-container success margin-top-5 fa-container edit"
                                                  data-toggle2="tooltip" data-placement="right"
                                                  onclick="changeAdminPropertiesInnerBlock('class',${loopPropertyClass.propertyClassId})"
                                                  title="Edit property class">
                                                            <i class="fa fa-pencil-square-o pull-right"
                                                               aria-hidden="true"></i>
                                                            <i class="fa fa-pencil-square pull-right i-success"
                                                               aria-hidden="true"></i>
                                            </span>
                                            <span class="pull-right-container success margin-top-5 fa-container plus"
                                                  data-toggle2="tooltip" data-placement="right"
                                                  onclick="addTreeViewItem(${loopPropertyClass.propertyClassId},'admin/properties','propertyClassRootUl')"
                                                  title="Add property">
                                            <i class="fa fa-plus pull-right" aria-hidden="true"></i>
                                            <i class="fa fa-plus-circle pull-right i-success" aria-hidden="true"></i>
                                            </span>

                                            <span class="pull-right-container danger margin-top-5 fa-container remove"
                                                  data-toggle2="tooltip" data-placement="right"
                                                  onclick="deletePropertyClass(${loopPropertyClass.propertyClassId})"
                                                  title="Remove property class">
                                            <i class="fa fa-remove pull-right" aria-hidden="true"></i>
                                            <i class="fa fa-times-circle pull-right i-danger" aria-hidden="true"></i>
                                            </span>
        <c:set var="expandeHidden" value="hidden-block"/>
        <c:if test="${loopPropertyClass.propertiesByPropertyClassId.size()>0}">
            <c:set var="expandeHidden" value=""/>
        </c:if>
                                            <span id="propertyClassRootUlExp${loopPropertyClass.propertyClassId}"
                                                  class="pull-right-container margin-top-5 fa-container collapsable ${expandeHidden}"
                                                  onclick="var $this = this;var liParrent = getParentByTagName($this, 'li');$(liParrent).toggleClass('active');">
                                            <i class="fa fa-angle-left pull-right" aria-hidden="true"></i>
                                            <i class="fa fa-chevron-circle-left pull-right margin-right-5"
                                               aria-hidden="true"></i>
                                            </span>

    </a>
    <ul class="treeview-menu menu-open" id="propertyClassRootUl${loopPropertyClass.propertyClassId}">
        <c:forEach items="${loopPropertyClass.propertiesByPropertyClassId}" var="property">
            <c:set var="loopProperty" value="${property}" scope="request"/>
            <c:set var="loopPropertyClass" value="${loopPropertyClass}" scope="request"/>
            <jsp:include page="propertyTreeViewItem.jsp"/>
        </c:forEach>
    </ul>
</li>