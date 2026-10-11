package com.ali.library_management_system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * JPA entity tracking a single borrow transaction: which user borrowed which book,
 * and whether/when it has been returned.
 * Deliberately a one-way relationship (Loan -> Book, Loan -> User) with no back-reference
 * on Book/User, to keep things simple and avoid circular toString()/equals() calls from @Data.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate loanDate;

    // stays null until the book is actually returned
    private LocalDate returnDate;

    private boolean returned;
}
