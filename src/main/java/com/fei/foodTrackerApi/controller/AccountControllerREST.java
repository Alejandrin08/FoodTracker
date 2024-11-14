package com.fei.foodTrackerApi.controller;

import com.fei.foodTrackerApi.dto.AccountDTO;
import com.fei.foodTrackerApi.service.interfaces.IAccount;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountControllerREST {

    private final IAccount accountService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid String email, String password) {
        String token = accountService.login(email, password);
        if (token != null) {
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> createAccount(@RequestBody @Valid AccountDTO accountDTO) {
        Integer id = accountService.createAccount(accountDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Account created successfully. ID: " + id);
    }
}
