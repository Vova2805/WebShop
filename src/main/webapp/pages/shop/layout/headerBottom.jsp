<header id="aa-header">
    <div class="aa-header-bottom">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="aa-header-bottom-area">
                        <div class="aa-logo">
                            <a href="/">
                                <span class="fa fa-logo"></span>
                                <p>Web <strong>Shop</strong> <span>Everything you want</span></p>
                            </a>
                        </div>
                        <div class="aa-search-box">
                            <select class="searchingSelect">
                            </select>
                            <button><span class="fa fa-search"></span></button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>
<link rel="stylesheet" href="/resources/plugins/select2/select2.min.css">
<script src="/resources/plugins/select2/select2.full.min.js"></script>
<script>
    /**
     * Searching product using api and select2 plugin
     */
    $(".searchingSelect").select2(
            {
                ajax: {
                    url: "/api/goods",
                    dataType: 'json',
                    delay: 200,
                    data: function (params) {
                        return {
                            q: params.term,
                            limit: 5,
                            offset: (params.page || 0) * 5
                        };
                    },
                    processResults: function (data, params) {
                        params.page = params.page || 0;
                        return {
                            results: data,
                            pagination: {
                                more: (params.page * 5) < Object.keys(data).length
                            }
                        };
                    },
                    cache: true
                },
                escapeMarkup: function (markup) {
                    return markup;
                },
                minimumInputLength: 1,
                templateResult: function (option) {
                    return '<a href="/goods/' + option.productId + '" class="select2-result-repository clearfix">' +
                            '<div class="col-xs-2">' +
                            '<img src="' + option.smallImage + '" ' +
                            'style="max-width:36px;max-height:36px;">' +
                            '</div>' +
                            '<div class="col-xs-10">' +
                            '<div>' + option.title +
                            '</div>' +
                            '</div>' +
                            '</a>';
                },
                templateSelection: function (option) {
                    return option.title;
                }
            }
    );
</script>
