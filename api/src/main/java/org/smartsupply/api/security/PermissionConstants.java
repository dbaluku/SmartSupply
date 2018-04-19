package org.smartsupply.api.security;

import org.smartsupply.model.admin.Permission;

import java.lang.reflect.Field;

public final class PermissionConstants {

    private PermissionConstants() {

    }

    private static Field getField(String permissionConstants)
            throws NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {

        Field[] fields = PermissionConstants.class.getFields();

        if (fields != null)
            for (Field field : fields) {
                String name = (String) field.get(null);
                if (name.equalsIgnoreCase(permissionConstants))
                    return field;
            }

        return null;
    }

    public static Permission getPermission(String permissionConstants) throws NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        Field field = getField(permissionConstants);
        if (field == null)
            return null;
        else
            return getPermission(field);
    }

    private static Permission getPermission(Field field)
            throws NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {

        PermissionAnnotation annotation = field.getAnnotation(PermissionAnnotation.class);

        String id = annotation.id();
        String desc = annotation.description();
        String name = (String) field.get(null);

        Permission perm = new Permission(id, name, desc);

        return perm;
    }

    @PermissionAnnotation(id = "12078A16-C74D-4f83-BF2E-A4B6A50824FB", description = "Ability to view the administration control panel ")
    public static final String PERM_VIEW_SETTINGS = "perm_view_administration";

    @PermissionAnnotation(id = "perm_manage_settings", description = "Ability to add/edit/delete settings")
    public static final String PERM_MANAGE_SETTINGS = "perm_manage_settings";

    @PermissionAnnotation(id = "297CA129-AFEC-470e-806F-B4C0ADCBB710", description = "Ability to access web resources if api is used in a web application")
    public static final String PERM_WEB_ACCESS = "perm_web_access";

    @PermissionAnnotation(id = "ECA30E67-D583-4fe5-8576-64F86EFD0DE7", description = "Ability to add address info")
    public static final String ADD_ADDRESS_INFO = "perm_add_address_info";

    @PermissionAnnotation(id = "6379EBCE-A8E0-4a29-9889-9EE6B1FE11EC", description = "Ability to edit address info")
    public static final String EDIT_ADDRESS_INFO = "perm_edit_address_info";

    @PermissionAnnotation(id = "42B47E60-9DB9-4125-A4AF-E5826CB4C5A5", description = "Ability to view address info")
    public static final String VIEW_ADDRESS_INFO = "perm_view_address_info";

    @PermissionAnnotation(id = "0C54C990-7A73-47b5-8AC7-202E711EEA49", description = "Ability to delete address info")
    public static final String DELETE_ADDRESS_INFO = "perm_delete_address_info";

    @PermissionAnnotation(id = "47C6A0EE-0001-4831-B731-2763B56209EE", description = "Ability to add course, options, plans, planIntakeCurriculums, courseIntakes and Intakes")
    public static final String ADD_COURSE_DETAILS = "perm_add_course_details";

    // permission to edit a course, Intake, courseIntake
    @PermissionAnnotation(id = "32FAE4F8-4D9A-4980-BEAF-22E8E4055A92", description = "Ability to edit course, options, plans, planIntakeCurriculums, courseIntakes and Intakes")
    public static final String EDIT_COURSE_DETAILS = "perm_edit_course_details";

    // permission to delete a course, Intake, courseIntake
    @PermissionAnnotation(id = "8801D613-B22B-452a-A2E8-C3DE753245BC", description = "Ability to delete course, options, plans, planIntakeCurriculums, courseIntakes and Intakes")
    public static final String DELETE_COURSE_DETAILS = "perm_delete_course_details";

    // permission to add a curriculum , YearName, YearOfStudy, SememsterName, Semester, SemesterCourseUnit
    @PermissionAnnotation(id = "878B315E-15A3-4411-8F35-7FE35460A50D", description = "Ability to add curriculum, yearOfStudy, Semester, Sem-CourseUnit, CourseUnit")
    public static final String ADD_CURRICULUM_DETAILS = "perm_add_curriculum_details";

    // permission to edit a curriculum, YearName, YearOfStudy, SememsterName, Semester, SemesterCourseUnit
    @PermissionAnnotation(id = "0B778F0D-430F-4868-8004-9580CD29B34D", description = "Ability to edit curriculum, yearOfStudy, Semester, Sem-CourseUnit, CourseUnit")
    public static final String EDIT_CURRICULUM_DETAILS = "perm_edit_curriculum_details";

    // permission to delete a curriculum, YearName, YearOfStudy, SememsterName, Semester, SemesterCourseUnit
    @PermissionAnnotation(id = "D7DC7027-9956-45e2-B4A4-5433C73A28A2", description = "Ability to delete curriculum, yearOfStudy, Semester, Sem-CourseUnit, CourseUnit")
    public static final String DELETE_CURRICULUM_DETAILS = "perm_delete_curriculum_details";

    // permission to add a grading system, grades, specialGrades
    @PermissionAnnotation(id = "DB018B4C-931B-40f9-BB7D-E41BA48787FB", description = "Ability to add grading system details")
    public static final String ADD_GRADING_SYSTEM_DETAILS = "perm_add_grading_system_details";

    // permission to edit a grading system, grades, specialGrades
    @PermissionAnnotation(id = "07DD340B-6199-4bd7-ADBA-5C111123D9AE", description = "Ability to edit grading system details")
    public static final String EDIT_GRADING_SYSTEM_DETAILS = "perm_edit_grading_system_details";

    // permission to delete a grading system, grades, specialGrades
    @PermissionAnnotation(id = "A3A79DAD-C72A-4a1f-B8B8-784B8D4C7B94", description = "Ability to delete grading system details")
    public static final String DELETE_GRADING_SYSTEM_DETAILS = "perm_delete_grading_system_details";

    //permission to add a concept_category, concepts (this includes add and update)
    @PermissionAnnotation(id = "8841EFC7-EAEF-4b1a-93BD-A387FD1295F4", description = "Ability to add concept details")
    public static final String ADD_CONCEPT_DETAILS = "perm_add_concept_details";

    // permission to view an concept category, concepts
    @PermissionAnnotation(id = "44ABDB3F-97BB-4523-9FE1-C730D3B378C2", description = "Ability to edit concept details")
    public static final String EDIT_CONCEPT_DETAILS = "perm_edit_concept_details";

    @PermissionAnnotation(id = "2CD8DF85-152E-4c30-BF74-FE18103A5D2D", description = "Ability to delete concept details")
    public static final String DELETE_CONCEPT_DETAILS = "perm_delete_concept_details";

    @PermissionAnnotation(id = "perm_manage_roles", description = "Ability to add/edit/delete roles")
    public static final String MANAGE_ROLES = "perm_manage_roles";

    @PermissionAnnotation(id = "19A55078-045E-43a6-A40D-8E6159C57963", description = "Ability to add user, job title")
    public static final String ADD_USER = "perm_add_user";

    @PermissionAnnotation(id = "D7D8E11E-A0AB-4fc5-9C40-CC28F180834A", description = "Ability to edit user, job title")
    public static final String EDIT_USER = "perm_edit_user";

    @PermissionAnnotation(id = "5D5A8046-98F5-4ef3-ADE5-97135204C31F", description = "Ability to delete user, job title")
    public static final String DELETE_USER = "perm_delete_user";

    // @PermissionAnnotation(id = "FAC88688-E04F-4ba7-B76D-AAF93351C115",
    // description = "Ability to purge a role from the system")
    // public static final String PURGE_ROLE = "perm_purge_role";

    @PermissionAnnotation(id = "90433DD2-A64B-489b-B0B0-1634528A6705", description = "Ability to add students")
    public static final String ADD_STUDENT = "perm_add_student";

    @PermissionAnnotation(id = "5C9C6C83-725F-43de-9349-C599136C0676", description = "Ability to edit student")
    public static final String EDIT_STUDENT = "perm_edit_student";

    @PermissionAnnotation(id = "C7991039-FC02-44e7-98CD-AAF63A49CE41", description = "Ability to view student")
    public static final String VIEW_STUDENT = "perm_view_student";

    @PermissionAnnotation(id = "3F479FA6-6270-4e4d-9AC6-3AEB43CEB458", description = "Ability to delete students")
    public static final String DELETE_STUDENT = "perm_delete_student";

    // @PermissionAnnotation(id = "D82DC8B3-2E29-4790-9ABD-8D0832074000",
    // description = "Ability to purge a student")
    // public static final String PURGE_STUDENT = "perm_purge_student";

    //student result, misplacedStudentResult (this includes add and update)
    @PermissionAnnotation(id = "8AB69146-874C-46bd-AA67-ABC873DB83D2", description = "Ability to add student results")
    public static final String ADD_STUDENT_RESULT = "perm_add_student_result";

    /**
     * permission to edit a student result, misplacedStudentResult
     */
    @PermissionAnnotation(id = "D3F55395-3D7D-4366-ABB8-BA8EDD15AB1D", description = "Ability to edit student result")
    public static final String EDIT_STUDENT_RESULT = "perm_edit_student_result";

    //student result, misplacedStudentResult
    @PermissionAnnotation(id = "A50A43B8-E821-460c-9B25-DD42156726EC", description = "Ability to view student result")
    public static final String VIEW_STUDENT_RESULT = "perm_view_student_result";

    //delete a student result, misplacedStudentResult
    @PermissionAnnotation(id = "1CEC9E44-9D82-4d66-8B9C-C4AAAAB799AA", description = "Ability to delete student results")
    public static final String DELETE_STUDENT_RESULT = "perm_delete_student_result";

    // @PermissionAnnotation(id = "145870E5-AF80-46b6-AAC3-91ECAA2BAF41",
    // description = "Ability to purge a student result")
    // public static final String PURGE_STUDENT_RESULT =
    // "perm_purge_student_result";

    @PermissionAnnotation(id = "55371A7E-33CD-48f3-BD04-0D119223AFD2", description = "Ability to add complaints")
    public static final String ADD_COMPLAINT = "perm_add_compalaint";

    @PermissionAnnotation(id = "A3B26808-0FE5-4159-BC14-244B3A3836B1", description = "Ability to edit complaints")
    public static final String EDIT_COMPLAINT = "perm_edit_complaint";

    @PermissionAnnotation(id = "1B78D8F8-AFE2-4363-A1AD-166EF3180DC3", description = "Ability to view complaint")
    public static final String VIEW_COMPLAINT = "perm_view_complaint";

    @PermissionAnnotation(id = "315E3E8A-E241-4165-854E-6C9DD97E136C", description = "Ability to delete complaints")
    public static final String DELETE_COMPLAINT = "perm_delete_complaint";

    // @PermissionAnnotation(id = "F28D16CC-F501-4e76-9C90-F0DCD357B893",
    // description = "Ability to purge a complaint")
    // public static final String PURGE_COMPLAINT = "perm_purge_complaint";

    @PermissionAnnotation(id = "7286141F-F05C-4217-B6FA-D24126A95C1C", description = "Ability to add complaint events")
    public static final String ADD_EVENT = "perm_add_complaint_event";

    @PermissionAnnotation(id = "C0B42B24-CDE8-4969-AF67-DD1784871C9B", description = "Ability to edit complaint events")
    public static final String EDIT_EVENT = "perm_edit_complaint_event";

    @PermissionAnnotation(id = "91C9BA42-B869-4c1f-B887-3F074B3A216B", description = "Ability to view complaint event")
    public static final String VIEW_EVENT = "perm_view_complaint_event";

    @PermissionAnnotation(id = "B7CC6E4B-E395-480b-8553-8FBFF722ADD3", description = "Ability to delete complaint events")
    public static final String DELETE_EVENT = "perm_delete_complaint_event";

    @PermissionAnnotation(id = "98357FA0-E5EE-48e3-8F16-3110158FF4F3", description = "Ability to add  payments")
    public static final String ADD_PAYMENTS = "add_payment";

    @PermissionAnnotation(id = "FA5621F2-10A2-4ef0-AB58-C024CE8BF7BD", description = "Ability to edit  payments")
    public static final String EDIT_PAYMENTS = "edit_payment";

    @PermissionAnnotation(id = "B1A44802-610A-41bc-BA87-9AD09AF7B493", description = "Ability to view  payment")
    public static final String VIEW_PAYMENTS = "view_payment";

    @PermissionAnnotation(id = "DE556237-B7A9-47fc-905A-03D69FC04DA1", description = "Ability to delete  payments")
    public static final String DELETE_PAYMENTS = "delete_payment";

    @PermissionAnnotation(id = "0D958F7A-C70F-4574-9E99-947916D0D41B", description = "Ability to view fees structure, yearOfStudy, Semester")
    public static final String VIEW_FEES_STRUCTURE_DETAILS = "perm_view_fees_structure_details";

    //edit a fees structure, YearOfStudy, Semester and fees
    @PermissionAnnotation(id = "B92A51CE-904E-4cb7-BBE9-9156933EEAB4", description = "Ability to edit fees structure, yearOfStudy, Semester")
    public static final String EDIT_FEES_STRUCTURE_DETAILS = "perm_edit_fees_structure_details";

    @PermissionAnnotation(id = "D3629F10-3236-4831-A432-3560BFE77EB7", description = "Ability to add fees structure, yearOfStudy, Semester")
    public static final String ADD_FEES_STRUCTURE_DETAILS = "perm_add_fees_structure_details";

    @PermissionAnnotation(id = "A553084C-6DE9-44e0-A794-FA18601EC675", description = "Ability to delete fees structure, yearOfStudy, Semester")
    public static final String DELETE_FEES_STRUCTURE_DETAILS = "perm_delete_fees_structure_details";

    @PermissionAnnotation(id = "perm_manage_graduation", description = "Ability to add, edit, delete graduations")
    public static final String MANAGE_GRADUATION = "perm_manage_graduation";

    @PermissionAnnotation(id = "perm_view_graduation", description = "Ability to view graduations")
    public static final String VIEW_GRADUATION = "perm_view_graduation";

    @PermissionAnnotation(id = "perm_view_print_authorization", description = "Ability to view print authorization")
    public static final String VIEW_PRINT_AUTHORIZATION = "perm_view_print_authorization";

    @PermissionAnnotation(id = "perm_add_print_authorization", description = "Ability to add print authorization")
    public static final String ADD_PRINT_AUTHORIZATION = "perm_add_print_authorization";

    @PermissionAnnotation(id = "perm_delete_print_authorization", description = "Ability to delete print authorization")
    public static final String DELETE_PRINT_AUTHORIZATION = "perm_delete_print_authorization";

    @PermissionAnnotation(id = "perm_print", description = "Ability to print transcripts and certificates")
    public static final String CAN_PRINT_TRANSCRIPTS_AND_CERTIFICATES = "perm_print_transcripts_&_certificates";

    @PermissionAnnotation(id = "perm_manage_timetables", description = "Ability to add/edit/delete activities")
    public static final String PERM_MANAGE_TIMETABLES = "perm_manage_settings";

    @PermissionAnnotation(id = "perm_view_lecturercourseunits", description = "View Lecturer courseunits")
    public static final String PERM_VIEW_LECTURER_COURSEUNITS = "perm_manage_settings";


}
