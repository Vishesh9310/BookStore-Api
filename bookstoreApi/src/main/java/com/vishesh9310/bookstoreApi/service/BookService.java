package com.vishesh9310.bookstoreApi.service;

import com.vishesh9310.bookstoreApi.entity.Author;
import com.vishesh9310.bookstoreApi.entity.Book;
import com.vishesh9310.bookstoreApi.repository.AuthorRepository;
import com.vishesh9310.bookstoreApi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public void saveEntry(Book book, String authorName){
        Author author = authorRepository.findByName(authorName);
        if (author != null) {
            book.setAuthor(author);
            bookRepository.save(book);
        } else {
            throw new RuntimeException("Author not found");
        }
    }

    public void saveEntry(Book book){
        bookRepository.save(book);
    }

    public Optional<Book> findById(Long id){
        return bookRepository.findById(id);
    }

    public Book findByTitle(String bookName){
        return bookRepository.findByTitle(bookName);
    }

    public void delete(Book book){
        bookRepository.delete(book);
    }

    public void deleteByName(String name){
        Book book = bookRepository.findByTitle(name);
        if (book != null) {
            bookRepository.delete(book);
        }
    }
}
