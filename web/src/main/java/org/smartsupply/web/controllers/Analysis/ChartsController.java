package org.smartsupply.web.controllers.Analysis;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

public class ChartsController {

    @RequestMapping(value = "viewCharts", method = RequestMethod.GET)
    public ModelAndView showCharts(ModelMap modelMap) {
        return new ModelAndView("OrderCharts",modelMap);
    }
}
