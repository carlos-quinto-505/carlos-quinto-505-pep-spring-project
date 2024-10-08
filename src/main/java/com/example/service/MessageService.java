package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entity.Message;
import com.example.repository.MessageRepository;

import java.util.List;

/**
 * Service layer for message database interation.
 * @author C. Quinto
 */
@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;
    
    /**
     * Get all message records.
     * @return a list of all messages.
     */
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    /**
     * Get message record by matching Id.
     * @return a message entity with a matching Id or a Message#empty if no match is found.
     */
    public Message getMessageById(Integer id) {
        return messageRepository.findById(id).orElse(new Message(-1,-1,"",Long.getLong("-1")));
    }

    /**
     * Delete message record by matching Id.
     * @return number of rows updated.
     */
    public int deleteMessageById(Integer id) {
        if (messageRepository.existsById(id)) {
            long countNew = 0, countOld = messageRepository.count();

            messageRepository.deleteById(id);
            countNew = messageRepository.count();
            return (int)(countOld - countNew);
        } else return 0;
    }
}
