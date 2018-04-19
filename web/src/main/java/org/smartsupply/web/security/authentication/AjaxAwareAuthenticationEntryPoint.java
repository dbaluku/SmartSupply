package org.smartsupply.web.security.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/**
 * Extends the {@link LoginUrlAuthenticationEntryPoint} and looks for the
 * "X-AjaxRequest" header in the request. If this X-AjaxRequest is found in the
 * request, a 601 error code is sent back to the client indicating that ajax
 * calls don't support form login and therefore the client should login using a
 * normal form login
 * 
 * @author ctumwebaze
 * 
 */
public class AjaxAwareAuthenticationEntryPoint extends
		LoginUrlAuthenticationEntryPoint {

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.web.authentication.
     * LoginUrlAuthenticationEntryPoint
     * #commence(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse,
     * org.springframework.security.core.AuthenticationException)
     */
    @Override
    public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
	if (request.getHeader("X-AjaxRequest") != null
				&& request.getHeader("X-AjaxRequest").equals("1")) {
	    response.sendError(601, "");
	} else {
	    super.commence(request, response, authException);
	}
    }
}
