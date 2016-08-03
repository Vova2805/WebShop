<div class="aa-myaccount-register">
    <h4>Register</h4>
    <form action="registerCustomer" commandName="customer" method="post" role="form" id="registration-form"
          class="aa-login-form">
        <label for="reg-username">Login<span>*</span></label>
        <input type="text" name="customerName" id="reg-username" placeholder="Login" required>

        <label for="reg-username">Email<span>*</span></label>
        <input type="email" name="email" id="reg-email" placeholder="Email" required>

        <label for="reg-password">Password<span>*</span></label>
        <input type="password" name="password" id="reg-password" placeholder="Password" required>

        <label for="reg-confirm">Confirm password<span>*</span></label>
        <input type="password" id="reg-confirm" placeholder="Confirm password" required>

        <button type="submit" name="submit" value="submit" class="btn btn-default aa-review-submit">Sign up</button>
        <p class="already-have-account" onclick="changeContent('login')">Already have an account?</p>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</div>