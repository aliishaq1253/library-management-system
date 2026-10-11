package com.ali.library_management_system.exception;

/**
 * Thrown when a user is requested (by id) but does not exist in the database.
 * Analogous to BookNotFoundException.
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("User not found with id: " + id);
    }
}
