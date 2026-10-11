package com.ali.library_management_system.controller;

import com.ali.library_management_system.model.Loan;
import com.ali.library_management_system.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller exposing the borrow/return workflow and loan lookups under /api/loans.
 */
@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping("/borrow")
    @ResponseStatus(HttpStatus.CREATED)
    public Loan borrowBook(@RequestParam Long userId, @RequestParam Long bookId) {
        return loanService.borrowBook(userId, bookId);
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<Loan> returnBook(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.returnBook(id));
    }

    // currently open loans (system-wide)
    @GetMapping
    public List<Loan> getActiveLoans() {
        return loanService.getActiveLoans();
    }

    // full loan history for one user
    @GetMapping("/user/{userId}")
    public List<Loan> getLoansByUser(@PathVariable Long userId) {
        return loanService.getLoansByUser(userId);
    }
}
