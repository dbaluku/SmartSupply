package org.smartsupply.api.security.service;

import org.smartsupply.api.security.RmsUserDetails;
import org.smartsupply.model.admin.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Interface for providing a MohrUserDetails services
 * 
 * @author ctumwebaze
 * 
 */
public interface RmsUserDetailsService extends UserDetailsService {

    /**
     * gets a MohrUserDetails for a given user
     * 
     * @param user
     * @return
     */
    RmsUserDetails getUserDetailsForUser(User user) throws Exception;
}
