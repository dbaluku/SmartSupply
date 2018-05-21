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
            var index =size - 1;
            element.find('#id_select_0').attr('name','products['+index +'].product.id');
            element.find('#id_quantity_0').attr('name','products['+index+'].quantity');
            element.find('#id_unitprice_0').attr('id','id_unitprice_' + size);
            element.find('#id_total_0').attr('id','id_total_' + size);
            element.find('#id_select_0').attr('id','id_select_' + size);
            element.find('#id_quantity_0').attr('id','id_quantity_' +size);

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

                jQuery('#order_table_body >tr').each(function(index) {
                    var i=index+1;
                    //alert(index);
                    //$(this).find('span.sn').html(index-1);
                    $(this).attr('id','rec-' + i);
                });

                jQuery('#order_table_body >tr').each(function(index) {
                    var i=index+1;

                    $(this).find('td:first').find('select').attr('name','products['+index +'].product.id');
                    $(this).find('td:first').find('select').attr('id','id_select_' + i);
                    $(this).find('td:nth-child(2)').find('input').attr('name','products['+index+'].quantity');
                    $(this).find('td:nth-child(2)').find('input').attr('id','id_quantity_' + i);
                    $(this).find('td:nth-child(3)').find('input').attr('name','order['+index +']');
                    $(this).find('td:nth-child(3)').find('input').attr('id','id_unitprice_' + i);
                    $(this).find('td:nth-child(4)').find('input').attr('name','total_amount['+index+']');
                    $(this).find('td:nth-child(4)').find('input').attr('id','id_total_' + i);
                    $(this).find('td:nth-child(5)').find('.delete-record').attr('data-id',i);

//                    $(this).find('td').each (function() {
//                        $(this).find('')
//                        $(this).find('#id_select').attr('name','products['+index+'].product.id');
//                        $(this).find('#id_quantity').attr('name','products['+index+'].quantity');
//                        // do your cool stuff
//                    });

                });
                return true;
            } else {
                return false;
            }
        });

        jQuery(document).delegate('select.product-select', 'change', function(e)
         {

//            console.log($(this).val());
            var option_id = $(this).val();
//            extract row id no from id of select
            var x = $(this).attr('id');
            var stringLength = x.length;
            var indexvalue =x.charAt(stringLength - 1);
            //id of input value to set unitprice
            var selct_value_to_change_id = 'id_unitprice_' +indexvalue;
            var unit_price =$('#' + option_id).val();
            $('#' + selct_value_to_change_id).attr('value',unit_price);
            //$('#id_total_1').attr('value',unit_price);


//            console.log(unit_price);
        });

        jQuery(document).delegate('input.amount_calc', 'change', function(e)
        {
            //console.log($(this).val());
            var item_qnty = $(this).val();
            var x = $(this).attr('id');
            var stringLength = x.length;
            var indexvalue =x.charAt(stringLength - 1);

//            getting product id that was selected
            var product_id = $('#id_select_' + indexvalue).val();
            var stck_quantity = $('.stock_quantity').find('#' + product_id).val();
            console.log(stck_quantity);
            if(stck_quantity >= item_qnty){
                //id of input value to set unitprice
                var total_id = 'id_total_' + indexvalue;
                var unit_price = $('#id_unitprice_' + indexvalue).val();
                var item_total = unit_price * item_qnty;
                $('#' + total_id).attr('value', item_total);
                compute_total_amount_for_Order();
            }

            else {
                window.alert("Quantity not in stock." +
                    "Please make sure it's" +  " " +stck_quantity +"and below");
                $('#id_quantity_'+ indexvalue).focus();

            }

        });

    });

    function refresh_row(some_vlaue){
        var quantity = $('#rec_' + some_vlaue).find('#id_quantity_' + some_vlaue).val();
        var unitprice = $('#rec_' + some_vlaue).find('#id_unitprice_' + some_vlaue).val();
        var total = quantity * unitprice;
        $('#id_total_' + some_vlaue).attr('value',total);
//        console.log('yes i did it');
    }

    function compute_total_amount_for_Order() {
        var total_amt =parseFloat(0.0);
        jQuery('#order_table_body >tr').each(function(index) {
            var i=index+1;

            var index_value = parseFloat($(this).find('#id_total_'+ i).val());
            total_amt =total_amt + index_value ;
            $('#final_amount').attr('value',total_amt);
        });

    }

//    $('#').click(function (e) {
//        var form = $('#myform');
//        e.preventDefault();
//        var TableData;
//        TableData = storeTblValues();
//        //TableData = $.toJSON(TableData);
//        TableData = JSON.stringify({
//
//            'TableData' : TableData
//
//        });
//        console.log(TableData);
//        $.ajax({
//            headers: {
//                'Accept': 'application/json',
//                'Content-Type': 'application/json'
//            },
//            type: "POST",
//            url: form.attr('action'),
//            contentType:"application/json",
//            data: JSON.stringify({
//
//                'TableData' : TableData
//
//            }),
//            success: function(data){
//                alert('Success');
//
//            },
//            error: function (e) {
//                alert("Error: " + e);
//            }
//        });
//    });


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
