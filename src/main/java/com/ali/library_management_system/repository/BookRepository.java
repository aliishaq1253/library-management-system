package com.ali.library_management_system.repository;

import com.ali.library_management_system.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Spring Data JPA repository for Book entities.
 * Extending JpaRepository gives us CRUD methods (findAll, findById, save, deleteById, ...)
 * for free, without writing any implementation code.
 */
public interface BookRepository extends JpaRepository<Book, Long> {

    // Spring Data derives the SQL query automatically from the method name
    // (equivalent to: SELECT * FROM book WHERE author = ?, case-insensitive)
    List<Book> findByAuthorContainingIgnoreCase(String author);
}
