package org.smartsupply.api.security.service;

import org.smartsupply.model.admin.User;

public interface AuthenticationService {

    User authenticate(String username, String password, boolean attachUserToSecurityContext) throws Exception;

    Boolean isValidUserPassword(User user, String loginPassword);
}
