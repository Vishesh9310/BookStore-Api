package com.vishesh9310.bookstoreApi.repository;

import com.vishesh9310.bookstoreApi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByTitle(String name);
}
