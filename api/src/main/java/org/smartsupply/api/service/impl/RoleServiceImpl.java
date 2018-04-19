package org.smartsupply.api.service.impl;

import com.jarcommons.JarValidate;
import org.smartsupply.api.dao.BaseDAO;
import org.smartsupply.api.service.baseclasses.BaseService;
import org.smartsupply.api.service.impl.base.BaseServiceImpl;
import org.smartsupply.api.utils.MyValidate;
import org.smartsupply.model.admin.Role;
import org.smartsupply.model.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service("roleService")
@Transactional
public class RoleServiceImpl extends BaseServiceImpl<Role> implements BaseService<Role> {

    @Autowired
    public void setBaseDao(BaseDAO<Role> daoToSet) {
        super.baseDAO = daoToSet;
        super.baseDAO.setClazz(Role.class);
    }

    @Override
    public void validate(Role role) throws Exception {
        JarValidate.disallowBlank(role.getName(), "Name");
        JarValidate.disallowBlank(role.getDescription(), "Description");
        MyValidate.uniqueStringFieldExists(role.getName(), "name", "Name", "Role", role, this);
    }

    @Override
    public void validateDelete(String[] ids) throws Exception {
        List<Role> roles = byIds(ids);
        for (Role role : roles) {
            if (role.isDefaultAdminRole()) {
                throw new Exception("Cannot delete default administration role");
            }
            if (role.getUsers() != null && role.getUsers().size() > 0) {
                throw new ValidationException("Role " + role.getName() + " can't be deleted because its assigned to Users");
            }
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Role getById(String id) {
        Role role = baseDAO.searchUniqueByPropertyEqual("id", id);
        if (role != null && role.getPermissions() != null)
            Collections.sort(role.getPermissions());
        return role;
    }
}
