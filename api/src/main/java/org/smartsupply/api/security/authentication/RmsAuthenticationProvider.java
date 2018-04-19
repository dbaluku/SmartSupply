package org.smartsupply.api.security.authentication;

import org.smartsupply.api.security.RmsUserDetails;
import org.smartsupply.api.security.service.AuthenticationService;
import org.smartsupply.api.security.service.RmsUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Extends the spring framework {@link AuthenticationProvider} interface so as
 * to provide a custom authentication mechanism
 * 
 * @author ctumwebaze
 * 
 */
public class RmsAuthenticationProvider implements AuthenticationProvider {

	private RmsUserDetailsService userDetailsService;

	@Autowired
	private AuthenticationService authenticationService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UserDetails userDetails = getUserDetailsService().loadUserByUsername((String) authentication.getPrincipal());
		if (userDetails != null) {
			if (authenticationService.isValidUserPassword(
					((RmsUserDetails) userDetails).getSystemUser(), (String) authentication.getCredentials())) {
				return new UsernamePasswordAuthenticationToken(userDetails, authentication.getCredentials(), userDetails.getAuthorities());
			} else {
				throw new AuthenticationServiceException("password don't match");
			}
		} else {
			throw new AuthenticationCredentialsNotFoundException("");
		}
	}

	@Override
	public boolean supports(Class<? extends Object> supportedClass) {
		if (supportedClass.getName().equalsIgnoreCase(
						"org.springframework.security.authentication.UsernamePasswordAuthenticationToken")) {
			return true;
		}

		return false;
	}

	public RmsUserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	@Autowired
	public void setUserDetailsService(RmsUserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	public AuthenticationService getAuthenticationService() {
		return authenticationService;
	}

	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}
}
