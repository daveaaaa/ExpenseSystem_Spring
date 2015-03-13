<%@include file="/WEB-INF/templates/header.jsp" %>

<div class="row">
    <form:form  modelAttribute="user">
        <div class="form-group form-inline">            
            <label for="txtUsername">Username</label>
            <form:input class="form-control" path="username" placeholder="Username" id="txtUsername"/>
        </div>
        <div class="form-group form-inline"> 
            <label for="txtPassword">Password</label>
            <form:input class="form-control" path="password" id="txtPassword"/>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-default">Login</button>
        </div>
    </form:form>
</div>
<%@include file="/WEB-INF/templates/footer.jsp" %>