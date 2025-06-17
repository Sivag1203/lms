package com.springboot.lms.repository;

import com.springboot.lms.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    @Query("SELECT a FROM Author a WHERE a.user.username = ?1")
    Optional<Author> findByUsername(String username);

    @Query("SELECT a FROM Author a WHERE a.user.username = ?1")
    Author getByUsername(String username);
}
