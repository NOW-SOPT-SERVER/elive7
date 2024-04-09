package org.sopt.service;

import org.sopt.exceptions.InappropriateValueException;
import org.sopt.model.Account;

import java.util.Scanner;

public class BankingService {
    private final Scanner scanner;
    private final Account account;
    public BankingService(Account account){
        this.scanner = new Scanner(System.in);
        this.account = account;
    }

    public void checkBalance(){
        System.out.println(account.getAccountNumber()+ " " +account.getOwner().name()+" 계좌의 잔액은 "+account.getValue()+"원입니다. ");
    }
    public void deposit(double amount){
        account.deposit(amount);
        System.out.println(account.getAccountNumber()+ " " +account.getOwner().name()+" 계좌로 "+amount+"원을 입금했습니다. ");
    }
    public void withdraw(double amount){
        try{
            account.withdraw(amount);
            System.out.println(account.getAccountNumber()+ " " +account.getOwner().name()+" 계좌에서 "+amount+"원을 출금했습니다. ");
        } catch (InappropriateValueException e){
            System.out.println(e.getMessage());
        }
    }
    public void transfer(Account to, double amount){
        try{
            account.withdraw(amount);
            to.deposit(amount);
            System.out.println( "--" + to.getAccountNumber()+ " " +to.getOwner().name()+" 계좌로 요청하신 " + amount + "원의 계좌 이체가 완료되었습니다. -- ");
        } catch (InappropriateValueException e){
            System.out.println(e.getMessage());
        }
    }
}
