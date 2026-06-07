package org.example.banking.repository;

import org.example.banking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

// This is a repository interface for the Account entity. It extends JpaRepository, which provides basic CRUD operations for the Account entity. Additionally, it defines custom query methods to find an account by the associated user's username and to find an account by its account number. The @Query annotation is used to define a custom JPQL query for finding an account by username, while the method name convention is used for finding an account by account number.
public interface AccountRepository extends JpaRepository<Account, UUID> {

    @Query("select a from Account a join a.user u where u.userName = :username")
    Account findByUserName(@Param("username") String userName);

    Optional<Account> findByAccountNumber(String accountNumber);
}
