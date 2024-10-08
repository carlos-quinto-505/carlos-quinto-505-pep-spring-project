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
    MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }
    
    /**
     * Get all message records.
     * @return a list of all messages.
     */
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    
}
