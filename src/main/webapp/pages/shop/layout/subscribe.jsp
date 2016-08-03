<section id="aa-subscribe">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="aa-subscribe-area">
                    <h3>Subscribe our newsletter </h3>
                    <p></p>

                    <div class="row-fluid aa-subscribe-form">
                        <div class="bs-example hidden-block" id="subscribeError">
                            <div class="alert alert-danger">
                                <a class="close" onclick="$('#subscribeError').addClass('hidden-block');">&times;</a>
                                <strong>Error!</strong> Email is invalid. Try another one
                            </div>
                        </div>
                        <div class="bs-example hidden-block" id="subscribeSuccess">
                            <div class="alert alert-success">
                                <a class="close" onclick="$('#subscribeSuccess').addClass('hidden-block');">&times;</a>
                                <strong>Success!</strong> You are successfully subscribed. Check you inbox
                            </div>
                        </div>
                    </div>
                    <form id="subscribeForm" class="aa-subscribe-form">
                        <div class="row-fluid">
                            <input type="email" id="subscribedEmail" name="subscriberEmail"
                                   placeholder="Enter your Email">
                            <input type="submit" value="Subscribe">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<script>
    $('#subscribeForm').submit(function (e) {
                e.preventDefault();
                $.ajax({
                    type: "POST",
                    url: "api/subscribed?email=" + $('#subscribedEmail').val(),
                    dataType: 'json',
                    success: function (response) {
                        $('#subscribeSuccess').removeClass("hidden-block");
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        $('#subscribeError').removeClass("hidden-block");
                        console.log(errorThrown);
                    }
                });
                e.preventDefault();
            }
    );
</script>
