package org.sopt.service;

import org.sopt.exceptions.InappropriateAccountException;
import org.sopt.model.Account;
import org.sopt.model.CheckingAccountBuilder;
import org.sopt.model.Person;
import org.sopt.repository.AccountRepository;

public class AccountService {
    private AccountRepository accountRepository;
    public AccountService(){
        this.accountRepository = AccountRepository.getInstance();
    }
    public Account findAccount(String accountNumber) throws InappropriateAccountException {
        return accountRepository.findAccount(accountNumber);
    }
    public Account createAccount(Person owner){
        Account account = new CheckingAccountBuilder().accountNumber(accountRepository.getNewAccountNumber().toString()).owner(owner).build();
        accountRepository.addAcount(account);
        System.out.println("계좌 생성이 완료되었습니다.");
        return account;
    }
}
