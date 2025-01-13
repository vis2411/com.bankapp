package com.bankapp.mapper;

import com.bankapp.dto.AccountDto;
import com.bankapp.entity.Account;

public class AccountMapper {

    //Account to AccountDto mapper
    public static AccountDto mapToAccountDto(Account account){
        return new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
    }

    public static  Account mapToAccount(AccountDto accountDto){
        return new Account(
                accountDto.id(),
                accountDto.accountHolderName(),
                accountDto.balance()
        );

    }

}
