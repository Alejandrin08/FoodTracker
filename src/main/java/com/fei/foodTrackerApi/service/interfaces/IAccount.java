package com.fei.foodTrackerApi.service.interfaces;

import com.fei.foodTrackerApi.dto.AccountDTO;

public interface IAccount {
    Integer createAccount(AccountDTO accountDTO);
    String login(String email, String password);
}
