package com.bankapp.controller;

import com.bankapp.dto.AccountDto;
import com.bankapp.entity.Account;
import com.bankapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService  accountService;

    @PostMapping
    public ResponseEntity<AccountDto> savedAccount(@RequestBody AccountDto accountDto){
        AccountDto saved = accountService.createAccount(accountDto);
        return new ResponseEntity<AccountDto>(saved, HttpStatus.CREATED);

    }

    @GetMapping("{id}")
    public ResponseEntity<AccountDto> getById(@PathVariable("id") Long accountId){
        AccountDto getAccountById = accountService.getById(accountId);
        return new ResponseEntity<AccountDto>(getAccountById,HttpStatus.OK);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<AccountDto>> getAll(){
       List<AccountDto>  getAllAccounts = accountService.getAllAccounts();
       return new ResponseEntity<>(getAllAccounts,HttpStatus.OK);
    }


    @PutMapping("{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable("id") Long accountId,@RequestBody AccountDto accountDto){
        AccountDto update = accountService.updateAccount(accountId,accountDto);
        return new ResponseEntity<AccountDto>(update,HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable("id") Long accountId){
         accountService.deleteAccount(accountId);
         return ResponseEntity.ok().body("deleted");

    }


    @PutMapping("{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable("id") Long id , @RequestBody Map<String,Double> request){
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.deposit(id,amount);
        return new ResponseEntity<AccountDto>(accountDto,HttpStatus.OK);
    }


    @PutMapping("{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,@RequestBody Map<String,Double> request){
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id,amount);
        return new ResponseEntity<AccountDto>(accountDto,HttpStatus.OK);

    }

}
