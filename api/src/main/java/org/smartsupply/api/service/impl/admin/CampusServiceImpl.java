//package org.smartsupply.api.service.impl.admin;
//
//import org.smartsupply.api.dao.BaseDAO;
//import org.smartsupply.api.service.baseclasses.BaseService;
//import org.smartsupply.api.service.impl.base.BaseServiceImpl;
//import org.smartsupply.model.admin.Campus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import static com.jarcommons.JarValidate.disallowBlank;
//import static org.smartsupply.api.utils.MyValidate.validateUniqueProperty;
//
//@Service("campusService")
//@Transactional
//public class CampusServiceImpl extends BaseServiceImpl<Campus> implements BaseService<Campus> {
//
//    @Autowired
//    public void setBaseDao(BaseDAO<Campus> daoToSet) {
//        super.baseDAO = daoToSet;
//        super.baseDAO.setClazz(Campus.class);
//    }
//
//    @Override
//    @Transactional(propagation = Propagation.REQUIRED)
//    public void validate(Campus campus) throws Exception {
//        disallowBlank(campus.getName(), "Campus Name");
//        disallowBlank(campus.getAbbreviation(), "Abbreviation");
//
//        Campus existingCampus = getByField("name", campus.getName());
//        validateUniqueProperty(existingCampus, campus, "Campus", "Name", campus.getName());
//
//        existingCampus = getByField("abbreviation", campus.getAbbreviation());
//        validateUniqueProperty(existingCampus, campus, "Campus", "abbreviation", campus.getAbbreviation());
//    }
//}
