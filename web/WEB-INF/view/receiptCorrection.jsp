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
                    <c:forEach  var="item"  items="${merchantList}">
                        <tr class="" id="tr${item.ID}">
                            <td>
                                <input type="text" id="name${item.ID}" value="${item.name}" class="form-control" disabled="true"/>
                            </td>
                            <td>
                                <input type="hidden" value="" name="included" id="hid${item.ID}"/>
                                <button type="button"  class="btn btn-success" onclick="includeRow(${item.ID})">
                                    <span class="glyphicon glyphicon glyphicon-ok-circle"></span>
                                </button>
                                <button type="button" class="btn btn-danger" onclick="hideRow(${item.ID})">
                                    <span class="glyphicon glyphicon glyphicon-remove-circle"></span>
                                </button>
                            </td>
                        </tr>          
                    </c:forEach>
                </tbody>
                <tfoot>
                    <tr id="trAddRow" class="hidden">
                        <td><input type="text" value="" class="form-control" name="newMerchantName" id="newMerchantName"></td>
                        <td>
                            <button type="button"  class="btn btn-success" onclick="disable('newMerchantName', 1)">
                                <span class="glyphicon glyphicon glyphicon-ok-circle"></span>
                            </button>
                        </td>
                    </tr>
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
                        <th></th>
                    </tr>
                </thead>
                <tbody id="itemRow">
                    <c:forEach  var="item" items="${itemList}" >
                        <tr class="" id="tr${item.ID}">
                            <td><input type="text" id="name${item.ID}" value="${item.name}" class="form-control" disabled="true"/></td>
                            <td><input type="text" id="quantity${item.ID}" value="${item.quantity}" class="form-control" disabled="true"/></td>
                            <td><input type="text" id="price${item.ID}" value="${item.price}" class="form-control" disabled="true"/></td>
                            <td><input type="text" id="total${item.ID}" value="${item.total}" class="form-control" disabled="true"/></td>
                            <td><input type="hidden" value="" name="included" id="hid${item.ID}"/>
                                <button type="button"  class="btn btn-success" onclick="includeRow(${item.ID})">
                                    <span class="glyphicon glyphicon glyphicon-ok-circle"></span>
                                </button>
                                <button type="button" class="btn btn-danger" onclick="hideRow(${item.ID})">
                                    <span class="glyphicon glyphicon glyphicon-remove-circle"></span>
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr></tr> 
                </tbody>
                <tfoot>
                    <tr id="trAddRow">
                        <td><input type="text" class="form-control" id="addItemName" /></td>
                        <td><input type="text" class="form-control" id="addItemQuantity" onblur="guessTotal()"/></td>
                        <td><input type="text" class="form-control" id="addItemPrice" onblur="guessTotal()"/></td>
                        <td><input type="text" class="form-control" id="addItemTotal"/></td>
                        <td> 
                            <button type="button"  class="btn btn-success" onclick="addRow()">
                                <span class="glyphicon glyphicon glyphicon-ok-circle"></span>
                            </button>
                        </td>
                    </tr>
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
                    <c:forEach  var="item" items="${totalList}">
                        <tr class="" id="tr${item.ID}">
                            <td><input type="text" id="price${item.ID} value="${item.price}"  class="form-control" disabled="true"/></td>
                            <td><input type="hidden" value="" name="included" id="hid${item.ID}"/>
                                <button type="button"  class="btn btn-success" onclick="includeRow(${item.ID})">
                                    <span class="glyphicon glyphicon glyphicon-ok-circle"></span>
                                </button>
                                <button type="button" class="btn btn-danger" onclick="hideRow(${item.ID})">
                                    <span class="glyphicon glyphicon glyphicon-remove-circle"></span>
                                </button>
                            </td>
                        </tr>
                    </c:forEach>  
                </tbody>
                <tfoot>
                    <tr id="trAddRow" class="hidden">
                        <td><input type="text" value="" class="form-control" name="newTotal" id="newTotal"></td>
                        <td> 
                            <button type="button"  class="btn btn-success" onclick="disable('newTotal', 3)">
                                <span class="glyphicon glyphicon glyphicon-ok-circle"></span>
                            </button>
                        </td>
                    </tr>
                </tfoot>
            </table>
        </div>

    </div>
    <div class="col-lg-6 col-md-6 col-sm-6">
        <%@include file="/WEB-INF/templates/viewImage.jsp" %>
    </div>
    <div class="row">
        <button type="submit" class="btn btn-success">Submit</button>
    </div>
