package org.example.banking.controller;

import org.example.banking.dto.AccountDTO;
import org.example.banking.dto.AmountRequest;
import org.example.banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Account Management", description = "APIs for managing application users account")
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
    @Operation(
            summary = "Get all accounts",
            description = "Provides full account details."
    )
    @ApiResponse(responseCode = "200", description = "Accounts successfully found")
    @ApiResponse(responseCode = "404", description = "Accounts not found")
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
    @Operation(
            summary = "Get account by ID",
            description = "Provides full account details based on the unique account database ID."
    )
    @ApiResponse(responseCode = "200", description = "Account successfully found")
    @ApiResponse(responseCode = "404", description = "Account not found")
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
    @Operation(
            summary = "Get account by username",
            description = "Provides full account details based on the unique username."
    )
    @ApiResponse(responseCode = "200", description = "Account successfully found")
    @ApiResponse(responseCode = "404", description = "Account not found")
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
    @Operation(
            summary = "Get account by account number",
            description = "Provides full account details based on the unique account number."
    )
    @ApiResponse(responseCode = "200", description = "Account successfully found")
    @ApiResponse(responseCode = "404", description = "Account not found")
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
    @Operation(
            summary = "Get balance by accountnumber",
            description = "Provides balance based on the unique account number."
    )
    @ApiResponse(responseCode = "200", description = "Account successfully found")
    @ApiResponse(responseCode = "404", description = "Account not found")
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
    @Operation(
            summary = "Create a new account",
            description = "Create a new account for the user."
    )
    @ApiResponse(responseCode = "200", description = "Account successfully created")
    @ApiResponse(responseCode = "404", description = "Account not created")
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO) {
        AccountDTO result = accountService.createAccount(accountDTO);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{accountNumber}/debit")
    @Operation(
            summary = "Debit from account",
            description = "Debit from account."
    )
    @ApiResponse(responseCode = "200", description = "Account successfully debited")
    @ApiResponse(responseCode = "404", description = "Account not debited")
    public ResponseEntity<AccountDTO> debitAccount(@PathVariable String accountNumber,
                                                   @RequestBody AmountRequest request) {
        AccountDTO result = accountService.debitAccount(accountNumber, request.getAmount());
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{accountNumber}/credit")
    @Operation(
            summary = "Credit from account",
            description = "Credit from account."
    )
    @ApiResponse(responseCode = "200", description = "Account successfully credited")
    @ApiResponse(responseCode = "404", description = "Account not credited")
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
    @Operation(
            summary = "Update account",
            description = "Update account."
    )
    @ApiResponse(responseCode = "200", description = "Account successfully updated")
    @ApiResponse(responseCode = "404", description = "Account not updated")
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
    @Operation(
            summary = "Delete account",
            description = "Delete account."
    )
    @ApiResponse(responseCode = "200", description = "Account successfully deleted")
    @ApiResponse(responseCode = "404", description = "Account not deleted")
    public ResponseEntity<AccountDTO> deleteAccount(@PathVariable UUID id) {
        try {
            accountService.deleteAccount(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
