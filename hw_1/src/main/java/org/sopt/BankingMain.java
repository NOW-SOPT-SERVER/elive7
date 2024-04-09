package org.sopt;

import org.sopt.controller.AccountController;
import org.sopt.controller.BankingController;
import org.sopt.exceptions.InappropriateAccountException;
import org.sopt.model.Account;
import org.sopt.model.Person;
import org.sopt.service.AccountService;
import org.sopt.service.BankingService;
import org.sopt.view.BankingView;

public class BankingMain {
    public static void main(String[] args) {
        BankingView bankingView = new BankingView();
        AccountController accountController = new AccountController(new AccountService());
        Account account = null;
        do{
            int choice = bankingView.checkAccount();
            switch (choice){
                case 1:
                    try {
                        String s = bankingView.checkExistingAccount();
                        account = accountController.findAccount(s);
                    }catch (InappropriateAccountException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    Person p = bankingView.makeNewMember();
                    account = accountController.createAccount(p);
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            }

        }while(account == null);
        BankingController bankingController = new BankingController(new BankingService(account));
        while(true){
            int choice = bankingView.printAccount(account);
            switch (choice){
                case 0:
                    return;
                case 1:
                    bankingController.deposit(bankingView.getAmount());
                    break;
                case 2:
                    bankingController.withdraw(bankingView.getAmount());
                    break;
                case 3:
                    Account a = null;
                    try {
                        String s = bankingView.checkTransferAccount();
                        a = accountController.findAccount(s);
                    }catch (InappropriateAccountException e){
                        System.out.println(e.getMessage());
                    }
                    if (a!=null) {
                        bankingController.transfer(a,bankingView.getAmount());
                    }
                    break;
                case 4:
                    bankingController.checkBalance();
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 시도해주세요.");

            }
        }
    }
}