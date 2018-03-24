package com.example.bot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoadDataController {

	@RequestMapping(value = "/store/sentence")
	public String storeSentenceToDataBase() {

		List<String> sentenceStringList=new  ArrayList<>();
		sentenceStringList.add("The most important thing in communication is hearing what isn't said.");
		sentenceStringList.add("Listening (the first competence of leadership) is not a skill, it is a discipline. All you have to do is keep your mouth shut.\n" + 
				"");
		sentenceStringList.add("");
		sentenceStringList.add("");

		sentenceStringList.add("");

		
		
		return null;

	}

}
