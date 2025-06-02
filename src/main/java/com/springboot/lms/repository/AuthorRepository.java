package com.springboot.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.lms.model.Author;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Optional<Author> findByUsername(String username);

    @Query("select a from Author a where a.user.username = ?1")
    Author getAuthorByUsername(String username);

}
