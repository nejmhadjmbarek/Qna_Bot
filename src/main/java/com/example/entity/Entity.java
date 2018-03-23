package com.example.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Entity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int idEntity;
	private float sentiment;
	private float salience;

	@OneToMany(mappedBy = "entity")
	@JsonIgnoreProperties({ "sentence", "entity" })
	private List<SentenceEntityRelation> sentenceEntityRelation;

	public int getIdEntity() {
		return idEntity;
	}

	public void setIdEntity(int idEntity) {
		this.idEntity = idEntity;
	}

	public float getSentiment() {
		return sentiment;
	}

	public void setSentiment(float sentiment) {
		this.sentiment = sentiment;
	}

	public float getSalience() {
		return salience;
	}

	public void setSalience(float salience) {
		this.salience = salience;
	}

	public List<SentenceEntityRelation> getSentenceEntityRelation() {
		return sentenceEntityRelation;
	}

	public void setSentenceEntityRelation(List<SentenceEntityRelation> sentenceEntityRelation) {
		this.sentenceEntityRelation = sentenceEntityRelation;
	}

}
