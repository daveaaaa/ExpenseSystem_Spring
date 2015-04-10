<%@include file="/WEB-INF/templates/header.jsp" %>
<div class="row col-lg-12">
<%@include file="/WEB-INF/templates/viewImage.jsp" %>
</div>
<div class="row">
    <form action="parseReceipt" method="post">
        <button type="submit" class="btn btn-default">Parse Receipt</button>
    </form>
</div>
<%@include file="/WEB-INF/templates/logout.jsp" %>
<%@include file="/WEB-INF/templates/footer.jsp" %>