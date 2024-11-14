package com.fei.foodTrackerApi.service.implementations;

import com.fei.foodTrackerApi.config.JwtUtil;
import com.fei.foodTrackerApi.dto.AccountDTO;
import com.fei.foodTrackerApi.model.Account;
import com.fei.foodTrackerApi.repository.AccountRepository;
import com.fei.foodTrackerApi.service.interfaces.IAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccount {

    private final AccountRepository accountRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Integer createAccount(AccountDTO accountDTO) {
        Account account = new Account();
        account.setEmail(accountDTO.getEmail());
        account.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
        account.setAccountType(accountDTO.getAccountType().toString());
        return accountRepository.save(account).getId();
    }

    @Override
    public String login(String email, String password) {
        Account account = accountRepository.findByEmail(email);
        String result = null;
        if (account != null && passwordEncoder.matches(password, account.getPassword())) {
            result = jwtUtil.generateToken(email, account.getAccountType());
        }
        return result;
    }
}
