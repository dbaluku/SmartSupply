package org.smartsupply.api.service.impl.admin;

import org.smartsupply.api.dao.BaseDAO;
import org.smartsupply.api.service.baseclasses.BaseService;
import org.smartsupply.api.service.impl.base.BaseServiceImpl;
import org.smartsupply.model.admin.Url;
import org.smartsupply.model.admin.UserType;
import org.smartsupply.model.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("urlService")
@Transactional
public class UrlService extends BaseServiceImpl<Url> implements BaseService<Url> {

    @Autowired
    public void setBaseDao(BaseDAO<Url> daoToSet) {
        super.baseDAO = daoToSet;
        super.baseDAO.setClazz(Url.class);
    }

    @Override
    public void validateDelete(String[] ids) throws Exception {
        List<Url> urls = all();
        if (numberOfCourseAdminUrls(urls) == numberOfCourseAdminUrlsBeingDeleted(urls, compress(ids))) {
            throw new ValidationException("Delete operation not allowed. User-type "
                    + UserType.MANAGER.getName() + " will have no access URL.");
        }
    }

    private String compress(String[] ids) {
        String compressed = "";
        for (String s : ids) {
            compressed += compressed == "" ? s : "," + s;
        }
        return compressed;
    }

    private int numberOfCourseAdminUrls(List<Url> urls) {
        int x = 0;
        for (Url t : urls) {
            if (t.getUserTypes().contains(UserType.MANAGER)) {
                x++;
            }
        }
        return x;
    }

    private int numberOfCourseAdminUrlsBeingDeleted(List<Url> urls, String ids) {
        int x = 0;
        for (Url t : urls) {
            if (t.getUserTypes().contains(UserType.MANAGER) && ids.contains(t.getId())) {
                x++;
            }
        }
        return x;
    }
}
