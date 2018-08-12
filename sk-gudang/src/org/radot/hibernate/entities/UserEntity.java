package org.radot.hibernate.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.radot.enums.UserType;

@Entity(name="User")
@Table(name="sk_user")
public class UserEntity extends BaseEntity {


	/**
	 * 
	 */
	private static final long serialVersionUID = -499986483169929066L;

	@Column(name="user_name", length=128, unique=true, nullable=false, updatable=false)
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Convert(converter=UserType.Converter.class)
	@Column(name="user_type", length=20)
	private UserType type;

	@Column(name="user_pass", length=128)
	private String password;

	@Column(name="last_login")
	private Date lastLogin;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public UserType getType() {
		return type;
	}

	public void setType(final UserType type) {
		this.type = type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(final Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	
}
