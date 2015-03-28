<%@page import="java.util.ArrayList"%>
<%@include file="/WEB-INF/templates/header.jsp" %>
<div class="row">

    <div class="col-lg-6 col-md-6 col-sm-6">
        <div class="row">
            <table>
                <thead>
                <th>Merchant</th>
                </thead>
                <tbody>
                    <c:forEach  var="item"  items="${merchantList}">
                        <tr>
                            <td>${item.name}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="row">
            <table class="table table-hover table-striped">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Total</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach  var="item" items="${itemList}" >
                        <tr>
                            <td><input type="text" value="${item.name}" class="input-group input-group-sm"/></td>
                            <td><input type="text" value="${item.quantity}" class="input-group input-group-sm"/></td>
                            <td><input type="text" value="${item.price}" class="input-group input-group-sm"/></td>
                            <td><input type="text" value="${item.total}" class="input-group input-group-sm"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="row">
            <table>
                <thead>
                <th>Total</th>
                </thead>
                <tbody>
                    <c:forEach  var="item" items="${totalList}">
                        <tr>
                            <td>${item.price}</td>
                        </tr>
                    </c:forEach> 
                </tbody>
            </table>
        </div>
    </div>
    <div class="col-lg-6 col-md-6 col-sm-6">
        <%@include file="/WEB-INF/templates/viewImage.jsp" %>
    </div>
</div>
<%@include file="/WEB-INF/templates/logout.jsp" %>
<%@include file="/WEB-INF/templates/footer.jsp" %>