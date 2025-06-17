package com.springboot.lms.service;

import com.springboot.lms.model.Author;
import com.springboot.lms.model.User;
import com.springboot.lms.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    private UserService userService;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, UserService userService) {
        this.authorRepository = authorRepository;
        this.userService = userService;
    }


    // Create
    public Author addAuthor(Author author) {
        User user = author.getUser();
        user.setRole("AUTHOR");

        /* Fetch User from Author and add to DB */
        user = userService.signUp(author.getUser());

        /* Attach this user to author again */
        author.setUser(user);

        /* Activate Author - later let executive do this.. */
        author.setActive(true);

        /* Save Author in Db */
        return authorRepository.save(author);
    }

    // Read All
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorByUsername(String username) {
        return authorRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Author not found"));
    }

    public Author updateAuthorByUsername(String username, Author updatedAuthor) {
        return authorRepository.findByUsername(username).map(existingAuthor -> {
            existingAuthor.setName(updatedAuthor.getName());
            existingAuthor.setContact(updatedAuthor.getContact());
            existingAuthor.setWebsite(updatedAuthor.getWebsite());
            existingAuthor.setProfilePic(updatedAuthor.getProfilePic());
            return authorRepository.save(existingAuthor);
        }).orElseThrow(() -> new RuntimeException("Author not found"));
    }

    public boolean deleteAuthorByUsername(String username) {
        return authorRepository.findByUsername(username).map(author -> {
            authorRepository.delete(author);
            return true;
        }).orElse(false);
    }

}
