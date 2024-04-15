package org.sopt.controller;

import org.sopt.exceptions.InappropriateAccountException;
import org.sopt.model.Account;
import org.sopt.model.Person;
import org.sopt.service.AccountService;

public class AccountController {
    private final AccountService accountService;
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }
    public Account findAccount(String accountNumber) throws InappropriateAccountException{
        return accountService.findAccount(accountNumber);
    }
    public Account createAccount(Person owner){
        return accountService.createAccount(owner);
    }
}
