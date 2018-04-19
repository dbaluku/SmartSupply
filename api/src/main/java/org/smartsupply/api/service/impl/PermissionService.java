package org.smartsupply.api.service.impl;

import org.smartsupply.api.dao.BaseDAO;
import org.smartsupply.api.service.baseclasses.BaseService;
import org.smartsupply.api.service.impl.base.BaseServiceImpl;
import org.smartsupply.model.admin.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("permissionService")
@Transactional
public class PermissionService extends BaseServiceImpl<Permission> implements BaseService<Permission> {

    @Autowired
    public void setBaseDao(BaseDAO<Permission> daoToSet) {
        super.baseDAO = daoToSet;
        super.baseDAO.setClazz(Permission.class);
    }
}
