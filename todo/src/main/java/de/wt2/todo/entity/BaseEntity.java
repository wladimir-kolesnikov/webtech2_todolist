package de.wt2.todo.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/*
 * Abstrakte Klasse für Entities. Jede Entity muss diese Eigenschaften erfüllen
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 8554237959965022491L;
	
	// Jede Entity hat eine ID, welche unique ist
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	public BaseEntity() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}