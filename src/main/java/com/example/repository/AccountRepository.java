package com.example.repository;

import com.example.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    /**
     * Retrieves an entity by its username field.
     * @param username a username field value.
     * @return the entity with the given username or an Optional#empty if none found.
     */
    Optional<Account> findByUsername(String username);

    /**
     * Retrieves an entity by its username and password fields.
     * @param username a username field value.
     * @param password a password field value.
     * @return the entity with the given username and password or an Optional#empty if none found.
     */
    Optional<Account> findByUsernameAndPassword(String username, String password);
}
