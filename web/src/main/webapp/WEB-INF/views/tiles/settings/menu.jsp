<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions' %>


<style type="text/css" media="Screen">

    #expandable-navigation ul {
        list-style-type: none;
        padding: 0;
        margin: 1em;
        width: auto;
    }

    #expandable-navigation a {
        text-decoration: none;
        display: block;
        padding: 3px 12px 3px 8px;
        background-color: #774242;
        color: #fff;
        border: 1px solid #ddd;
        height: 1.6em;
        border-radius: 0.5em;
    }

    #expandable-navigation .group-title {
        background-color: #828282 !important;
        color: #fff;
    }

    .pre-active {
        background-color: #538c5a !important;
        color: #000  !important;
    }

    #expandable-navigation .activeGroup, #expandable-navigation .activeItem {
        border-radius: 0.5em;
        background-color: #000000 !important;
        /*background-color: #0A4085 !important;*/
        color: #FFF !important;
    }
</style>
<script type="text/javascript">
    function swap(targetId) {
        if (document.getElementById) {
            var targetGroupId = "group-" + targetId;

            var groups = $("ul[id=group-ul]").children("li").get();

            $.each(groups, function (index, group) {
                var groupUl = $(group).children('ul').first();
                var groupTitle = $(group).children('a').first();
                var itemId = group.id;
                if(!groupTitle.hasClass("activeGroup") && !$("#" + targetId).hasClass("activeGroup")) {
                    if(itemId == targetGroupId) {
                        groupUl.toggleClass("hidden");
                        groupTitle.toggleClass("pre-active");
                        groupTitle.toggleClass("group-title");
                    } else {
                        if(!groupUl.hasClass("hidden")) {
                            groupUl.addClass("hidden");
                        }
                        groupTitle.removeClass("pre-active");
                        groupTitle.addClass("group-title");
                    }
                }
            });
        }
    }
</script>
<div id="expandable-navigation">
    <ul id="group-ul">
        <li id="group-courses-and-intakes">

            <menuTags:group-title groupName="courses-and-intakes" displayName="Courses & Intakes"/>

            <ul id="courses-and-intakes"
        <cssTags:boolean-classes printClass="true" trueCssClasses="" falseCssClasses="hidden"
                                 value="${activeGroup eq 'courses-and-intakes'}"/>

        <menuTags:menu-item url="course/view" itemName="courses" displayName="Courses"/>
        <menuTags:menu-item url="intakes_item/view" itemName="intakes_item" displayName="Intakes"/>
        <menuTags:menu-item url="course-intakes/view" itemName="course-intakes" displayName="Course Intakes"/>
    </ul>
    </li>

    <li id="group-curriculum-and-course-units">

        <menuTags:group-title groupName="curriculum-and-course-units" displayName="Program & Course Units"/>

        <ul id="curriculum-and-course-units"
        <cssTags:boolean-classes printClass="true" trueCssClasses="" falseCssClasses="hidden"
                                 value="${activeGroup eq 'curriculum-and-course-units'}"/>
        <menuTags:menu-item url="curriculums/view" itemName="curriculums|yos" displayName="Program"/>
        <menuTags:menu-item url="courseunits/view" itemName="courseunits" displayName="Course Units"/>





        </ul>
    </li>
    <li id="group-users-roles-and-org-units">

        <menuTags:group-title groupName="users-roles-and-org-units" displayName="Users, Roles & Org-Units"/>

        <ul id="users-roles-and-org-units"
                <cssTags:boolean-classes printClass="true" trueCssClasses="" falseCssClasses="hidden"
                                         value="${activeGroup eq 'users-roles-and-org-units'}"/>>

            <menuTags:menu-item url="staff-users/view" itemName="staff-users" displayName="Users"/>
            <menuTags:menu-item url="staff-users/user-types" itemName="user-types" displayName="User Types"/>
            <menuTags:menu-item url="org-units/view" itemName="org-units" displayName="Organisation Units"/>
            <menuTags:menu-item url="org-units/types" itemName="org-unit-types" displayName="Organisation Unit Types"/>
            <menuTags:menu-item url="job-titles/view" itemName="job-titles" displayName="Job Titles"/>
            <menuTags:menu-item url="roles/view" itemName="roles" displayName="Roles"/>
            <menuTags:menu-item url="roles/permissions" itemName="permissions" displayName="Permissions"/>
            <menuTags:menu-item url="urls/view" itemName="urls" displayName="Urls"/>
        </ul>
    </li>

    <li id="group-concepts_and_concept_categories">

        <menuTags:group-title groupName="concepts_and_concept_categories" displayName="Concepts"/>

        <ul id="concepts_and_concept_categories"
                <cssTags:boolean-classes printClass="true" trueCssClasses="" falseCssClasses="hidden"
                                         value="${activeGroup eq 'concepts_and_concept_categories'}"/>>

            <menuTags:menu-item url="settings/view/concepts/page/1" itemName="concepts" displayName="Concepts"/>
            <menuTags:menu-item url="settings/view/conceptcategories/page/1" itemName="concept-categories" displayName="Concept Categories"/>
            <menuTags:menu-item url="settings/student-statuses" itemName="student-statuses" displayName="Student Statuses"/>
        </ul>
    </li>

    <li id="group-grading_systems_and_grades">

        <menuTags:group-title groupName="grading_systems_and_grades" displayName="Grading Systems & Grades"/>

        <ul id="grading_systems_and_grades"
                <cssTags:boolean-classes printClass="true" trueCssClasses="" falseCssClasses="hidden"
                                         value="${activeGroup eq 'grading_systems_and_grades'}"/>>

            <menuTags:menu-item url="grading-systems/view" itemName="grading-systems|grades"
                                displayName="Grading Systems"/>
            <menuTags:menu-item url="special-grades/view" itemName="special-grades" displayName="Special Grades"/>

        </ul>
    </li>
    <li id="group-graduations">

        <menuTags:group-title groupName="graduations" displayName="Graduation"/>

        <ul id="graduations"
        <cssTags:boolean-classes printClass="true" trueCssClasses="" falseCssClasses="hidden"
                                 value="${activeGroup eq 'graduations'}"/>
        <menuTags:menu-item url="graduations/view" itemName="graduations" displayName="Graduations"/>
        <menuTags:menu-item url="print-authorizations/view" itemName="print-authorizations"
                            displayName="Print Authorization"/>
        </ul>

    </li>
    </ul>

</div>