<link rel="stylesheet" href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>

<div class="aa-sidebar-price-range">
    <div>
        <div class="row-fluid form-group">
            <div id="slider-range"></div>
        </div>
        <div class="row form-group">
            <div class="col-md-6">
                <input id="priceFrom" min="0" step="1" name="priceFrom" value="0"
                       class="form-control"
                       type="number">
            </div>
            <div class="col-md-6">
                <input id="priceTo" min="0" step="1" name="priceTo" value="${priceTo}"
                       class="form-control"
                       type="number">
            </div>
        </div>
        <div class="row form-group">
            <button type="button" class="aa-filter-btn" onclick="submitForm()">Filter</button>
        </div>
    </div>
</div>
<script>
    /**
     * Initializing price slider
     */
    $(function () {
        $("#slider-range").slider({
            range: true,
            min: 0,
            max: ${priceTo},
            values: [0, ${priceTo}],
            slide: function (event, ui) {
                $("#priceFrom").val(ui.values[0]);
                $("#priceTo").val(ui.values[1]);
            }
        });
        $("#priceFrom").val($("#slider-range").slider("values", 0));
        $("#priceTo").val($("#slider-range").slider("values", 1));

        $("#priceFrom").bind('keyup mouseup change input', function () {
            var min = $("#slider-range").slider("option", "min");
            var secondValue = $("#slider-range").slider('values', 1);
            var value = $("#priceFrom").val();
            if (value < min) {
                value = min;
            }
            else if (value > secondValue) {
                value = secondValue;
            }
            else {
                $("#slider-range").slider('values', 0, value);
            }
            $("#priceFrom").val(value);
        });
        $("#priceTo").bind('keyup mouseup change input', function () {
            $("#slider-range").slider('values', 1, $("#priceTo").val());

            var max = $("#slider-range").slider("option", "max");
            var firstValue = $("#slider-range").slider('values', 0);
            var value = $("#priceTo").val();
            if (value > max) {
                value = max;
            }
            else if (value < firstValue) {
                value = firstValue;
            }
            else {
                $("#slider-range").slider('values', 1, value);
            }
            $("#priceTo").val(value);
        });
    });
</script>
