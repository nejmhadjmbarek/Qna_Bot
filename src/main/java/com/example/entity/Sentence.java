package com.example.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@javax.persistence.Entity
public class Sentence implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idSentence;
	private String sentence;
	private String language;

	@OneToMany(mappedBy = "sentence")
	@JsonIgnoreProperties({ "sentence", "entity" })
	private List<SentenceEntityRelation> sentenceEntityRelation;

	public int getIdSentence() {
		return idSentence;
	}

	public void setIdSentence(int idSentence) {
		this.idSentence = idSentence;
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public List<SentenceEntityRelation> getSentenceEntityRelation() {
		return sentenceEntityRelation;
	}

	public void setSentenceEntityRelation(List<SentenceEntityRelation> sentenceEntityRelation) {
		this.sentenceEntityRelation = sentenceEntityRelation;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
