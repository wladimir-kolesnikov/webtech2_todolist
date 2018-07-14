package de.wt2.todo.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity(name="note")
@Table(name="note")
public class Note extends BaseEntity {

	private static final long serialVersionUID = 3713677712268341918L;
	
	@JsonProperty(access = Access.WRITE_ONLY)
    @ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="user_id")
    private User authorUser;	
	
	// Die Überschrift
	private String headline;
	
	// Erstellt am
	private LocalDate created;
	
	// Zuletzt editiert
	@Column(name="last_edited")
	private LocalDate lastEdited;
	
	// Wann läuft die Note aus
	private LocalDate due;
	
	private String content;
	
	@Enumerated(EnumType.STRING)
	/*
	 * Die Dringlichkeit des Notes (NORMAL, IMPORTANT, URGENT)
	 * "NORMAL" notes haben kein Ablaufdatum? 
	 * IMPORTANT und URGENT haben z.B. ein rotes Warndreieck neben der Darstellung?
	 */
	private Relevance relevance;

	public User getAuthorUser() {
		return authorUser;
	}

	public void setAuthorUser(User authorUser) {
		this.authorUser = authorUser;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public LocalDate getCreated() {
		return created;
	}

	public void setCreated(LocalDate created) {
		this.created = created;
	}

	public LocalDate getLastEdited() {
		return lastEdited;
	}

	public void setLastEdited(LocalDate lastEdited) {
		this.lastEdited = lastEdited;
	}

	public LocalDate getDue() {
		return due;
	}

	public void setDue(LocalDate due) {
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
