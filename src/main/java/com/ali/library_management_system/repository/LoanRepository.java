package com.ali.library_management_system.repository;

import com.ali.library_management_system.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    // all loans of a given user that have not been returned yet
    List<Loan> findByUserIdAndReturnedFalse(Long userId);

    // every loan in the system that is still open, regardless of user
    List<Loan> findByReturnedFalse();

    // full loan history (returned + active) for a given user
    List<Loan> findByUserId(Long userId);
}
