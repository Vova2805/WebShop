<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    $(document).ready();
</script>

<section class="content-header">
    <h1>
        My comments
    </h1>
</section>

<section class="content">
    <div class="row">
        <div class="col-md-12">
            <c:choose>
                <c:when test="${empty activeUser.commentsByCustomerId}">
                    <c:set var="mainHeader" value="You did not comment anything" scope="request"/>
                    <c:set var="body" value="Leave your comments and they will be displayed here" scope="request"/>
                    <jsp:include page="emptyListMessagePartial.jsp"/>
                </c:when>
                <c:otherwise>
                    <div>
                        <div class="time-label margin-bottom">
                        <span class="bg-green">
                             3 Jan. 2014
                        </span>
                        </div>
                        <div class="timeline-item no-margin">
                            <span class="time"><i class="fa fa-clock-o"></i> 12:05</span>
                            <div class="row">
                                <div class="col-md-1">
                                    <figure class="margin">
                                        <a href="http://rozetka.com.ua/prestigio_multiphone_5453_duo_black/p1319897/">
                                            <img src="https://i1.rozetka.com.ua/goods/149998/prestigio_multiphone_5453_duo_black_images_149998437.jpg"
                                                 alt="Prestigio MultiPhone 5453 Duo Black"
                                                 title="Prestigio MultiPhone 5453 Duo Black"
                                                 style="border:none"
                                                 height="110"
                                                 width="55">
                                        </a>
                                    </figure>
                                </div>
                                <div class="col-md-10">
                                    <h3 class="timeline-header"><a href="#">Prestigio MultiPhone 5453 Duo Black</a></h3>

                                    <div class="timeline-body">
                                        Etsy doostang zoodles disqus groupon greplin oooj voxy zoodles,
                                        weebly ning heekya handango imeem plugg dopplr jibjab, movity
                                        jajah plickers sifteo edmodo ifttt zimbra. Babblely odeo kaboodle
                                        quora plaxo ideeli hulu weebly balihoo...
                                    </div>
                                    <div class="timeline-footer">
                                        <a class="btn btn-primary btn-xs">To source</a>
                                        <a class="btn btn-danger btn-xs">Delete</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</section>



