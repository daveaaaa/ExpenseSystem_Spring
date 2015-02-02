<%@include file="/WEB-INF/templates/header.jsp" %>
<%@include file="/WEB-INF/templates/viewImage.jsp" %>
<div class="row">
    <form action="parseReceipt" method="post">
        <button type="submit" class="btn btn-default">Parse Receipt</button>
    </form>
</div>
<%@include file="/WEB-INF/templates/logout.jsp" %>
<%@include file="/WEB-INF/templates/footer.jsp" %>