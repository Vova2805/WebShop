<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="accordion${entry.key}" role="tablist" aria-multiselectable="true">
    <div class="aa-sidebar-widget padding-left-15">
        <div class="panel-heading no-padding-right-left" role="tab" id="header${entry.key}">
            <h3 class="panel-title">
                <a>
                    ${entry.value.propertyClassTitle}
                </a>
            </h3>
        </div>
        <ul class="aa-catg-nav">
            <c:set var="relatedProperties"
                   value="${entry.value.propertiesByPropertyClassId}"/>
            <div id="collapsible${entry.key}" class="panel-collapse collapse in"
                 role="tabpanel"
                 aria-labelledby="header${entry.key}">
                <c:forEach var="relatedProperty" items="${relatedProperties}">
                    <c:choose>
                        <c:when test="${relatedProperty.active}">
                            <c:set var="checked" value=""/>
                            <c:if test="${relatedProperty.checked}">
                                <c:set var="checked" value="checked='checked'"/>
                            </c:if>
                            <li><a>
                                <input name="propertiesIds" onclick="submitForm()" class="propertyId" type="checkbox"
                                       value="${relatedProperty.propertyId}"
                                       id="property${relatedProperty.propertyId}" ${checked}/>
                                <label for="property${relatedProperty.propertyId}"> ${relatedProperty.propertyValue}</label></a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li><a>
                                <input name="propertiesIds" onclick="submitForm()" class="propertyId" type="checkbox"
                                       value="${relatedProperty.propertyId}" id="property${relatedProperty.propertyId}"
                                       disabled/>
                                <label for="property${relatedProperty.propertyId}"> ${relatedProperty.propertyValue}</label></a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>

        </ul>
        <div class="panel-heading no-padding-right-left" role="tab">
            <h3 class="panel-title no-border">
                <a class="collapsed" data-toggle="collapse"
                   data-parent="#accordion${entry.key}"
                   id="toggleLable${entry.key}"
                   href="#collapsible${entry.key}" aria-expanded="false"
                   aria-controls="collapsible${entry.key}">
                    Toggle visibility
                </a>
            </h3>
        </div>
    </div>
</div>
