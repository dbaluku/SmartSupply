<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 27-Apr-18
  Time: 12:54 PM
  To change this template use File | Settings | File Templates.
--%>
<script type="text/javascript">

    $(document).ready( function() {

        jQuery(document).delegate('a.add-record', 'click', function(e) {
            e.preventDefault();
            var content = jQuery('#sample_table tr'),
                size = jQuery('#order_table >tbody >tr').length + 1,
                element = null,
                element = content.clone();
            element.attr('id', 'rec-'+size);
            element.find('.delete-record').attr('data-id', size);
            element.appendTo('#order_table_body');
            element.find('.sn').html(size);
        });


        jQuery(document).delegate('a.delete-record', 'click', function(e) {
            e.preventDefault();
            var didConfirm = confirm("Are you sure You want to delete");
            if (didConfirm == true) {
                var id = jQuery(this).attr('data-id');
                var targetDiv = jQuery(this).attr('targetDiv');
                jQuery('#rec-' + id).remove();

                //regnerate index number on table
                $('#order_table_body tr').each(function(index) {
                    //alert(index);
                    $(this).find('span.sn').html(index+1);
                });
                return true;
            } else {
                return false;
            }
        });

    });

    $('#').click(function (e) {
        var form = $('#myform');
        e.preventDefault();
        var TableData;
        TableData = storeTblValues();
        //TableData = $.toJSON(TableData);
        TableData = JSON.stringify({

            'TableData' : TableData

        });
        console.log(TableData);
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "POST",
            url: form.attr('action'),
            contentType:"application/json",
            data: JSON.stringify({

                'TableData' : TableData

            }),
            success: function(data){
                alert('Success');

            },
            error: function (e) {
                alert("Error: " + e);
            }
        });
    });


    function storeTblValues() {
        var TableData = new Array();
        $('#order_table tr').each(function (row, tr) {
            TableData[row] = {
                "product_id": $(tr).find('td:eq(0)').text()
                , "quantity": $(tr).find('td:eq(1)').text()
            }
        });
        TableData.shift();  // first row is the table header - so remove
        return TableData;
    }


</script>
