package com.bankapp.service;

import com.bankapp.dto.AccountDto;
import com.bankapp.entity.Account;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto getById(Long id);
    List<AccountDto> getAllAccounts();
    AccountDto updateAccount(Long id,AccountDto accountDto);
    void deleteAccount(Long id);

    AccountDto deposit(Long id, double amount);
    AccountDto withdraw(Long id, double amount);

}
