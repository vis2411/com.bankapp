package com.bankapp.service.impl;

import com.bankapp.dto.AccountDto;
import com.bankapp.entity.Account;
import com.bankapp.exception.ResourceNotFoundException;
import com.bankapp.mapper.AccountMapper;
import com.bankapp.repository.AccountRepository;
import com.bankapp.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;


    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getById(Long id) {
        Account getAccount = accountRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Account is not available with given id " +id));
        return AccountMapper.mapToAccountDto(getAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> getAllAccounts = accountRepository.findAll();
        return getAllAccounts.stream().map((accounts)->AccountMapper.mapToAccountDto(accounts)).collect(Collectors.toList());
    }

    @Override
    public AccountDto updateAccount(Long id, AccountDto accountDto) {
        Account updateAccount = accountRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("given id is not available" + id));
        updateAccount.setAccountHolderName(accountDto.accountHolderName());
        updateAccount.setBalance(accountDto.balance());
        Account savedAccount =accountRepository.save(updateAccount);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public void deleteAccount(Long id) {
        Account deleteAccountById = accountRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("account is not available with this id" +id));
        accountRepository.deleteById(id);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account =  accountRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("id is not present" + id));
        double total = account.getBalance()+amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("id is not present"));

        if(account.getBalance()<amount){
            throw new RuntimeException("Insufficient balance");

        }
        double total = account.getBalance()-amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }
}
