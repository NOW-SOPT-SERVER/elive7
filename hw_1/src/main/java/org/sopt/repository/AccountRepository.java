package org.sopt.repository;

import org.sopt.exceptions.InappropriateAccountException;
import org.sopt.model.Account;
import org.sopt.model.CheckingAccountBuilder;
import org.sopt.model.Person;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AccountRepository {
    private static AccountRepository accountRepository = new AccountRepository();
    private final Map<String, Account> accoutMap = new HashMap<>();

    public static AccountRepository getInstance(){
        return  accountRepository;
    }

    private AccountRepository(){
        Account account1 = new CheckingAccountBuilder().accountNumber("1").owner(new Person("김철수", "서울시", 23)).build();
        Account account2 = new CheckingAccountBuilder().accountNumber("2").owner(new Person("이영희", "경기도", 30)).build();
        accoutMap.put(account1.getAccountNumber(), account1);
        accoutMap.put(account2.getAccountNumber(), account2);
    }
    public void addAcount(Account account){
        accoutMap.put(account.getAccountNumber(), account);
    }
    public Account findAccount(String accountNumber) throws InappropriateAccountException{
        Account account = accoutMap.get(accountNumber);
        if (account!=null){
            return account;
        }
        else{
            throw new InappropriateAccountException("계좌를 찾을 수 없습니다. 다시 시도해 주세요.");
        }
    }
    public Integer getNewAccountNumber(){
        return accoutMap.size()+1;
    }
}
