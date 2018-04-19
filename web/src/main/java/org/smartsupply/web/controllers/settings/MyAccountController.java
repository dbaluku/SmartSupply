package org.smartsupply.web.controllers.settings;

import org.smartsupply.api.security.service.impl.AuthenticationServiceImpl;
import org.smartsupply.api.security.util.RmsSecurityUtil;
import org.smartsupply.api.service.UserService;
import org.smartsupply.model.admin.User;
import org.smartsupply.model.exception.RmsSessionExpiredException;
import org.smartsupply.model.exception.ValidationException;
import org.smartsupply.web.WebConstants;
import org.smartsupply.web.WebUtils;
import org.smartsupply.web.controllers.ApplicationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("settings/myaccount")
public class MyAccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationController applicationController;

    @RequestMapping(method = RequestMethod.GET, value = "edit")
    public ModelAndView editMyAccountHandler(HttpServletRequest request, ModelMap modelMap) {
        User loggedInUser;
        try {
            loggedInUser = RmsSecurityUtil.getLoggedInUser();
            if (null != loggedInUser) {
                loggedInUser.setFirstName(loggedInUser.getFullName());
                modelMap.put("user", loggedInUser);
                modelMap.put(WebConstants.CONTENT_HEADER, "Edit my account: " + loggedInUser.getFullName());
                return new ModelAndView("myAccountForm", modelMap);
            }
        } catch (RmsSessionExpiredException e) {
            WebUtils.logExceptionAndAddErrorMessage(modelMap, e);
        }
        return applicationController.welcomeHandler(request, modelMap);
    }

    @RequestMapping(method = RequestMethod.POST, value = "save")
    public ModelAndView saveMyAccountHandler(HttpServletRequest request,
                                             @ModelAttribute("user") User user, ModelMap modelMap) {

        if (user != null) {
            try {

                User loggedInUser = userService.getById(RmsSecurityUtil.getLoggedInUser().getId());
                if (loggedInUser == null)
                    throw new Exception("Internal error, logged in user-account information not found");
                if (!AuthenticationServiceImpl.passwordEqual(loggedInUser, user.getCurrentPassword()))
                    throw new ValidationException("Current password mismatch");

                loggedInUser.setFirstName(user.getFirstName());
                loggedInUser.setLastName(user.getLastName());
                loggedInUser.setUsername(user.getUsername());
                loggedInUser.setClearTextPassword(user.getClearTextPassword());

                RmsSecurityUtil.prepUserCredentials(loggedInUser);
                userService.validate(loggedInUser);
                userService.save(loggedInUser);

                WebUtils.addSystemMessage(modelMap, "Your account details have been updated successfully");

            } catch (Exception e) {
                WebUtils.logExceptionAndAddErrorMessage(modelMap, e);
                return new ModelAndView("myAccountForm", modelMap);
            }
        }

        return applicationController.welcomeHandler(request, modelMap);
    }

}
