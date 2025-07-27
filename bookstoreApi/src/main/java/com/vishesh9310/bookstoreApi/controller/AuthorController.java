package com.vishesh9310.bookstoreApi.controller;

import com.vishesh9310.bookstoreApi.entity.Author;
import com.vishesh9310.bookstoreApi.service.AuthorService;
import com.vishesh9310.bookstoreApi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @GetMapping("/{authorName}")
    public ResponseEntity<Author> getByName(@PathVariable String authorName){
        Author author = authorService.findByName(authorName);
        if(author != null){
            return new ResponseEntity<>(author, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> saveEntry(@RequestBody Author author){
        authorService.saveEntry(author);
        return new ResponseEntity<>(author,HttpStatus.OK);
    }

    @PutMapping("/{authorName}")
    public ResponseEntity<?> updateAuthorByName(@PathVariable String authorName, @RequestBody Author author){
        Author old = authorService.findByName(authorName);
        if(old != null){
            old.setName(author.getName());
            old.setPassword(author.getPassword());
            authorService.saveEntry(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{authorName}")
    public ResponseEntity<?> deleteByAuthorName(@PathVariable String authorName){
        Author author = authorService.findByName(authorName);
        if (author == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        authorService.deleteByName(authorName);
        return new ResponseEntity<>(author,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        return authorService.findById(id)
                .map(author -> {
                    authorService.delete(author);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
