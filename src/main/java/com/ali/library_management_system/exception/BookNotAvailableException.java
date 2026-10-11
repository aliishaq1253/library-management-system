package com.ali.library_management_system.exception;

/**
 * Thrown when a book is requested for borrowing but is currently on loan
 * (i.e. its "available" flag is already false).
 */
public class BookNotAvailableException extends RuntimeException {

    public BookNotAvailableException(Long bookId) {
        super("Book with id " + bookId + " is currently not available");
    }
}
