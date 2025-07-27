package com.vishesh9310.bookstoreApi.repository;

import com.vishesh9310.bookstoreApi.entity.Author;

import org.springframework.data.jpa.repository.JpaRepository;
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByName(String name);
}

