package com.vishesh9310.bookstoreApi.service;

import com.vishesh9310.bookstoreApi.entity.Author;
import com.vishesh9310.bookstoreApi.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAll(){
        return authorRepository.findAll();
    }

    public void saveEntry(Author author){
        authorRepository.save(author);
    }

    public void deleteByName(String name){
        Author author = authorRepository.findByName(name);
        if(author != null){
            authorRepository.deleteById(author.getId());
        }
    }

    public void delete(Author author){
        authorRepository.delete(author);
    }

    public Author findByName(String name){
        return authorRepository.findByName(name);
    }

    public Optional<Author> findById(Long id){
        return authorRepository.findById(id);
    }
}
