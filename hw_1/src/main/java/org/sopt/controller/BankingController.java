package org.sopt.controller;

import org.sopt.exceptions.InappropriateValueException;
import org.sopt.model.Account;
import org.sopt.service.AccountService;
import org.sopt.service.BankingService;

public class BankingController {
    private final BankingService bankingService;

    public BankingController(BankingService bankingService){
        this.bankingService = bankingService;
    }
    public void checkBalance(){
        bankingService.checkBalance();
    }
    public void deposit(double amount){
        bankingService.deposit(amount);
    }
    public void withdraw(double amount){
        bankingService.withdraw(amount);
    }
    public void transfer(Account to, double amount){
        bankingService.transfer(to, amount);
    }
}
