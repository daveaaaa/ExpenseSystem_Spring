<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="/WEB-INF/templates/header.jsp" %> 

<form:form  method="get" action="receiptProviderHomepage.htm">
    <div class="col-lg-6 col-md-6 col-sm-6">
        <div class="row">
            <table class="col-lg-6 col-md-6 col-sm-6">
                <thead>
                    <tr>
                        <th>Merchant</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody id="merchantRow">
                    <c:choose>
                        <c:when test="${merchantList.size() > 0}">
                            <c:forEach  var="item"  items="${merchantList}">
                                <tr class="" id="tr${item.ID}">
                                    <td>
                                        <input type="text" id="name${item.ID}" value="${item.name}" class="form-control" disabled="true"/>
                                    </td>
                                </tr>          
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr></tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
                <tfoot>
                </tfoot>
            </table>
        </div>
        <div class="row">
            <table class="table table-hover table-striped col-lg-6 col-md-6 col-sm-6">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Total</th>
                    </tr>
                </thead>
                <tbody id="itemRow">
                    <c:forEach  var="item" items="${itemList}" >
                        <tr class="" id="tr${item.ID}">
                            <td><input type="text" id="name${item.ID}" value="${item.name}" class="form-control col-xs-2" disabled="true"/></td>
                            <td><input type="text" id="quantity${item.ID}" value="${item.quantity}" class="form-control col-xs-2" disabled="true"/></td>
                            <td><input type="text" id="price${item.ID}" value="${item.price}" class="form-control col-xs-2" disabled="true"/></td>
                            <td><input type="text" id="total${item.ID}" value="${item.total}" class="form-control col-xs-2" disabled="true"/></td>
                        </tr>
                    </c:forEach>
                    <tr></tr> 
                </tbody>
                <tfoot>
                </tfoot>
            </table>
        </div>
        <div class="row">
            <table class="table table-hover table-striped col-lg-6 col-md-6 col-sm-6"> 
                <thead>
                    <tr>
                        <th>Total</th>
                    </tr>
                </thead>
                <tbody id="totalRow">
                    <c:choose>
                        <c:when test="${totalList.size() > 0}">
                            <c:forEach  var="item" items="${totalList}">
                                <tr class="" id="tr${item.ID}">
                                    <td><input type="text" id="price${item.ID}" value="${item.price}"  class="form-control" disabled="true"/></td>
                                    <td><input type="hidden" value="" name="included" id="hid${item.ID}"/></td>
                                </tr>
                            </c:forEach>  
                        </c:when>
                        <c:otherwise>
                            <tr></tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
                <tfoot>
                </tfoot>
            </table>
        </div>

    </div>
    <div class="col-lg-6 col-md-6 col-sm-6">
        <%@include file="/WEB-INF/templates/viewImage.jsp" %>
    </div>
    <div class="row">
        <button type="submit" class="btn btn-default">Back</button>
    </div>
</form:form> 
<%@include file="/WEB-INF/templates/logout.jsp" %>
<%@include file="/WEB-INF/templates/footer.jsp" %>