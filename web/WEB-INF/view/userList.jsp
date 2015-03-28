<%@include file="/WEB-INF/templates/header.jsp" %>
<div class="row">
    <table class="table table-hover">
        <thead>
            <tr>
                <th>Username</th>
                <th>Password</th>
                <th>Security Group</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.username}</td>
                    <td>${user.password}</td>
                    <td>${user.securityGroup}</td>
                    <form method="get" action="editUser">
                        <input type="hidden" value="${user.userID}" name="userID"/>
                        <td><input type="submit" class="btn btn-default" value="Edit User" name="edit"/></td>
                    </form>
                    <form method="post">
                        <input type="hidden" value="${user.userID}" name="userID"/>
                        <td><input type="submit" class="btn btn-danger" value="Delete" name="delete"/></td>
                    </form>    
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<div>
    <a href="adminHomepage.html" class="btn btn-default">Back</a>
</div>
<%@include file="/WEB-INF/templates/footer.jsp" %>