package com.example.repository;

import com.example.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, Integer> {
    /**
     * Retrieves an entity by its username field.
     * @param username a username field value.
     * @return the entity with the given username or an Optional#empty if none found.
     */
    Optional<Account> findByUsername(String username);
}
