package org.smartsupply.api.service.impl.admin;

import org.smartsupply.api.service.admin.AdminService;
import org.smartsupply.api.service.SetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.smartsupply.model.enums.CourseType.MASTERS;
import static org.smartsupply.model.enums.CourseType.POST_GRADUATE_DIPLOMA;
import static org.smartsupply.model.enums.Setup.UTAMU;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private SetupService setupService;


    @Override
    public void getAllSalesMenDetails() throws Exception {
        int x =2;
    }
}
