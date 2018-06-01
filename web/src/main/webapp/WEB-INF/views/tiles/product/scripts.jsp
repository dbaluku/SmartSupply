<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 25-May-18
  Time: 10:01 AM
  To change this template use File | Settings | File Templates.
--%>
<script src="${baseUrl}/static/js/force_numeric.js"></script>
<script type="text/javascript">
    $(document).ready( function() {
        $('#btnSave').click(function (e) {
            e.preventDefault();
            var product_name =$('#txtName').val();
            if(! isSystemBlank(product_name)){

            if(isPostiveNumber('unit_price')){
                $('#product_form').submit();
            }}
            else{
                window.alert("Required field(s) are missing");
            }

        });
    });

    function isSystemBlank(str) {
        if (str == null || str == "" || str == "none")
            return true;

        return false;
    }

    function isPostiveNumber(id) {
        var fieldName = jQuery("#" + id).attr('name');
        var numericExpression = /^[+]?[0-9]+(\.[0-9]+)?$/;
        var num = $("#" + id).val();

        if (!numericExpression.test(num)) {
            alert(fieldName + " must be a number!!");
            return false;
        }
        return true;
    }


</script>
