package org.smartsupply.web.security.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.smartsupply.api.security.RmsUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * redirects the user based to a particular url based on the role of the user
 * i.e. if the user is the administrator, they will directed to the
 * administrative url and if the user is a staff member, they will be redirected
 * to the staff url
 * 
 * @author ctumwebaze
 * 
 */
public class RmsAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private String administratorTargetUrl;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.
	 * SavedRequestAwareAuthenticationSuccessHandler
	 * #onAuthenticationSuccess(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {

		if (authentication.getPrincipal() instanceof RmsUserDetails) {
			if (((RmsUserDetails) authentication.getPrincipal())
					.getSystemUser().hasAdministrativePrivileges()) {

				// TODO check request cache if urls match
				if (StringUtils.isNotBlank(getAdministratorTargetUrl())
						&& StringUtils.isNotEmpty(getAdministratorTargetUrl())) {

					super.setDefaultTargetUrl(getAdministratorTargetUrl());
					super.setAlwaysUseDefaultTargetUrl(true);
				}
			} else {
				super.setAlwaysUseDefaultTargetUrl(false);
				super.setDefaultTargetUrl("/");
			}
		}
		super.onAuthenticationSuccess(request, response, authentication);
	}

	/**
	 * gets the administrator url that this SuccessHandler will redirect to if
	 * the logged in user is an administrator
	 * 
	 * @return the administratorTargetUrl
	 */
	public String getAdministratorTargetUrl() {
		return administratorTargetUrl;
	}

	/**
	 * sets the administrator url that this SuccessHandler will redirect to if
	 * the logged in user is an administrator
	 * 
	 * @param administratorTargetUrl
	 *            the administratorTargetUrl to set
	 */
	public void setAdministratorTargetUrl(String administratorTargetUrl) {
		this.administratorTargetUrl = administratorTargetUrl;
	}

}
