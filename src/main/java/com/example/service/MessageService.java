package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for message database interation.
 * @author C. Quinto
 */
@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    AccountRepository accountRepository;
    
    /**
     * Add message record.
     * @return a list of all messages.
     */
    public Message addMessage(Message target) {
        if (validateMessage(target)) return messageRepository.save(target);
        else return new Message(-1,-1,"",Long.getLong("-1"));
    }
    
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
        return messageRepository.findById(id).orElse(new Message(null,"",null));
    }

    /**
     * Get message records by matching postedBy Id.
     * @param id value to match against an entry's postedBy field.
     * @return list of entities with the matching value or an empty list if none found.
     */
    public List<Message> getAllMessagesByPostedById(int id) {
        Optional<List<Message>> optionalResult = messageRepository.findAllByPostedBy(id);
        
        if (optionalResult.isPresent()) {
            return optionalResult.get();
        } else {
            return List.of(new Message());
        }
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

    /**
     * Update message record text field by matching Id.
     * @param id value to match against record id field.
     * @param target entity containing desired text value to update.
     * @return
     */
    public int updateMessageTextById(int id, Message target) {
        Message message = messageRepository.findById(id).orElse(new Message());

        target.setMessageId(id);
        
        if (message.getMessageId() != null) {
            target.setPostedBy(message.getPostedBy());
            target.setTimePostedEpoch(message.getTimePostedEpoch());

                if (validateMessage(target)) {
                messageRepository.save(target);
                return 1;
            }
        }
        
        return 0;
    }

    /**
     * Validate a message entity.
     * @param target the message entity to validate.
     * @return true if message is valid, false otherwise.
     */
    private Boolean validateMessage(Message target) {
        int charCountMax = 255, charCountMin = 1;

        if (
            target.getMessageText().isBlank() ||
            target.getMessageText().length() < charCountMin ||
            target.getMessageText().length() > charCountMax ||
            accountRepository.findById(target.getPostedBy()).isEmpty()
            ) return false;

        return true;
    }
}
