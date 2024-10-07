package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.entity.*;
import com.example.service.*;

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
    AccountService accountService;

    @Autowired
    public SocialMediaController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Handle POST request to account database.
     * @param account Account data for POST request.
     * @return a response - a HTTP status code, followed by an entity representing the new record on success.
     */
    @PostMapping("/register")
    public ResponseEntity<Account> addAccount(@RequestBody Account account) {
        Account newAccount = accountService.addAccount(account);

        if (newAccount.getAccountId() >= 0) return ResponseEntity.status(200).body(newAccount);
        else if (newAccount.getAccountId() == -2) return ResponseEntity.status(409).build();
        else return ResponseEntity.status(400).build(); 
    }
}
