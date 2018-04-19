package org.smartsupply.api.security.service.impl;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import org.smartsupply.api.dao.BaseDAO;
import org.smartsupply.api.security.RmsUserDetails;
import org.smartsupply.api.security.service.RmsUserDetailsService;
//import org.smartsupply.api.service.course.CourseService;
//import org.smartsupply.api.utils.JsonUtil;
//import org.smartsupply.api.utils.UserCoursesAndCourseIntakeString;
import org.smartsupply.api.Constants;
import org.smartsupply.model.admin.Permission;
import org.smartsupply.model.admin.User;
import org.smartsupply.model.util.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.smartsupply.model.enums.UserStatus.ENABLED;

public class RmsUserDetailsServiceImpl implements RmsUserDetailsService {

    private BaseDAO<User> userDAO;

    @Autowired
    public void setUserDao(BaseDAO<User> daoToSet) {
        userDAO = daoToSet;
        userDAO.setClazz(User.class);
    }

    private BaseDAO<Permission> permissionDAO;

    @Autowired
    public void setPermissionDao(BaseDAO<Permission> daoToSet) {
        permissionDAO = daoToSet;
        permissionDAO.setClazz(Permission.class);
    }


    private Logger log = LoggerFactory.getLogger(RmsUserDetailsServiceImpl.class);

    // this method initialises the json string
    @Override
    public RmsUserDetails getUserDetailsForUser(User user) throws Exception {
        List<GrantedAuthority> authorities = getUserAuthorities(user);

        if (user.loadsTreeView()) {

        }

        return new RmsUserDetails(user, true, true, true, true, authorities);
    }




    protected List<GrantedAuthority> getUserAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<Permission> permissions = user.hasAdministrativePrivileges() ? permissionDAO.findAll() : user.findPermissions();
        for (Permission perm : permissions != null ? permissions : new ArrayList<Permission>()) {
            GrantedAuthority ga = new GrantedAuthorityImpl(perm.getName());
            authorities.add(ga);
        }
        return authorities;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        try {
            User user = userDAO.searchUniqueByPropertyEqual("username", username);



            // in-case its null, and the username repeat the search and make it case insensitive for student regNos
            if (user == null && username.length() > 2) {
                String firstTwoLetters = username.substring(0, 2);
                try {
                    Integer.parseInt(firstTwoLetters);
                    Search search = new Search();
                    // case-insensitive search for students only
                    search.addFilter(new Filter("username", username, Filter.OP_ILIKE));
                    user = userDAO.searchUnique(search);
                } catch (Exception e) {
                    // this is not a student
                }
            }

            if (user != null) {
                return getUserDetailsForUser(user);
            }
        } catch (Exception e) {
            log.error("LOGIN Error for " + username, e);
            throw new UsernameNotFoundException(e.getMessage(), e);
        }

        return null;
    }
}
