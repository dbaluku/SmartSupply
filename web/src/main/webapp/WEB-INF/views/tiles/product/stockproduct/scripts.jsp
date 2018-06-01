<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 25-May-18
  Time: 12:02 AM
  To change this template use File | Settings | File Templates.
--%>
<script src="${baseUrl}/static/js/force_numeric.js"></script>

<%--make sure they select a product on btn sabe click--%>
<%--postivenumber--%>

<script type="text/javascript">

    $(document).ready( function() {
        $('#btnSave').click(function (e) {
            e.preventDefault();
            var product =$('#productlist').val();
            if(isPostiveNumber('quantity')&& !isSystemBlank(product)){
                $('#stocking_form').submit();
            }
            else{
                windows.alert("Required field(s) are missing");
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