<%@include file="/WEB-INF/templates/header.jsp" %>
<div class="row">
    <table class="table table-hover">
        <thead>
            <tr>
                <th></th>
                <th>Receipt Date</th>
                <th>Total</th>
                <th>Created Date</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="receipt" items="${receipts}">
                <tr>
                    <td>   
                        <img alt="receipt" height="500" width="300" src="data:${receipt.image.format};base64,${receipt.image.base64}">
                    </td>
                    <td>${receipt.receiptdate}</td>
                    <td>${receipt.total}</td>
                    <td>${receipt.createdon}</td>
            <form method="get" action="editreceipt">
                <input type="hidden" value="${receipt.receiptID}" name="receiptID"/>
                <td><input type="submit" class="btn btn-default" value="Edit User" name="edit"/></td>
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