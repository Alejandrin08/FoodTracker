package com.fei.foodTrackerApi.service.implementations;

import com.fei.foodTrackerApi.config.JwtUtil;
import com.fei.foodTrackerApi.dto.AccountDTO;
import com.fei.foodTrackerApi.dto.CustomUserDetails;
import com.fei.foodTrackerApi.model.Account;
import com.fei.foodTrackerApi.repository.AccountRepository;
import com.fei.foodTrackerApi.service.interfaces.IAccount;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccount {

    private final AccountRepository accountRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        Account account = new Account();
        account.setEmail(accountDTO.getEmail());
        account.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
        account.setAccountType(accountDTO.getAccountType().toString());
        accountRepository.save(account);
        return modelMapper.map(account, AccountDTO.class);
    }

    @Override
    public String login(AccountDTO accountDTO) {
        Optional<Account> accountOptional = accountRepository.findByEmail(accountDTO.getEmail());
        String token = null;

        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            if (passwordEncoder.matches(accountDTO.getPassword(), account.getPassword())) {
                CustomUserDetails userDetails = new CustomUserDetails(account);
                token = jwtUtil.generateToken(userDetails);
            } else {
                throw new RuntimeException("Invalid credentials");
            }
        } else {
            throw new RuntimeException("Account not found");
        }
        return token;
    }

    @Override
    @Transactional
    public AccountDTO modifyAccount(Integer id, AccountDTO accountDTO) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();

            if (accountRepository.existsByEmail(accountDTO.getEmail()) && !account.getEmail().equals(accountDTO.getEmail())) {
                throw new RuntimeException("Email already in use");
            }

            account.setEmail(accountDTO.getEmail());
            String encodedPassword = passwordEncoder.encode(accountDTO.getPassword());
            account.setPassword(encodedPassword);
            accountRepository.save(account);

            return modelMapper.map(account, AccountDTO.class);
        } else {
            throw new RuntimeException("Account not found");
        }
    }
}