/**
 *
 */
package org.smartsupply.web.controllers;

import org.smartsupply.web.WebConstants;
import org.smartsupply.web.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("error")
public class ErrorController {

    @RequestMapping(method = RequestMethod.GET, value = "404")
    public ModelAndView handler404(ModelMap modelMap) {
        WebUtils.addErrorMessage(modelMap, "This Path does not Exit on the system");
        return new ModelAndView("errorPage", modelMap);

    }


    @RequestMapping(method = RequestMethod.GET, value = "405")
    public ModelAndView handler405(ModelMap modelMap) {
        WebUtils.addErrorMessage(modelMap, "This Path requires post-data to process!. " + "HTTP ERROR: 405, Request method 'GET' not supported");
        return new ModelAndView("errorPage", modelMap);

    }

    @RequestMapping(method = RequestMethod.GET, value = "accessDenied")
    public ModelAndView handlerAccessDenied(ModelMap modelmap) {
        modelmap.put(WebConstants.ERROR_MESSAGE, "You don't have sufficient rights to access this resource.");
        return new ModelAndView("errorPage", modelmap);
    }


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView systemError(ModelMap modelmap) {
        if (!modelmap.containsAttribute(WebConstants.ERROR_MESSAGE))
            modelmap.put(WebConstants.ERROR_MESSAGE, "System encountered an error.");
        return new ModelAndView("errorPage", modelmap);
    }
}
