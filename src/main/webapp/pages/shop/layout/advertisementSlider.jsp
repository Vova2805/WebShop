<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${advertisements.size() > 0}">
    <section id="aa-slider">
        <div class="aa-slider-area">
            <div id="sequence" class="seq">
                <div class="seq-screen">
                    <ul class="seq-canvas">

                        <c:forEach items="${advertisements}" var="item">
                            <li>
                                <div class="seq-model">
                                    <img data-seq src="${item.images['huge']}"/>
                                </div>
                                <div class="seq-title">
                                    <h2 data-seq>${item.title}</h2>
                                    <p data-seq>${item.body}</p>
                                    <a data-seq href="${item.buttonHref}" class="aa-shop-now-btn aa-secondary-btn">SHOP
                                        NOW</a>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <fieldset class="seq-nav" aria-controls="sequence" aria-label="Slider buttons">
                    <a type="button" class="seq-prev" aria-label="Previous"><span class="fa fa-angle-left"></span></a>
                    <a type="button" class="seq-next" aria-label="Next"><span class="fa fa-angle-right"></span></a>
                </fieldset>
            </div>
        </div>
    </section>
</c:if>
