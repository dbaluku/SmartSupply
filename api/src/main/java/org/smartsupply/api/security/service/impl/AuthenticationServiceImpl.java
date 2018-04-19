package org.smartsupply.api.security.service.impl;

import org.apache.commons.lang.StringUtils;
import org.smartsupply.api.dao.BaseDAO;
import org.smartsupply.api.security.RmsUserDetails;
import org.smartsupply.api.security.service.AuthenticationService;
import org.smartsupply.api.security.service.RmsUserDetailsService;
import org.smartsupply.api.security.util.RmsSecurityUtil;
import org.smartsupply.model.admin.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {

    private BaseDAO<User> userDAO;

    @Autowired
    public void setUserDao(BaseDAO<User> daoToSet) {
        userDAO = daoToSet;
        userDAO.setClazz(User.class);
    }

    @Autowired
    private RmsUserDetailsService rmsUserDetailsService;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public User authenticate(String username, String password, boolean attachUserToSecurityContext) throws Exception {
        User user = null;
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
            user = userDAO.searchUniqueByPropertyEqual("username", username);

            if (user != null && isValidUserPassword(user, password)) {

                if (attachUserToSecurityContext) {
                    RmsUserDetails userDetails = rmsUserDetailsService.getUserDetailsForUser(user);
                    if (userDetails != null) {
                        RmsSecurityUtil.setSecurityContext(userDetails);
                    }
                } else {
                    return user;
                }
            } else {
                log.error("Access denied for " + user.getUsername());
                throw new AccessDeniedException("password and username mismatch.");
            }
        }

        return user;
    }

    @Override
    public Boolean isValidUserPassword(User user, String password) {

        if (user == null || StringUtils.isBlank(user.getSalt())) {
            return false;
        }

        if (StringUtils.isBlank(password)) {
            password = "-1";
        }

        boolean passwordEqual = passwordEqual(user, password);

        if (!passwordEqual) {
            // check if this is a student trying to login. If password
            // fails, change it to "-1"
            String firstTwoLetters = user.getUsername().substring(0, 2);
            try {
                Integer.parseInt(firstTwoLetters);
                passwordEqual = passwordEqual(user, "-1");
            } catch (Exception e) {
                // this is not a student
            }
        }

        return passwordEqual;
    }

    public static boolean passwordEqual(User user, String password) {
        String hashedPassword = RmsSecurityUtil.encodeString(password + user.getSalt());
        if (hashedPassword.equals(user.getPassword())) {
            return true;
        } else {
            // try legacy method for backward compatibility
            hashedPassword = RmsSecurityUtil.encodeString2(password + user.getSalt());
            if (hashedPassword.equals(user.getPassword())) {
                return true;
            }
        }

        return false;
    }

    public RmsUserDetailsService getRmsUserDetailsService() {
        return rmsUserDetailsService;
    }

    public void setRmsUserDetailsService(RmsUserDetailsService rmsUserDetailsService) {
        this.rmsUserDetailsService = rmsUserDetailsService;
    }
}
