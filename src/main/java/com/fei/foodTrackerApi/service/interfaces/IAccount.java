package com.fei.foodTrackerApi.service.interfaces;

import com.fei.foodTrackerApi.dto.AccountDTO;

public interface IAccount {
    AccountDTO createAccount(AccountDTO accountDTO);
    String login(AccountDTO loginDTO);
    AccountDTO modifyAccount(Integer id, AccountDTO loginDTO);
}
