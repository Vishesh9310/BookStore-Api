package com.vishesh9310.bookstoreApi.controller;

import com.vishesh9310.bookstoreApi.entity.Author;
import com.vishesh9310.bookstoreApi.entity.Book;
import com.vishesh9310.bookstoreApi.repository.BookRepository;
import com.vishesh9310.bookstoreApi.service.AuthorService;
import com.vishesh9310.bookstoreApi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @GetMapping("/{authorName}")
    public ResponseEntity<?> getAllBooksOfAuthor(@PathVariable String authorName){
        Author author = authorService.findByName(authorName);
        Set<Book> all = author.getBooks();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{authorName}")
    public ResponseEntity<Book> createBook(@RequestBody Book myEntry, @PathVariable String authorName) {
        try{
            bookService.saveEntry(myEntry,authorName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/title/{bookName}")
    public ResponseEntity<Book> updateBook(@PathVariable String bookName, @RequestBody Book book) {
        Book bookInDB = bookService.findByTitle(bookName);
        if(bookInDB != null){
            if (book.getTitle() != null && !book.getTitle().isEmpty()) {
                bookInDB.setTitle(book.getTitle());
            }
            if (book.getPrice() != null) {
                bookInDB.setPrice(book.getPrice());
            }
            bookService.saveEntry(bookInDB);
            return new ResponseEntity<>(bookInDB, HttpStatus.CREATED);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
    }

    @DeleteMapping("/{title}")
    public ResponseEntity<Void> deleteBook(@PathVariable String title) {
        Book book = bookService.findByTitle(title);
        if (book != null) {
            bookService.delete(book);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
