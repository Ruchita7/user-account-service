package org.example.banking.service;

import org.example.banking.dto.AccountDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * AccountService is an interface that defines the contract for account-related operations in the banking application.
 * It provides methods for creating, retrieving, updating, and deleting accounts, as well as finding accounts by specific criteria such as account number or user name.
 */
public interface AccountService {

    /**
     * Retrieves an account by its unique identifier.
     *
     * @param uuid the UUID of the account to retrieve.
     * @return the AccountDTO representing the account with the specified UUID.
     */
    AccountDTO findByAccount(UUID uuid);

    /**
     * Retrieves an account by the associated user's name.
     *
     * @param userName the name of the user associated with the account to retrieve.
     * @return the AccountDTO representing the account associated with the specified user name.
     */
    AccountDTO findByUserName(String userName);

    /**
     * Creates a new account based on the provided AccountDTO.
     *
     * @param accountDTO the AccountDTO containing the details of the account to create.
     * @return the AccountDTO representing the newly created account.
     */
    AccountDTO createAccount(AccountDTO accountDTO);

    /**
     * Retrieves a list of all accounts.
     *
     * @return a List of AccountDTOs representing all accounts in the system.
     */
    List<AccountDTO> findAllAccounts();

    /**
     * Updates an existing account identified by the provided accountId with the details from the provided AccountDTO.
     *
     * @param accoundId  the UUID of the account to update.
     * @param accountDTO the AccountDTO containing the updated details of the account.
     * @return the AccountDTO representing the updated account.
     * @throws Exception if the account with the specified UUID does not exist or if there is an error during the update process.
     */
    AccountDTO updateAccount(UUID accoundId, AccountDTO accountDTO) throws Exception;

    /*  *
     * Deletes an account identified by the provided accountId.
     *
     * @param accountId the UUID of the account to delete.
     * @throws Exception if the account with the specified UUID does not exist or if there is an error during the deletion process.
     */
    void deleteAccount(UUID accountId) throws Exception;

    /* *
     * Retrieves an account by its account number.
     *
     * @param accountNumber the account number of the account to retrieve.
     * @return the AccountDTO representing the account with the specified account number.
     * @throws Exception if the account with the specified account number does not exist or if there is an error during the retrieval process.
     */
    AccountDTO findByAccountNumber(String accountNumber) throws Exception;

    /* *
     * Retrieves the balance of an account by its account number.
     *
     * @param accountNumber the account number of the account whose balance is to be retrieved.
     * @return the BigDecimal representing the balance of the account with the specified account number.
     * @throws Exception if the account with the specified account number does not exist or if there is an error during the retrieval process.
     */
    BigDecimal findBalanceByAccountNumber(String accountNumber) throws Exception;
}
