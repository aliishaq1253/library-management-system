package com.ali.library_management_system.service;

import com.ali.library_management_system.exception.BookNotAvailableException;
import com.ali.library_management_system.exception.BookNotFoundException;
import com.ali.library_management_system.exception.UserNotFoundException;
import com.ali.library_management_system.model.Book;
import com.ali.library_management_system.model.Loan;
import com.ali.library_management_system.model.User;
import com.ali.library_management_system.repository.BookRepository;
import com.ali.library_management_system.repository.LoanRepository;
import com.ali.library_management_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Business logic for borrowing and returning books.
 * This is the one place where Book availability and Loan records are kept in sync.
 */
@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public Loan borrowBook(Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        if (!book.isAvailable()) {
            throw new BookNotAvailableException(bookId);
        }

        // mark the book as borrowed before persisting the loan
        book.setAvailable(false);
        bookRepository.save(book);

        Loan loan = new Loan();
        loan.setUser(user);
        loan.setBook(book);
        loan.setLoanDate(LocalDate.now());
        loan.setReturned(false);

        return loanRepository.save(loan);
    }

    public Loan returnBook(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new NoSuchElementException("Loan not found with id: " + loanId));

        loan.setReturned(true);
        loan.setReturnDate(LocalDate.now());

        // make the book available again
        Book book = loan.getBook();
        book.setAvailable(true);
        bookRepository.save(book);

        return loanRepository.save(loan);
    }

    public List<Loan> getActiveLoans() {
        return loanRepository.findByReturnedFalse();
    }

    public List<Loan> getLoansByUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
        return loanRepository.findByUserId(userId);
    }
}
