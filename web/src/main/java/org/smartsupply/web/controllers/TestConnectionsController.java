package org.smartsupply.web.controllers;

import org.smartsupply.web.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("connect")
public class TestConnectionsController {

    @Autowired
    private ApplicationController applicationController;



    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView connect(HttpServletRequest request, ModelMap modelMap) {
        return body(request, "blank", modelMap);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{name}")
    public ModelAndView connect2(HttpServletRequest request, @PathVariable("name") String name, ModelMap modelMap) {
        return body(request, name, modelMap);
    }

    public ModelAndView body(HttpServletRequest request, String name, ModelMap modelMap) {
        try {

        } catch (Exception e) {
            WebUtils.logExceptionAndAddErrorMessage(modelMap, e);
        }
        return applicationController.welcomeHandler(request, modelMap);
    }

//    public void loadStudentResults(ModelMap modelMap) throws Exception {
//        Student student = studentService.searchUniqueWithParams(new StudentSearchParams("05/U/1412", false));
//        Curriculum curriculum = student.getSubCourseIntake() != null ? student.getSubCourseIntake().getCurriculum()
//                : student.getCourseIntake().getCurriculum() != null ? student.getCourseIntake().getCurriculum() : null;
//
//        if (null == curriculum) {
//            WebUtils.addErrorMessage(modelMap, "No curriculum attached to "
//                    + (student.getLeafCourseIntake().getCourseCodeIntakeName()));
//        }

//        studentResultService.getUtilStudentResultByStudent(student, curriculum);
    }


