package de.wt2.todo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name="note")
@Table(name="note")

public class Note extends BaseEntity {

	private static final long serialVersionUID = 3713677712268341918L;
	
	@ManyToOne
	// Der Author
	private User author;
	
	// Die Überschrift
	private String headline;
	
	// Erstellt am
	private Date created;
	
	// Zuletzt editiert
	@Column(name="last_edited")
	private Date lastEdited;
	
	// Wann läuft die Note aus
	private Date due;
	
	
	@Enumerated(EnumType.STRING)
	/*
	 * Die Dringlichkeit des Notes (NORMAL, IMPORTANT, URGENT)
	 * "NORMAL" notes haben kein Ablaufdatum? 
	 * IMPORTANT und URGENT haben z.B. ein rotes Warndreieck neben der Darstellung?
	 */
	private Relevance relevance;

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getLastEdited() {
		return lastEdited;
	}

	public void setLastEdited(Date lastEdited) {
		this.lastEdited = lastEdited;
	}

	public Date getDue() {
		return due;
	}

	public void setDue(Date due) {
		this.due = due;
	}

	public Relevance getRelevance() {
		return relevance;
	}

	public void setRelevance(Relevance relevance) {
		this.relevance = relevance;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
