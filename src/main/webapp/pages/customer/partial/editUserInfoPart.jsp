<div class="content min-height-255 no-padding-top">
    <div class="row form-group">
        <div class="col-md-3">
            Name: <span>*</span>
        </div>
        <div class="col-md-9 no-padding-right">
            <div class="input-group">
                <div class="input-group-addon">
                    <i class="fa fa-user"></i>
                </div>
                <input required type="text" name="customerName" class="form-control"
                       value="${activeUser.customerName}"/>
            </div>
        </div>
    </div>

    <div class="row form-group">
        <div class="col-md-3">
            Email: <span>*</span>
        </div>
        <div class="col-md-9 no-padding-right">
            <div class="input-group">
                <div class="input-group-addon">
                    <i class="fa fa-envelope"></i>
                </div>
                <input autocomplete="on" required type="email" name="email" class="form-control"
                       value="${activeUser.email}"/>
            </div>
        </div>
    </div>

    <div class="row form-group">
        <div class="col-md-3">
            Phone number: <span>*</span>
        </div>
        <div class="col-md-9 no-padding-right">
            <div class="input-group">
                <div class="input-group-addon">
                    <i class="fa fa-phone"></i>
                </div>
                <input autocomplete="on" required class="form-control" name="phone"
                       data-inputmask="&quot;mask&quot;: &quot;(999) 999-9999&quot;"
                       data-mask="" type="text" value="${activeUser.phone}">
            </div>
        </div>
    </div>

    <div class="row form-group">
        <div class="col-md-3">
            Country: <span>*</span>
        </div>
        <div class="col-md-9 no-padding-right">
            <div class="input-group">
                <div class="input-group-addon">
                    <i class="fa fa-globe"></i>
                </div>
                <div>
                    <input required autocomplete="on" name="country" class="form-control col-md-10"
                           value="${address.country}"
                           type="text">
                </div>
            </div>
        </div>
    </div>

    <div class="row form-group">
        <div class="col-md-3">
            Region:
        </div>
        <div class="col-md-9 no-padding-right">
            <div class="input-group">
                <div class="input-group-addon">
                    <i class="fa fa-arrows-alt"></i>
                </div>
                <div>
                    <input autocomplete="on" name="region" class="form-control col-md-10"
                           value="${address.region}" type="text">
                </div>
            </div>
        </div>
    </div>


    <div class="row form-group">
        <div class="col-md-3">
            City: <span>*</span>
        </div>
        <div class="col-md-9 no-padding-right">
            <div class="input-group">
                <div class="input-group-addon">
                    <i class="glyphicon glyphicon-map-marker"></i>
                </div>
                <div>
                    <input required autocomplete="on" name="city" class="form-control"
                           value="${address.city}" type="text">
                </div>
            </div>
        </div>
    </div>

    <div class="row form-group">
        <div class="col-md-3">
            Street: <span>*</span>
        </div>
        <div class="col-md-9 no-padding-right">
            <div class="input-group">
                <div class="input-group-addon">
                    <i class="glyphicon glyphicon-road"></i>
                </div>
                <div>
                    <input required autocomplete="on" name="street" class="form-control"
                           value="${address.street}" type="text">
                </div>
            </div>
        </div>
    </div>

    <div class="row form-group">
        <div class="col-md-3">
            Number:
        </div>
        <div class="col-md-9 no-padding-right">
            <div class="input-group">
                <div class="input-group-addon">
                    <i class="fa fa-home"></i>
                </div>
                <div>
                    <input autocomplete="on" name="buildingNumber" class="form-control col-md-10"
                           value="${address.buildingNumber}" type="text">
                </div>
            </div>
        </div>
    </div>
</div>