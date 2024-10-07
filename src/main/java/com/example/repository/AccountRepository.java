package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

import com.example.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    /**
     * Retrieves an entity by its username field.
     * @param username a username field value.
     * @return the entity with the given username or an Optional#empty if none found.
     */
    @Query(value = "SELECT * FROM account WHERE username=?1;")
    public Optional<Account> findByUsername(String username);
}
