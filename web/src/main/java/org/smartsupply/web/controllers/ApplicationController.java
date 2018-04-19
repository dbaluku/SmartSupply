package org.smartsupply.web.controllers;


import org.smartsupply.api.ConceptCategoryAnnotation;
import org.smartsupply.api.DefaultConceptCategories;
import org.smartsupply.api.security.PermissionConstants;
import org.smartsupply.api.security.util.RmsSecurityUtil;
import org.smartsupply.api.service.UserService;
import org.smartsupply.api.service.baseclasses.BaseService;
import org.smartsupply.api.utils.RmsUtil;
import org.smartsupply.model.admin.Permission;
import org.smartsupply.model.admin.Url;
import org.smartsupply.model.admin.User;
import org.smartsupply.model.admin.UserType;
import org.smartsupply.model.enums.UserStatus;
import org.smartsupply.model.exception.RmsSessionExpiredException;
import org.smartsupply.web.WebUtils;
import org.smartsupply.web.controllers.product.ProductController;
import org.smartsupply.web.controllers.settings.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static org.smartsupply.model.admin.UserType.MANAGER;

@Controller
public class ApplicationController {

    @Autowired
    private UserController userController;

    @Autowired
    private ProductController productController;


    @Autowired
    private BaseService<Url> urlService;


    @Autowired
    private ErrorController errorController;


    private static Logger logStatic = LoggerFactory.getLogger(ApplicationController.class);

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = {"/", "index.jsp"})
    public ModelAndView welcomeHandler(HttpServletRequest request, ModelMap modelMap) {

        try {
            User user = RmsSecurityUtil.getLoggedInUser();

            if (!user.hasAdministrativePrivileges() && !userTypeAllowedOnThisUrl(request, user.getUserType())) {
                return new ModelAndView("admin",modelMap);
            }

            if (user.hasAdministrativePrivileges() || user.getUserType().equals(MANAGER) || user.getUserType().equals(UserType.ACCOUNTANT)) {

//                StudentSearchParams params = new StudentSearchParams();
//                modelMap.put("studentSearchParams", params);
//                searchController.prepareSearchCommand(params, modelMap);
//                WebUtils.addContentHeader(modelMap, "Student Search");
//                String ipAddress = request.getHeader("X-FORWARDED-FOR");
//                if (ipAddress == null) {
//                    ipAddress = request.getRemoteAddr();
//                }
//                return courseIntakeController.viewAllCourseIntakes(modelMap);
//                //return new ModelAndView("adminStaff", modelMap);
//
//            } else if (user.getUserType().equals(UserType.STUDENT)) {
//                return studentController.studentViewProfileHandler(request, user.getId(), new ModelMap());}
//
//            else if (user.getUserType().equals(UserType.LECTURER)) {
//                return lecturerController.LecturerViewProfile(request,user.getId(), new ModelMap());
//
                return productController.view(modelMap);
               }
        } catch (Exception e) {
            log.error("Error", e);
            WebUtils.addErrorMessage(modelMap, e.getMessage());
        }
        return new ModelAndView("here",modelMap);
    }

    public ModelAndView returnHome(ModelMap modelMap) {

        try {
            User user = RmsSecurityUtil.getLoggedInUser();

            if (user.hasAdministrativePrivileges() || user.getUserType().equals(MANAGER)) {

                return new ModelAndView("adminStaff", modelMap);
            }
        } catch (Exception e) {
            log.error("Error", e);
        }
        return null;
    }

    @RequestMapping("/ServiceLogin")
    public String loginHandler() {
        return "login";
    }

    @RequestMapping("/ServiceLoginFailure")
    public ModelAndView loginFailureHander(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        if (!modelMap.containsAttribute("errorMessage")) {
            WebUtils.addErrorMessage(modelMap, "Incorrect username or password. (Note: Students use RegNo as username and StudentNo as password)");
        }

        return new ModelAndView("login", modelMap);
    }





    public User getLoggedInUser() throws Exception {
        User user = RmsSecurityUtil.getLoggedInUser();
        return user;
    }

//    public User getLoggedInUserFromDb() throws Exception {
//        User user = getLoggedInUser();
//        return user != null ? userService.getById(user.getId()) : null;
//    }

    public boolean loggedInUserIsAdmin() throws Exception {
        User user = getLoggedInUser();
        return user.hasAdministrativePrivileges();
    }

    public boolean loggedInUserIsType(UserType userType) throws Exception {
        User user = getLoggedInUser();
        return user.getUserType().equals(userType);
    }

    @RequestMapping("settings")
    public ModelAndView settingsHandler() {
        return userController.view(new ModelMap());
    }

    public Boolean userTypeAllowedOnThisUrl(HttpServletRequest request, UserType userType) {
        String baseUrl = WebUtils.getBaseUrl(request);
        List<Url> urls = urlService.all();
        for (Url t : urls) {
            if (t.getUrl().toLowerCase().contains(baseUrl.toLowerCase()) && t.getUserTypes().contains(userType))
                return true;
        }
        return false;
    }

    public ModelAndView viewLoginViewWithUrlError(ModelMap modelMap) {
        WebUtils.addErrorMessage(modelMap, "Sorry, you are not allowed to access the system on this URL");
        return new ModelAndView("login", modelMap);
    }

//    public void addConceptsToModelMap(ModelMap modelMap, String conceptCategoryName, String key) {
//        try {
//            ConceptCategoryAnnotation conceptAnnotation = RmsUtil
//                    .getConceptCategoryFieldAnnotation(DefaultConceptCategories.class, conceptCategoryName);
//
//            if (conceptAnnotation != null) {
//                modelMap.put(key, conceptService.getConceptsByCategoryAnnotation(conceptAnnotation));
//            }
//
//        } catch (Exception e) {
//            WebUtils.logExceptionAndAddErrorMessage(modelMap, e);
//        }
//    }

//    public boolean userHasRightsTo(CourseIntake courseIntake) {
//        try {
//            User loggedInUser = RmsSecurityUtil.getLoggedInUser();
//
//            if (loggedInUser.getUserCourseIntakesString().contains(courseIntake.getId()))
//                return true;
//        } catch (RmsSessionExpiredException e) {
//            log.error("Oops, session error");
//        }
//        return false;
//    }

    public boolean hasPermission(List<String> permissionStrings) throws Exception {
        User user = getLoggedInUser();
        return user.hasAdministrativePrivileges() ? true : hasPermission(user, permissionStrings);
    }

    private boolean hasPermission(User user, List<String> permissionStrings) throws Exception {
        List<Permission> permissions = user.findPermissions();
        boolean hasPermission = true;
        for (String s : permissionStrings) {
            if (!permissions.contains(PermissionConstants.getPermission(s))) {
                hasPermission = false;
            }
        }
        return hasPermission;
    }
}
