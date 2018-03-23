package com.example.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SentenceEntityRelation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SentenceEntityRelationPK sentenceRelationPK;

	@ManyToOne
	@JoinColumn(name = "idSentence", referencedColumnName = "idSentence", insertable = false, updatable = false)
	private Sentence sentence;

	@ManyToOne
	@JoinColumn(name = "idEntity", referencedColumnName = "idEntity", insertable = false, updatable = false)
	private com.example.entity.Entity entity;

	public SentenceEntityRelationPK getSentenceRelationPK() {
		return sentenceRelationPK;
	}

	public void setSentenceRelationPK(SentenceEntityRelationPK sentenceRelationPK) {
		this.sentenceRelationPK = sentenceRelationPK;
	}

	public Sentence getSentence() {
		return sentence;
	}

	public void setSentence(Sentence sentence) {
		this.sentence = sentence;
	}

	public com.example.entity.Entity getEntity() {
		return entity;
	}

	public void setEntity(com.example.entity.Entity entity) {
		this.entity = entity;
	}

}
