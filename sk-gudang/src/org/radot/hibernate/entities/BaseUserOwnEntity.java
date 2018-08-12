package org.radot.hibernate.entities;

import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass()
public class BaseUserOwnEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1374851049239688917L;

	@JoinColumn(name="owner_fk", updatable=false)
	@OneToOne()
	private UserEntity owner;

	public final UserEntity getOwner() {
		return owner;
	}

	public final void setOwner(final UserEntity owner) {
		this.owner = owner;
	}
	
}
