package com.fei.foodTrackerApi.service.implementations;

import com.fei.foodTrackerApi.dto.AccountDTO;
import com.fei.foodTrackerApi.repository.AccountRepository;
import com.fei.foodTrackerApi.service.interfaces.IAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccount {

    private final AccountRepository accountRepository;

    public Integer createAccount(AccountDTO accountDTO) {
        Account account = new Account();
        account.setEmail(accountDTO.getEmail());
        account.setPassword(accountDTO.getPassword());
        account.setAccountType(accountDTO.getAccountType().toString());
        return accountRepository.save(account).getId();
    }
}
