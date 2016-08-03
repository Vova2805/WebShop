<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4>Login or Register</h4>
                <div class="aa-login-form" action="">
                    <jsp:include page="../shop/layout/validation.jsp"/>
                    <form role="form" action="<c:url value='/j_spring_security_check' />" method="post"
                          class="aa-login-form">
                        <label for="log-username">Username or Email address<span>*</span></label>
                        <input type="text" id="log-username" placeholder="Username or email" name="username">
                        <label for="log-password">Password<span>*</span></label>
                        <input type="password" id="log-password" placeholder="Password" name="password">
                        <button type="submit" name="submit" class="btn btn-default aa-review-submit">Login</button>
                        <label class="rememberme" for="rememberme"><input type="checkbox" id="rememberme"> Remember me
                        </label>
                        <p class="aa-lost-password"><a href="#">Lost your password?</a></p>
                        <div class="aa-register-now">
                            Don't have an account?<a href="/signup">Register now!</a>
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" class="btn btn-primary"
                               value="${_csrf.token}"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
