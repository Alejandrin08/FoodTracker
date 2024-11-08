package com.fei.foodTrackerApi.controller;

import com.fei.foodTrackerApi.dto.AccountDTO;
import com.fei.foodTrackerApi.service.implementations.AccountService;
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

    private final AccountService accountService;

    @PostMapping()
    public ResponseEntity<String> createAccount(@Valid @RequestBody AccountDTO accountDTO) {
        Integer id = accountService.createAccount(accountDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Account created successfully. ID: " + id);
    }
}
