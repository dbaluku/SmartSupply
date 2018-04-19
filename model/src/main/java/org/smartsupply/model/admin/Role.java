package org.smartsupply.model.admin;

import org.smartsupply.model.BaseData;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "smartsupply_roles")
public class Role extends BaseData implements Comparable<Role> {

	private static final long serialVersionUID = -419580141759765365L;

	public static final String DEFAULT_ADMIN_ROLE = "ROLE_ADMINISTRATOR";

	private String name;
	private String description;
	private List<Permission> permissions;
	private Set<User> users;



	public Role() {

	}



	public Role(String name, String description) {
		this.setName(name);
		this.setDescription(description);
	}

	public Role(String id, String name, String description) {
		this(name, description);
		this.setId(id);
	}

	@Column(name = "role_name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany
	@JoinTable(name = "role_permissions", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "roles", targetEntity = User.class)
	public Set<User> getUsers() {
		return users;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "description", nullable = false)
	public String getDescription() {
		return description;
	}

	@Transient
	public boolean isDefaultAdminRole() {
		return this.getName().equals(Role.DEFAULT_ADMIN_ROLE);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (this.getName() == null) {
			if (other.getName() == null)
				return false;
		} else if (!this.getName().equalsIgnoreCase(other.getName()))
			return false;

		return true;
	}

	@Override
	public int compareTo(Role o) {
		return this.getName().compareToIgnoreCase(o.getName());
	}


	public static void copy(Role dest, Role source) {
		dest.name = source.name;
		dest.description = source.description;
		dest.permissions = source.permissions;
		BaseData.copy(dest, source);
	}

	@Override
	public <T extends BaseData> void copyFrom(T source) {
		copy(this, (Role) source);
	}

}