</form:form> 
<script type="text/javascript">
    var DELETED = 0;
    var INCLUDED = 1;
    var NEW_ITEM_COUNT = 0;

    function itemFactory() {
        return item = {
            ID: "-1",           //Int
            name: "",           //String
            quantity: "",       //ideally int
            price: "",          //ideally double
            total: "",          //Ideally double
            type: "",           //String
            isIncluded:"false"  //Idealy boolean
        };
    }

    function hideRow(id) {
        //Actions
        removeRow(id);
        countShownRows(id);
        doAjaxCall(id);

        function removeRow(id) {
            $("#tr" + id).addClass("hidden");
            $("#hid" + id).val(DELETED);
        }

        function countShownRows(id) {
            var hiddenRows = $("#tr" + id).parent().children(".hidden").length;
            var allRows = $("#tr" + id).parent().children("").length;
            if (hiddenRows === allRows) {
                var child = $("#tr" + id).parent().siblings("tfoot").children()[0];
                $(child).removeClass("hidden");
            }
        }

        function doAjaxCall(id) {
            var item = itemFactory();
            item.ID = id;
            item.isIncluded = "false";
            ajaxCall(item);
        }
    }

    function includeRow(id) {
        $("#hid" + id).val(INCLUDED);
        $("#hid" + id).siblings().addClass("hidden");
    }

    function addRow() {
        NEW_ITEM_COUNT += 1;

        var name = $("#addItemName").val();
        var quantity = $("#addItemQuantity").val();
        var price = $("#addItemPrice").val();
        var total = $("#addItemTotal").val();

        createTable(name, quantity, price, total);
        doAjaxCall(name, quantity, price, total);
        clearBoxes();

        function createTable(name, quantity, price, total) {
            //Create new input boxes
            var txtName = "<td><input type='text' disabled='true' value='" + name + "' name='newItemName" + NEW_ITEM_COUNT + "'/></td>";
            var txtQuantity = "<td><input type='text' disabled='true' value='" + quantity + "' name='newItemQuantity" + NEW_ITEM_COUNT + "'/></td>";
            var txtValue = "<td><input type='text' disabled='true' value='" + price + "' name='newItemPrice" + NEW_ITEM_COUNT + "'/></td>";
            var txtTotal = "<td><input type='text' disabled='true' value='" + total + "' name='newItemTotal" + NEW_ITEM_COUNT + "'/></td>";

            var html = "<tr>" + txtName + txtQuantity + txtValue + txtTotal + "</tr>";
            $("#itemRow").append(html);
        }

        function clearBoxes() {
            //Clear input boxes
            $("#addItemName").val("");
            $("#addItemQuantity").val("");
            $("#addItemPrice").val("");
            $("#addItemTotal").val("");
        }

        function doAjaxCall(name, quantity, price, total) {
            var item = itemFactory();

            item.name = name;
            item.quantity = quantity;
            item.price = price;
            item.total = total;
            item.type = 4;
            item.isIncluded = "true";

            ajaxCall(item);
        }
    }

    function disable(id, type) {
        var args = $("#" + id).val();

        $("#" + id).prop('disabled', true);
        $("#" + id).parent().siblings().addClass("hidden");

        doAjaxCall(type, args);

        function doAjaxCall(type, args) {
            var item = itemFactory();
            item.type = type;
            item.isIncluded = "true";

            switch (type) {
                case 1:
                    item.name = args;
                    break;
                case 3:
                    item.price = args;
                    break;
            }
            ajaxCall(item);
        }
    }

    function guessTotal() {
        var quantity = $("#addItemQuantity").val();
        var price = $("#addItemPrice").val();
        var total = $("#addItemTotal").val();

        if (total == "") {
            if ((quantity != "") & (price != "")) {
                $("#addItemTotal").val(quantity * price);
            }
        }
    }

    function ajaxCall(item) {

        var url = "${fn:replace(fn:replace(pageContext.request.requestURL, "/WEB-INF/jsp", ""),".jsp","")}";

        url = "http://localhost:8084/ExpensesSystem_Spring/receiptCorrection/create.htm";

        var receiptID = "${receiptID}";
        var data = {"receipt": receiptID, "item": item};

        $.ajax({
            url: url,
            type: "post",
            data: JSON.stringify(data),
            contentType: "application/json"
        });

    }



</script>
<%@include file="/WEB-INF/templates/logout.jsp" %>
<%@include file="/WEB-INF/templates/footer.jsp" %>