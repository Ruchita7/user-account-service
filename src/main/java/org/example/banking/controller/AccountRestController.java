package org.example.banking.controller;

import org.example.banking.dto.AccountDTO;
import org.example.banking.dto.AmountRequest;
import org.example.banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * AccountRestController is a REST controller that handles HTTP requests related to account operations.
 * It provides endpoints for creating, retrieving, updating, and deleting accounts.
 */
@RestController
@RequestMapping("/account")
public class AccountRestController {

    private AccountService accountService;

    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Retrieves a list of all accounts.
     *
     * @return ResponseEntity containing the list of AccountDTOs and HTTP status OK.
     */
    @GetMapping
    public ResponseEntity<List<AccountDTO>> findAll() {
        List<AccountDTO> accountList = accountService.findAllAccounts();
        return new ResponseEntity<>(accountList, HttpStatus.OK);
    }

    /**
     * Retrieves an account by its unique identifier.
     *
     * @param id the UUID of the account to retrieve.
     * @return ResponseEntity containing the AccountDTO and HTTP status OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> findById(@PathVariable UUID id) {
        AccountDTO account = accountService.findByAccount(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    /*  *
     * Retrieves an account by the associated user's name.
     *
     * @param userName the name of the user associated with the account to retrieve.
     * @return ResponseEntity containing the AccountDTO and HTTP status OK.
     */
    @GetMapping("/user")
    public ResponseEntity<AccountDTO> findByUserName(@RequestParam(value = "userName") String userName) {
        AccountDTO accountDTO = accountService.findByUserName(userName);
        return new ResponseEntity<>(accountDTO, HttpStatus.OK);
    }

    /* *
     * Retrieves an account by its account number.
     *
     * @param accountNumber the account number of the account to retrieve.
     * @return ResponseEntity containing the AccountDTO and HTTP status OK.
     */
    @GetMapping("/accountNo")
    public ResponseEntity<AccountDTO> findByAccountNumber(@RequestParam(value = "accountNumber") String accountNumber) {
        AccountDTO accountDTO = null;
        try {
            accountDTO = accountService.findByAccountNumber(accountNumber);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(accountDTO, HttpStatus.OK);
    }

    @GetMapping("/balance")
    public ResponseEntity<BigDecimal> findBalanceByAccountNumber(@RequestParam(value = "accountNumber") String accountNumber) {
        BigDecimal balance = null;
        try {
            balance = accountService.findBalanceByAccountNumber(accountNumber);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }

    /* *
     * Creates a new account.
     *
     * @param accountDTO the AccountDTO containing the details of the account to create.
     * @return ResponseEntity containing the created AccountDTO and HTTP status OK.
     */
    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO) {
        AccountDTO result = accountService.createAccount(accountDTO);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{accountNumber}/debit")
    public ResponseEntity<AccountDTO> debitAccount(@PathVariable String accountNumber,
                                                   @RequestBody AmountRequest request) {
        AccountDTO result = accountService.debitAccount(accountNumber, request.getAmount());
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{accountNumber}/credit")
    public ResponseEntity<AccountDTO> creditAccount(@PathVariable String accountNumber,
                                                   @RequestBody AmountRequest request) {
        AccountDTO result = accountService.credit(accountNumber, request.getAmount());
        return ResponseEntity.ok(result);
    }

    /* *
     * Updates an existing account.
     *
     * @param id the UUID of the account to update.
     * @param accountDTO the AccountDTO containing the updated details of the account.
     * @return ResponseEntity containing the updated AccountDTO and HTTP status OK.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<AccountDTO> updateAccount(@PathVariable UUID id, @RequestBody AccountDTO accountDTO) {
        AccountDTO accountResponse = null;
        try {
            accountResponse = accountService.updateAccount(id, accountDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(accountResponse, HttpStatus.OK);
    }

    /* *
     * Deletes an account by its unique identifier.
     *
     * @param id the UUID of the account to delete.
     * @return ResponseEntity with HTTP status NO_CONTENT if deletion is successful.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<AccountDTO> deleteAccount(@PathVariable UUID id) {
        try {
            accountService.deleteAccount(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
