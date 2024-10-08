package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entity.Account;
import com.example.repository.AccountRepository;

/**
 * Service layer for account database interation.
 * @author C. Quinto
 */
@Service
public class AccountService {
    AccountRepository accountRepository;
    String errorUsername = "Username is invalid/blank.",
        errorPassword = "Password is invalid/blank.",
        errorDuplicate = "Duplicate entry.",
        errorNone = "Valid entry.";

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Persist account record
     * @param account an entity with the desired fields to persist.
     * @return the persisted record or an Account with an invalid negative id if database insertion fails.
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
     * Find an account record matching desired username and password values.
     * @param account an Account with the desired field values to match.
     * @return the matched account record or an Account with invalid field values if not match is found.
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
