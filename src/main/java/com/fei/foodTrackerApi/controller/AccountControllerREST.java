package com.fei.foodTrackerApi.controller;

import com.fei.foodTrackerApi.dto.AccountDTO;
import com.fei.foodTrackerApi.dto.LoginResponseDTO;
import com.fei.foodTrackerApi.service.interfaces.IAccount;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountControllerREST {

    private final IAccount accountService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AccountDTO accountDTO) {
        String token = accountService.login(accountDTO);
        if (token != null) {
            return ResponseEntity.ok(new LoginResponseDTO(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<AccountDTO> createAccount(@RequestBody @Valid AccountDTO accountDTO) {
        AccountDTO account = accountService.createAccount(accountDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(account);
    }

    @PutMapping("/modifyAccount/{id}")
    public ResponseEntity<AccountDTO> modifyAccount(@PathVariable @Valid Integer id, @RequestBody @Valid AccountDTO accountDTO) {
        AccountDTO account = accountService.modifyAccount (id, accountDTO);
        return ResponseEntity.ok(account);
    }
}
