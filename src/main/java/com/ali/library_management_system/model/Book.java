package com.ali.library_management_system.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JPA entity representing a single book in the library catalog.
 * Each field maps 1:1 to a column in the generated "book" table.
 */
@Entity
@Data // Lombok: generates getters, setters, toString, equals/hashCode
@NoArgsConstructor // required by JPA/Hibernate to instantiate entities via reflection
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title must not be blank")
    private String title;

    @NotBlank(message = "Author must not be blank")
    private String author;

    @NotBlank(message = "ISBN must not be blank")
    private String isbn;

    @Column(name = "publication_year")
    private int publicationYear;

    // true = book is on the shelf, false = currently borrowed
    private boolean available;
}
