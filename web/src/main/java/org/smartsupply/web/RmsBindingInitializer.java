package org.smartsupply.web;

import org.smartsupply.model.admin.*;
import org.smartsupply.model.admin.Branch;
import org.smartsupply.model.product.Product;
import org.smartsupply.model.product.Stock;
import org.smartsupply.model.product.StockProduct;
import org.smartsupply.web.propertyeditors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

// this is the Global Data binding initializers that attaches different property-editors for different entity classes
public class RmsBindingInitializer implements WebBindingInitializer {

//    @Autowired
//    private CoursePropertyEditor coursePropertyEditor;
//
//    @Autowired
//    private CourseUnitPropertyEditor courseUnitPropertyEditor;
//
//    @Autowired
//    private CourseIntakePropertyEditor courseIntakePropertyEditor;

    @Autowired
    private PermissionPropertyEditor permissionPropertyEditor;

    @Autowired
    private RolePropertyEditor rolePropertyEditor;



    @Autowired
    private DatePropertyEditor datePropertyEditor;

    @Autowired
    private UserPropertyEditor userPropertyEditor;

    @Autowired
    private JobTitlePropertyEditor jobTitlePropertyEditor;

    @Autowired
    private OrgUnitPropertyEditor orgUnitPropertyEditor;

    @Autowired
    private ProductPropertyEditor productPropertyEditor;

    @Autowired
    private StockProductPropertyEditor stockProductPropertyEditor;

    @Autowired
    private StockPropertyEditor stockPropertyEditor;



    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.web.bind.support.WebBindingInitializer#initBinder
     * (org.springframework.web.bind.WebDataBinder,
     * org.springframework.web.context.request.WebRequest)
     */
    @Override
    public void initBinder(WebDataBinder binder, WebRequest request) {

//        binder.registerCustomEditor(Course.class, coursePropertyEditor);
//        binder.registerCustomEditor(CourseUnit.class, courseUnitPropertyEditor);
//        binder.registerCustomEditor(CourseIntake.class, courseIntakePropertyEditor);
        binder.registerCustomEditor(Permission.class, permissionPropertyEditor);
        binder.registerCustomEditor(Role.class, rolePropertyEditor);


        binder.registerCustomEditor(Date.class, datePropertyEditor);


        binder.registerCustomEditor(User.class, userPropertyEditor);

        binder.registerCustomEditor(JobTitle.class, jobTitlePropertyEditor);


        binder.registerCustomEditor(Branch.class, orgUnitPropertyEditor);
        binder.registerCustomEditor(Stock.class,stockPropertyEditor);
        binder.registerCustomEditor(Product.class,productPropertyEditor);
        binder.registerCustomEditor(StockProduct.class,stockProductPropertyEditor);
        binder.registerCustomEditor(StockProduct.class,productPropertyEditor);
        binder.registerCustomEditor(Product.class,stockProductPropertyEditor);




//        binder.registerCustomEditor(Activity.class, activityPropertyEditor);
//        binder.registerCustomEditor(Activity.class, courseUnitPropertyEditor);
//        binder.registerCustomEditor(Activity.class, courseIntakePropertyEditor);
//        binder.registerCustomEditor(Activity.class, userPropertyEditor);
//        binder.registerCustomEditor(Activity.class, intakePropertyEditor);




    }
}
