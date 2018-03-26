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

	private String sentenceToSearch;

	private String languageBot = "japanese";;

	private String intentName;

	public int getIdBotInformation() {
		return idBotInformation;
	}

	public void setIdBotInformation(int idBotInformation) {
		this.idBotInformation = idBotInformation;
	}

	public String getSentenceToSearch() {
		return sentenceToSearch;
	}

	public void setSentenceToSearch(String sentenceToSearch) {
		this.sentenceToSearch = sentenceToSearch;
	}

	public String getLanguageBot() {
		return languageBot;
	}

	public void setLanguageBot(String languageBot) {
		this.languageBot = languageBot;
	}

	public String getIntentName() {
		return intentName;
	}

	public void setIntentName(String intentName) {
		this.intentName = intentName;
	}

}
