package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.Message;

import java.util.List;
import java.util.Optional;

/**
 * Message repository layer.
 * @author C. Quinto
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{
    /**
     * Retrieves a list of all entries matching a given postedBy id.
     * @param value the desired postedBy value.
     * @return entities matching the postedBy field value or Optional#empty if none found.
     * @throws IllegalArgumentException value is null.
     */
    public Optional<List<Message>> findAllByPostedBy(int value) throws IllegalArgumentException;
}
