function getBaseURL() {
    var baseUrl = $("#baseUrl").attr("value");
    if (baseUrl == null || baseUrl == "")
        baseUrl = location.protocol + "//" + location.hostname
        + (location.port && ":" + location.port) + "/";
    else
        baseUrl = baseUrl + "/";
    return baseUrl;
}

function isSystemBlank(str) {
    if (str == null || str == "" || str == "none")
        return true;

    return false;
}

function getAttrValue(id, attrName) {
    return $("#" + id).attr(attrName);
}

function setAttrValue(id, attrName, value) {
    return $("#" + id).attr(attrName, value);
}

function toggleCssClass(id, cssClass) {
    var element = $("#" + id);
    element.hasClass(cssClass) ? element.removeClass(cssClass) : element.addClass(cssClass);
}

// sets the system message
function setSystemMessage(message) {
    $("div#systemMsg #sMsg").html(message);
    $("div#systemMsg").removeClass("hidden");

    if (!$("div#errorMsg").hasClass("hidden"))
        $("div#errorMsg").addClass("hidden");
}

// sets the error message
function setErrorMessage(message) {
    $("div#errorMsg #eMsg").html(message);
    $("div#errorMsg").removeClass("hidden");

    if (!$("div#systemMsg").hasClass("hidden"))
        $("div#systemMsg").addClass("hidden");
}

// System wide Javascript file
$(document).ready(function () {

    // setup global ajax requests
    $.ajaxSetup({
        beforeSend: function (xhr, settings) {
            xhr.setRequestHeader("X-AjaxRequest", "1");
        },
        complete: function (xhr, textStatus) {
            if (xhr.status == 601) {
                // alert("xhr status: " + xhr.status);
                window.location.reload();
            }
        }
    });

    // setting the datepicker settings
    $(".datepicker").datepicker({
        changeMonth: true,
        changeYear: true,
        changeDay: true,
        dateFormat: 'dd/mm/yy'
    });

    $(".datepicker").live("focus", function () {
        $(this).datepicker({
            changeMonth: true,
            changeYear: true,
            changeDay: true,
            dateFormat: 'dd/mm/yy'
        });
    });

    // setup the ajax loader
    $("#loader").hide().ajaxStart(function () {
        $(this).show();
    }).ajaxStop(function () {
        $(this).hide();
    });

    // initializing global dialog box
    $('#dialogBox').dialog({
        autoOpen: false,
        modal: true,
        beforeClose: function (event, ui) {
            return (window.confirm("Do you want to close this window?"));
        }
    });

    // initializing global button theme
    $(".uiButton").button();

    $("table.recordTable tbody tr").live('click', function (event) {

        if($(this).attr("id").indexOf("-hidden") != -1){
            return;
        }

        if (event.target.type == 'checkbox') {
            if ($(':checkbox', this).attr('checked')) {
                $("table.recordTable tr.selected").removeClass("selected");
                $(this).addClass("selected");
            } else
                $(this).removeClass("selected");
        } else {
            var url = $(this).attr("url");
            if (url != null) {
                window.open(url);
            }

            if ($(':checkbox', this).attr('checked')|| $(this).hasClass('selected')) {
                $(this).removeClass("selected");
                $(':checkbox', this).attr('checked', false);
            } else {

                $("table.recordTable tr.selected").removeClass("selected");
                $("table.recordTable tr input:checked").attr("checked", false);

                $(this).addClass("selected");
                $(':checkbox', this).attr('checked', true);
            }
        }


        //$("tr[id$='-hidden']").addClass("hidden");
        toggleCssClass($(this).attr("id") + "-hidden", "hidden");
    });


    /**
     * everytime the cbxSelectAllItems is selected, all rows in
     * the same table should be selected.
     */
    $("#cbxSelectAllItems").live('click', function (event) {
        if ($(this).attr("checked") == "checked") {
            $("table.recordTable tbody tr").each(function () {
                $(this).addClass("selected");
                $(":checkbox", $(this)).attr('checked', true);
            });
        } else {
            $("table.recordTable tbody tr").each(function () {
                $(this).removeClass("selected");
                $(":checkbox", this).attr('checked', false);
            });
        }
    });

    $("#btnSubmitSearchStudent").live('click', function () {
        var searchTerm = $("#txtSearchTerm").attr("value");
        if (searchTerm == null || searchTerm == "") {
            alert("Search term required!");
            return false;
        }

        return true;
    });

    $("#btnHelp").live('click',function () {
        var btnText = $("#btnHelp").attr("value").toLowerCase();
        if ($('#divHelp').length) {
            if (btnText == "show help") {
                $('#divHelp').removeClass("hidden");
                $("#btnHelp").attr("value", "Hide help");
            } else {
                $('#divHelp').addClass("hidden");
                $("#btnHelp").attr("value", "Show help");
            }
        }
    });

    $(".btnshowhide").live('click', function () {
        var showText = $(this).attr("showText");
        var hideText = $(this).attr("hideText");
        var showHideId = $(this).attr("show-hide-id");
        var showHideName = $(this).attr("show-hide-name");

        var textInFirstChild = false;
        var btnText = $(this).attr("value");

        // if text is blank, check in the nested
        // span
        if (isSystemBlank(btnText)) {
            textInFirstChild = true;
            btnText = $(this).children(":first").text();
        }

        var newText = btnText == showText ? hideText: showText;

        if (!isSystemBlank(showHideName)) {
            if ($("[name='" + showHideName+ "']").length) {
                if (btnText == showText) {
                    $("[name='" + showHideName + "']").removeClass("hidden");
                } else {
                    $("[name='" + showHideName + "']").addClass("hidden");
                }
            }
        } else {

            if ($('#' + showHideId).length) {
                if (btnText == showText) {
                    $('#' + showHideId).removeClass("hidden");
                } else {
                    $('#' + showHideId).addClass("hidden");
                }
            }
        }

        if (textInFirstChild) {
            $(this).children(":first").text(newText);
        } else
            $(this).attr("value", newText);

    });

    /**
     * This is a general filter function for drop down lists,
     * text typed in "#txtDropDownFilter" is used to filter the
     * drop down with id "#idOfdropDownToFilter"
     */
    $('#txtDropDownFilter').change(function () {
        var searchText = $(this).attr("value").toLowerCase();
        var idOfdropDownToFilter = $(this).attr("idOfdropDownToFilter");
        $('#' + idOfdropDownToFilter + ' option').each(
            function (i) {
                if ($(this).attr("value") == "none" || $(this).attr("value") == ""
                    || $(this).attr("selected") == "selected")
                    return;

                var text = $(this).text();
                if (text.toLowerCase().indexOf(searchText) != -1)
                    $(this).removeClass("hidden");
                else
                    $(this).addClass("hidden");

            });
    });

    $('#btnSubmitOnLoginInterface').click(function () {
        var username = $('#j_username').attr("value");
        if (username == null || username == "") {
            alert("Username required!! (Note: if you are a student, use your reg-no)");
            return false;
        }

        var password = $('#j_password').attr("value")
        return true;
    });
});
