package org.example.banking.service;

import lombok.AllArgsConstructor;
import org.example.banking.dto.AccountDTO;
import org.example.banking.entity.Account;
import org.example.banking.entity.User;
import org.example.banking.repository.AccountRepository;
import org.example.banking.repository.UserRepository;
import org.modelmapper.ModelMapper;import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountService_Impl implements AccountService{

    private AccountRepository accountRepository;
    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    public AccountDTO findByAccount(UUID uuid) {
        Optional<Account> accountOptional = accountRepository.findById(uuid); //TODO: add gloabal exception
        return modelMapper.map(accountOptional, AccountDTO.class);
    }

    @Override
    public AccountDTO findByUserName(String userName) {
        Account account = accountRepository.findByUserName(userName);
        return modelMapper.map(account, AccountDTO.class);
    }

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        User user = userRepository.findById(accountDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Account account = modelMapper.map(accountDTO, Account.class);
        account.setUser(user);
        Account savedAccount = accountRepository.save(account);
        AccountDTO result = modelMapper.map(savedAccount, AccountDTO.class);
        return result;
    }

    @Override
    public List<AccountDTO> findAllAccounts() {
       List<Account> accounts =  accountRepository.findAll();
       return accounts.stream().map(account -> modelMapper.map(account,AccountDTO.class)).collect(Collectors.toList());
    }

    @Override
    public AccountDTO findByAccountNumber(String accountNumber) throws Exception {
        return accountRepository.findByAccountNumber(accountNumber).map(account ->
                modelMapper.map(account, AccountDTO.class)).orElseThrow(()-> new Exception("Account Number not found"));
    }

    @Override
    public AccountDTO updateAccount(UUID accountId, AccountDTO accountDTO) throws Exception {
        //TODO Exception Handling
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new Exception());
        account.setAccountNumber(accountDTO.getAccountNumber());
        account.setBalance(accountDTO.getBalance());
        account.setUpdatedOn(accountDTO.getUpdatedOn());
        account.setAccountId(accountId);
        account.setAccountType(accountDTO.getAccountType());
        accountRepository.save(account);
        return modelMapper.map(account, AccountDTO.class);
    }

    @Override
    public void deleteAccount(UUID accountId) throws Exception {
        //TODO Exception Handling
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new Exception());
        accountRepository.delete(account);
    }

    @Override
    public BigDecimal findBalanceByAccountNumber(String accountNumber) throws Exception {
         AccountDTO accountDTO =  accountRepository.findByAccountNumber(accountNumber).map(account ->
                modelMapper.map(account, AccountDTO.class)).orElseThrow(()-> new Exception("Account Number not found"));
        return accountDTO.getBalance();
    }
}
