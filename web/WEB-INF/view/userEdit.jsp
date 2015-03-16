<%@include file="/WEB-INF/templates/header.jsp" %>
<div class="row">
    <form:form  method="post" modelAttribute="newUser">
        <div class="form-group form-inline">            
            <label for="txtUsername">Username</label>
            <form:input class="form-control" path="username" placeholder="Username" id="txtUsername"/>
        </div>
        <div class="form-group form-inline"> 
            <label for="txtPassword">Password</label>
            <form:input class="form-control" path="password" id="txtPassword"/>
        </div>
        <div  class="form-group form-inline">
             <label for="ddSecurityGroup">Security Group</label>
            <form:select path="securityGroup" items="${securityGroup}" class="form-control" id="ddSecruityGroup"> 
            </form:select>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-default">Update User</button>
            <a href="adminHomepage.html" class="btn btn-danger">Cancel</a>
        </div>
    </form:form>
</div>
<%@include file="/WEB-INF/templates/footer.jsp" %>
