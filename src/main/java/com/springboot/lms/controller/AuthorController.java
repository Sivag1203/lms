package com.springboot.lms.controller;

import com.springboot.lms.model.Author;
import com.springboot.lms.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    // Add Author
    @PostMapping("/add")
    public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        Author saved = authorService.addAuthor(author);
        return ResponseEntity.ok(saved);
    }

    // Get All Authors
    @GetMapping("/all")
    public ResponseEntity<List<Author>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    // Get Author using logged-in user's email/username
    @GetMapping("/get-one")
    public Author getAuthorByPrincipal(Principal principal) {
        String username = principal.getName(); // Logged-in username/email
        return authorService.getAuthorByUsername(username);
    }

    // Update Author using logged-in user's identity
    @PutMapping("/update")
    public Author updateAuthor(@RequestBody Author updatedAuthor, Principal principal) {
        String username = principal.getName();
        return authorService.updateAuthorByUsername(username, updatedAuthor);
    }

    // Delete Author using logged-in user
    @DeleteMapping("/delete")
    public String deleteAuthor(Principal principal) {
        String username = principal.getName();
        boolean deleted = authorService.deleteAuthorByUsername(username);
        return deleted ? "Author deleted successfully." : "Author not found.";
    }

    @PostMapping("/upload/profile-pic")
    public String uploadProfilePic(Principal principal, @RequestParam("file")MultipartFile file){
        String username = principal.getName();
        return file.getOriginalFilename() + file.getSize();
    }

}
