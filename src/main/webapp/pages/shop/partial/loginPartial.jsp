<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="aa-myaccount-login">
    <h4>Login</h4>
    <form role="form" action="<c:url value='/j_spring_security_check' />" method="post" class="aa-login-form">
        <label for="log-username">Username or Email address<span>*</span></label>
        <input type="text" id="log-username" placeholder="Username or email" name="username">
        <label for="log-password">Password<span>*</span></label>
        <input type="password" id="log-password" placeholder="Password" name="password">
        <button type="submit" name="submit" class="btn btn-default aa-review-submit">Login</button>
        <label class="rememberme" for="rememberme"><input type="checkbox" id="rememberme"> Remember me </label>
        <p class="aa-lost-password rememberme">Lost your password?</p>
        <p class="aa-lost-password rememberme" onclick="changeContent('register')">Register as a new user</p>
        <input type="hidden" name="${_csrf.parameterName}" class="btn btn-primary" value="${_csrf.token}"/>
    </form>
</div>

