//package org.smartsupply.web.propertyeditors;
//
//import org.smartsupply.api.service.baseclasses.BaseQuickService;
//import org.smartsupply.model.search.curriculum.ActivitySearchParams;
//import org.smartsupply.model.timetables.Activity;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component("activityPropertyEditor")
//public class ActivityPropertyEditor extends GenericBasePropertyEditor<Activity>{
//
//    @Autowired
//    private BaseQuickService<Activity,ActivitySearchParams> activityService;
//
//    @Override
//    protected Activity getObject(String id) {
//        return activityService.getById(id);
//    }
//}
