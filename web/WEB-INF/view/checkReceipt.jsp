<%@page import="java.util.ArrayList"%>
<%
    business.businessModel.Receipt receipt = (business.businessModel.Receipt) request.getAttribute("receipt");
    ArrayList<business.businessModel.ReceiptItems> receiptItems = receipt.getReceiptItems();
    pageContext.setAttribute("receiptItems", receiptItems);
%>
<%@include file="/WEB-INF/templates/header.jsp" %>
<div class="row">
    <div class="col-lg-6 col-md-6 col-sm-6">
        <%@include file="/WEB-INF/templates/viewImage.jsp" %>
    </div>
    <div class="col-lg-6 col-md-6 col-sm-6">
        <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Value</th>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach  items="${receiptItems}" var="item">
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%@include file="/WEB-INF/templates/logout.jsp" %>
<%@include file="/WEB-INF/templates/footer.jsp" %>