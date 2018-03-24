package com.example.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@javax.persistence.Entity
public class BotInformation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idBotInformation;

	private String SentenceToSearch;

	public int getIdBotInformation() {
		return idBotInformation;
	}

	public void setIdBotInformation(int idBotInformation) {
		this.idBotInformation = idBotInformation;
	}

	public String getSentenceToSearch() {
		return SentenceToSearch;
	}

	public void setSentenceToSearch(String sentenceToSearch) {
		SentenceToSearch = sentenceToSearch;
	}

}
