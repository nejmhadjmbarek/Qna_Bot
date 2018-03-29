package com.example.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Serializable> {
	
	
	@Query("SELECT idBook FROM Book b")
	public List<Integer> getAllBooksIDs();

}
