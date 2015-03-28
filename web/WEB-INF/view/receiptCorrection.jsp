<%@page import="java.util.ArrayList"%>
<%@include file="/WEB-INF/templates/header.jsp" %>
<div class="row">

    <div class="col-lg-6 col-md-6 col-sm-6">
        <div class="row">
            <table>
                <thead>
                    <tr>
                        <th>Merchant</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach  var="item"  items="${merchantList}">
                        <tr>
                            <td><input type="text" value="${item.name}" class="form-control"/></td>
                            <td><div class="input-group-btn">
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">Action <span class="caret"></span></button>
                                    <ul class="dropdown-menu dropdown-menu-right" role="menu">
                                        <li><a href="#">Action</a></li>
                                        <li><a href="#">Another action</a></li>
                                        <li><a href="#">Something else here</a></li>
                                        <li class="divider"></li>
                                        <li><a href="#">Separated link</a></li>
                                    </ul>
                                </div>
                            </td>
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
                            <td><input type="text" value="${item.name}" class="form-control"/></td>
                            <td><input type="text" value="${item.quantity}" class="form-control"/></td>
                            <td><input type="text" value="${item.price}" class="form-control"/></td>
                            <td><input type="text" value="${item.total}" class="form-control"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="row">
            <table>
                <thead>
                    <tr>
                        <th>Total</th>
                    </tr>
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