<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 27-Apr-18
  Time: 12:58 PM
  To change this template use File | Settings | File Templates.
--%>

<%--<script src="${baseUrl }/static/files/bower_components/jquery/js/jquery.min.js"></script>--%>
<script src="${baseUrl}/static/files/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="${baseUrl}/static/files/bower_components/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
<script src="${baseUrl}/static/files/assets/pages/data-table/js/jszip.min.js"></script>
<script src="${baseUrl}/static/files/assets/pages/data-table/js/pdfmake.min.js"></script>
<script src="${baseUrl}/static/files/assets/pages/data-table/js/vfs_fonts.js"></script>
<script src="${baseUrl}/static/files/bower_components/datatables.net-buttons/js/buttons.print.min.js"></script>
<script src="${baseUrl}/static/files/bower_components/datatables.net-buttons/js/buttons.html5.min.js"></script>
<script src="${baseUrl}/static/files/bower_components/datatables.net-bs4/js/dataTables.bootstrap4.min.js"></script>
<script src="${baseUrl}/static/files/bower_components/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
<%--<script src="${baseUrl}/static/files/bower_components/datatables.net-responsive-bs4/js/responsive.bootstrap4.min.js"></script>--%>

<script>
    $(document).ready(function() {
        $('#example').DataTable( {
            dom: 'Bfrtip',
            buttons: [
                'copy', 'csv', 'excel', 'pdf', 'print'
            ]
        } );
    } );
</script>
