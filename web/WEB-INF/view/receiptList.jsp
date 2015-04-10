<%@include file="/WEB-INF/templates/header.jsp" %>

<div class="row">
    <table class="table table-hover">
        <thead>
            <tr>
                <th></th>
                    <c:if test="${sessionScope.currentUser.securityGroup == SecurityGroup.ReceiptManager}">
                    <th>User</th>
                    </c:if>
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
                        <img alt="receipt" height="150" width="100" src="data:${receipt.image.format};base64,${receipt.image.base64}">
                    </td>
                    <c:if test="${sessionScope.currentUser.securityGroup == SecurityGroup.ReceiptManager}">
                        <td>${receipt.user.username}</td>
                    </c:if>
                    <td>
                        <c:set value="${receipt.receiptDate}" var="dateUploaded" />
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${dateUploaded}"/>
                    </td>
                    <td>${receipt.receiptItems.totalValue}</td>
                    <td>
                        <c:set value="${receipt.createdOn}" var="dateCreated" />
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${dateCreated}"/>
                    </td>
                    <td>
                        <c:if test="${receipt.finalized == false}">
                            <form method="get" action="editReceipt">
                                <input type="hidden" value="${receipt.receiptID}" name="receiptID"/>
                                <input type="submit" class="btn btn-default" value="Edit Receipt" name="edit"/>
                            </form> 
                        </c:if>
                        <c:if test="${receipt.finalized == true}">
                            <form method="get" action="viewReceipt">
                                <input type="hidden" value="${receipt.receiptID}" name="receiptID"/>
                                <input type="submit" class="btn btn-default" value="View Receipt" name="edit"/>
                            </form> 
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<div>
    <a href="receiptProviderHomepage.html" class="btn btn-default">Back</a>
</div>
<%@include file="/WEB-INF/templates/footer.jsp" %>