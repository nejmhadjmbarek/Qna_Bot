package com.example.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class SentenceEntityRelationPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idSentence;
	private int idEntity;

	public int getIdSentence() {
		return idSentence;
	}

	public void setIdSentence(int idSentence) {
		this.idSentence = idSentence;
	}

	public int getIdEntity() {
		return idEntity;
	}

	public void setIdEntity(int idEntity) {
		this.idEntity = idEntity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idEntity;
		result = prime * result + idSentence;
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
		SentenceEntityRelationPK other = (SentenceEntityRelationPK) obj;
		if (idEntity != other.idEntity)
			return false;
		if (idSentence != other.idSentence)
			return false;
		return true;
	}

}
