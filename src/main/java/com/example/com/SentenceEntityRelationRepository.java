package com.example.com;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.example.entity.SentenceEntityRelation;




@Repository
@RepositoryRestResource

public interface SentenceEntityRelationRepository extends JpaRepository<SentenceEntityRelation, Serializable> {

//	@Query(value ="SELECT sr FROM SentenceEntityRelation where MAX(sr.idEntity.)")
//	public SentenceEntityRelation findSimilarSentence(@Param("entities") LinkedHashMap<List<com.example.entity.Entity>, List<Integer>> userEntities)
//	
}
