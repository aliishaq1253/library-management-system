package com.ali.library_management_system.exception;

/**
 * Thrown when a book is requested (by id) but does not exist in the database.
 * Extends RuntimeException so it is unchecked - callers (service layer) don't need
 * to declare "throws", and it propagates up to the GlobalExceptionHandler automatically.
 */
public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(Long id) {
        super("Book not found with id: " + id);
    }
}
