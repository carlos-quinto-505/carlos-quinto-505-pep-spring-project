package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.entity.*;
import com.example.service.*;

import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 * 
 */
/**
 * Controller layer.
 * @author C. Quinto
 */
@Controller
@ResponseBody
public class SocialMediaController {
    @Autowired
    AccountService accountService;
    @Autowired
    MessageService messageService;

    /**
     * Handle POST request to account database endpoint /register.
     * @param account Account data for POST request.
     * @return a response - a HTTP status code, followed by an entity representing the new record on success or an empty response body if request failed.
     */
    @PostMapping("/register")
    public ResponseEntity<Account> addAccount(@RequestBody Account target) {
        Account account = accountService.addAccount(target);

        if (account.getAccountId() >= 0) return ResponseEntity.status(200).body(account);
        else if (account.getAccountId() == -2) return ResponseEntity.status(409).build();
        else return ResponseEntity.status(400).build(); 
    }
    
    /**
     * Handle POST request to account database endpoin /login.
     * @param account Account data for POST request.
     * @return a response - a HTTP status code, followed by an entity representing the matched record or an empty response body if no match found.
     */
    @PostMapping("/login")
    public ResponseEntity<Account> loginAccount(@RequestBody Account target) {
        Account account = accountService.getAccountByUsernameAndPassword(target);

        if(account.getAccountId() != null && account.getAccountId() > 0) return ResponseEntity.status(200).body(account);
        else return ResponseEntity.status(401).build();
    }

    /**
     * Handle GET request to message database endpoin /messages.
     * @return a response - a HTTP status code, followed by a list of retrieved message records.
     */
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        return ResponseEntity.status(200).body(messageService.getAllMessages());
    }
    
    /**
     * Handle GET request to message database endpoin /messages/{messageId}.
     * @return a response - a HTTP status code, followed by a list of retrieved message records.
     */
    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable int messageId) {
        Message target = messageService.getMessageById(messageId);

        if (target.getMessageId() > 0) return ResponseEntity.status(200).body(target);
        else return ResponseEntity.status(200).build();
    }
    
    /**
     * Handle GET request to message database endpoin /messages/{messageId}.
     * @return a response - a HTTP status code, followed by a list of retrieved message records.
     */
    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Integer> deleteMessageById(@PathVariable int messageId) {
        int rows = messageService.deleteMessageById(messageId);

        if (rows > 0) return ResponseEntity.status(200).body(rows);
        else return ResponseEntity.status(200).build();
    }
}
