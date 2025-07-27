package com.vishesh9310.bookstoreApi.repository;

import com.vishesh9310.bookstoreApi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByTitle(String name);

    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    Page<Book> findByPrice(BigDecimal price, Pageable pageable);
    Page<Book> findByTitleContainingIgnoreCaseAndPrice(String title, BigDecimal price, Pageable pageable);

}
