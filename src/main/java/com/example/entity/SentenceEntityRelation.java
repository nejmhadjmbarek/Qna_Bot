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
	private Entity entity;

}
