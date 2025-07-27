package com.vishesh9310.bookstoreApi.controller;

import com.vishesh9310.bookstoreApi.entity.Author;
import com.vishesh9310.bookstoreApi.entity.Book;
import com.vishesh9310.bookstoreApi.service.AuthorService;
import com.vishesh9310.bookstoreApi.service.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Set;

@RestController
@RequestMapping("/book")
@Tag(name = "Books", description = "Operations related to Books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;


    @GetMapping("/list")
    @Operation(summary = "List all books with filtering, pagination, and sorting")
    public ResponseEntity<Page<Book>> getBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) BigDecimal price
    ) {
        try {
            Sort.Direction direction = sort[1].equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
            Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort[0]));
            Page<Book> result = bookService.getBooksByFilter(title, price, pageable);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{authorName}")
    @Operation(summary = "Get all books by author name")
    public ResponseEntity<?> getAllBooksOfAuthor(@PathVariable String authorName) {
        Author author = authorService.findByName(authorName);
        if (author == null) {
            return new ResponseEntity<>("Author not found", HttpStatus.NOT_FOUND);
        }

        Set<Book> books = author.getBooks();
        if (books == null || books.isEmpty()) {
            return new ResponseEntity<>("No books found for this author", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping("/{authorName}")
    @Operation(summary = "Create Book for Author")
    public ResponseEntity<?> createBook(@RequestBody Book book, @PathVariable String authorName) {
        try {
            bookService.saveEntry(book, authorName);
            return new ResponseEntity<>(book, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/title/{bookName}")
    public ResponseEntity<?> updateBook(@PathVariable String bookName, @RequestBody Book book) {
        Book bookInDB = bookService.findByTitle(bookName);
        if (bookInDB != null) {
            if (book.getTitle() != null && !book.getTitle().isEmpty()) {
                bookInDB.setTitle(book.getTitle());
            }
            if (book.getPrice() != null) {
                bookInDB.setPrice(book.getPrice());
            }
            bookService.saveEntry(bookInDB);
            return new ResponseEntity<>(bookInDB, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
    }

    @DeleteMapping("/{title}")
    public ResponseEntity<?> deleteBook(@PathVariable String title) {
        Book book = bookService.findByTitle(title);
        if (book != null) {
            bookService.delete(book);
            return new ResponseEntity<>("Book deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
        }
    }
}
