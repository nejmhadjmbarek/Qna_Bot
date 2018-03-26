package com.example.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.example.entity.Sentence;

@Repository
@RepositoryRestResource
public interface SentenceRepository extends JpaRepository<Sentence, Serializable> {

	@Query("SELECT  idSentence FROM Sentence s where s.language=en")
	public List<Integer> getAllSentenceIDsEnglish();

	@Query("SELECT  idSentence FROM Sentence s where s.language=jp")
	public List<Integer> getAllSentenceIDsJapanese();
}
