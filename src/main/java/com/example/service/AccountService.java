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
        if(validateAccount(account)) {
            accountRepository.save(account);
            return accountRepository.findByUsername(account.getUsername()).get();
        }
        else return new Account(-2, account.getUsername(), account.getPassword());
    }

    private Boolean validateAccount(Account account) {
        int passMinLength = 4;

        if(
            !account.getUsername().isBlank() &&
            account.getPassword().length() >= passMinLength &&
            !accountRepository.findByUsername(account.getUsername()).isPresent()
            )
        return true;
        else return false;
    }

}
