package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entity.Account;
import com.example.repository.AccountRepository;

import java.util.List;

/**
 * Service layer for account database interation.
 * @author C. Quinto
 */
@Service
public class AccountService {
    String errorUsername = "Username is invalid/blank.",
        errorPassword = "Password is invalid/blank.",
        errorDuplicate = "Duplicate entry.",
        errorNone = "Valid entry.";
    AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Persist Account record
     * @param account an Account.
     * @return an Account.
     */
    public Account addAccount(Account account) {
        if(validateAccount(account) == errorNone) {
            accountRepository.save(account);
            return accountRepository.findByUsername(account.getUsername()).get();
        }
        else if(validateAccount(account) == errorDuplicate) return new Account(-2, account.getUsername(), account.getPassword());
        else return new Account(-1, account.getUsername(), account.getPassword());
    }

    /**
     * Find a matching Account record from the database.
     * @param account an Account.
     * @return an Account.
     */
    public Account getAccountByUsernameAndPassword(Account account) {
        return accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword()).orElse(new Account(-1," ", " "));
    }

    private String validateAccount(Account account) {
        int passMinLength = 4;

        if(account.getUsername().isBlank()) return errorUsername;
        else if(account.getPassword().length() < passMinLength) return errorPassword;
        else if(accountRepository.findByUsername(account.getUsername()).isPresent()) return errorDuplicate;
        else return errorNone;
    }

}
