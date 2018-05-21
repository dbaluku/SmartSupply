$(document).ready(function () {

    $("#lnkDeleteGuardian").live('click', function () {
        if (window.confirm("Do you want to delete this Guardian?")) {
            return true;
        }
        return false;
    });

    $('#lnkDeleteStudentProfile').click(function () {
        if (window.confirm("Do you want to delete the selected student?")) {
            return true;
        }
    });

    // For Student Results
    $('#editResultLink, #deleteResultLink').click(function () {
        var link = $(this).attr("href");
        if (link == null) {
            alert("First select a result to perform this operation.");
            return false;
        }

        if ($ (this).attr('id') == 'lnkDeleteResult')
            if (window.confirm("Do you want to delete this Result?")) {
                return true;
            }

        return true;
    });

    showLongResponseText();

    function showLongResponseText() {
        if ($('#longResponseText').length) {
            var text = $('#longResponseText').text().trim();

            if (!isSystemBlank(text))
                alert(text);
        }
    }

    myTitleCase();

    function titleCaseTransform(str) {
        var splitStr = str.toLowerCase().split(' ');
        for (var i = 0; i < splitStr.length; i++) {
            if (['of', 'in', 'and'].indexOf(splitStr[i]) == -1) {
                splitStr[i] = splitStr[i].charAt(0).toUpperCase() + splitStr[i].substring(1);
                if(splitStr[i].charAt(0) == '('){
                    splitStr[i] = '(' + splitStr[i].charAt(1).toUpperCase() + splitStr[i].substring(2);
                }
            }
        }
        return splitStr.join(' ');
    }

    function myTitleCase(){
        var list = document.getElementsByClassName("my-capitalize");
        for(var i = 0; i<list.length; i++){
            var text = list[i].innerHTML;
            var capitalized = titleCaseTransform(text);
            list[i].innerHTML = capitalized;
        }
    }

    /**
     * This is a general filter function for drop down lists,
     * text typed in input with ddfilter=true is used to filter
     * the drop down with id "#ddToFilter"
     */
    $('input[ddfilter=true]').live("keyup", function () {
        var searchText = $(this).attr("value");

        var matchFromStart = $(this).attr("matchFromStart");
        if (matchFromStart === undefined || matchFromStart == "false")
            matchFromStart = false;
        else
            matchFromStart = true;

        var ddToFilter = $(this).attr("ddToFilter");

        var correspondingItems = filterDD(ddToFilter, searchText, matchFromStart);

        if (correspondingItems == 1 && !matchFromStart)
            changeDDSelectedItem(ddToFilter, searchText, correspondingItems);
    });

    function filterDD(ddToFilter, searchText, matchFromStart) {

        var correspondingItems = 0;

        $('#' + ddToFilter + ' option').each(function (i) {
            if ($(this).attr("value") == "none" || $(this).attr("value") == ""
                || $(this).attr("selected") == "selected")
                return;

            var text = $(this).text();

            if (textIsMatching(text, searchText, false, matchFromStart)) {

                correspondingItems++;
                $(this).removeClass("hidden");

                if (correspondingItems == 1 && matchFromStart) {
                    $(this).attr("selected", "selected");
                    $('#' + ddToFilter).trigger("change");
                }
            } else
                $(this).addClass("hidden");

        });

        return correspondingItems;
    }

    function textIsMatching(text, searchText, caseSensitive, matchFromStart) {

        if (!caseSensitive) {
            text = text.toLowerCase();
            searchText = searchText.toLowerCase();
        }
        if (!matchFromStart)
            return (text.indexOf(searchText) != -1);

        else if (searchText.length <= text.length)
            return (searchText == text.substring(0, searchText.length));

        return false;
    }

    $("input[id^='selectAllCheckBoxes']").live('click', function (event) {
        var containerId = $(this).attr("container-id");
        $('#' + containerId + ' :checkbox').filter(':visible').attr('checked', $(this).attr("checked") == "checked");
    });

    $("input[id^='txtFilterCheckBoxes']").live("keyup", function () {
        var searchText = $(this).attr("value").replace(" ", "_");
        var idSubString = $(this).attr("id-sub-string");
        $("label[for^='" + idSubString + "']").each(function (i) {

            if (textIsMatching($(this).text(), searchText, false, false)) {
                $(this).parent(":first").removeClass("hidden");
            }
            else {
                $(this).parent(":first").addClass("hidden");
            }
        });
    });

    // multi-url form submit functions
    $("input[id^='multiUrlForm'],").click(function () {
        var formId = $(this).attr("formId") === undefined ? "multiUrlForm" : $(this).attr("formId");
        var url = getFormUrl(formId, $(this).attr("id"));
        setAttrValue(formId, "action", url);
        return true;

    });

    function getFormUrl(formId, btnId) {
        var url = getAttrValue(formId, "firstAction");

        if (isSystemBlank(url))
            url = getAttrValue(formId, action);

        var dropDownId = getAttrValue(btnId, "selectId");
        if (!isSystemBlank(dropDownId)) {
            var value = getAttrValue(dropDownId, "value");
            return url + value;
        }

        var task = getAttrValue(btnId, "task");

        if (!('contains' in String.prototype)) {
            String.prototype.contains = function (str, startIndex) {
                return -1 !== String.prototype.indexOf.call(this, str, startIndex);
            };
        }

        setAttrValue(formId, "target", task.contains("print") ? "_blank" : "");
        return url+task;
    }
});