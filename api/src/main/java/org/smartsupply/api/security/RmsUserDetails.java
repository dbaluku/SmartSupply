package org.smartsupply.api.security;

import org.smartsupply.model.admin.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author ctumwebaze
 * 
 */
public class RmsUserDetails extends
		org.springframework.security.core.userdetails.User {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8882301928519997148L;

	private User systemUser;

	/**
	 * constructor with initial specified values
	 * 
	 * @param username
	 * @param password
	 * @param enabled
	 * @param accountNonExpired
	 * @param credentialsNonExpired
	 * @param accountNonLocked
	 * @param authorities
	 */
	public RmsUserDetails(User user, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(user.getUsername(), user.getPassword(), enabled,
				accountNonExpired, credentialsNonExpired, accountNonLocked,
				authorities);

		systemUser = user;
	}

	/**
	 * gets the salt of the user
	 * 
	 * @return
	 */
	public String getSalt() {
		return systemUser.getSalt();
	}

	/**
	 * gets the System user
	 * 
	 * @return
	 */
	public User getSystemUser() {
		return systemUser;
	}
}
