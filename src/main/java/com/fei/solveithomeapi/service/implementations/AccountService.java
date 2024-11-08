package com.fei.solveithomeapi.service.implementations;

import com.fei.solveithomeapi.dto.AccountDTO;
import com.fei.solveithomeapi.model.Account;
import com.fei.solveithomeapi.repository.AccountRepository;
import com.fei.solveithomeapi.service.interfaces.IAccount;
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
