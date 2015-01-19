<%@include file="/WEB-INF/templates/header.jsp" %>
<form action="uploadReceipt" method="post" enctype="multipart/form-data">
    <div class="form-group">
        <fieldset>
            <label>Receipt Location</label>
            <input class="form-control" type="file" name="file"/>
        </fieldset>
    </div>
    <fieldset>
        <input class="btn btn-success" type="submit">Upload Receipt</input>
    </fieldset>
</form>
<a class="btn btn-danger" href="receiptProviderHomepage">Back</a>
<script type="text/javascript">
    $(document).ready(
            function () {
                $('input:submit').attr('disabled', true);
                $('input:file').change(
                        function () {
                            if ($(this).val()) {
                                $('input:submit').removeAttr('disabled');
                            }
                            else {
                                $('input:submit').attr('disabled', true);
                            }
                        });
            });
</script>

<%@include file="/WEB-INF/templates/logout.jsp" %>
<%@include file="/WEB-INF/templates/footer.jsp" %>