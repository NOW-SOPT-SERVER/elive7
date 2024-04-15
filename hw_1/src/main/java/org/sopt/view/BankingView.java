package org.sopt.view;

import jdk.jshell.Snippet;
import org.sopt.model.Account;
import org.sopt.model.Person;

import java.util.Scanner;

public class BankingView {
    private Scanner scanner;
    public BankingView(){
        this.scanner = new Scanner(System.in);
    }
    public int checkAccount(){
        System.out.println("===== Banking App =====");
        System.out.println("계좌가 있으신가요? ");
        System.out.println("1. 에");
        System.out.println("2. 아니요");
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            return choice;
        } catch (NumberFormatException e){
            System.out.println("숫자를 입력해 주세요.");
            return checkAccount();
        }

    }

    public String checkExistingAccount(){
        System.out.println("===============");
        System.out.println("기존 계좌 번호를 입력해주세요.");
        String accountNumber = scanner.nextLine();
        return accountNumber;
    }

    public Person makeNewMember(){
        System.out.println("===============");
        System.out.println("본인의 성명을 입력해주세요.");
        String name = scanner.nextLine();
        System.out.println("본인의 주소를 입력해주세요.");
        String address = scanner.nextLine();
        System.out.println("본인의 나이를 입력해주세요.");
        try {
            int age = Integer.parseInt(scanner.nextLine());
            if (age<0){
                System.out.println("나이는 양수로 입력해야 합니다. 다시 입력해 주세요.");
                return makeNewMember();
            }
            return new Person(name,address,age);
        } catch (NumberFormatException e){
            System.out.println("나이에는 숫자를 입력해야 합니다. 다시 입력해 주세요.");
            return makeNewMember();
        }
    }


    public int printAccount(Account account){
        System.out.println("===============");
        System.out.println(account.getAccountNumber()+ " "+account.getOwner()+"의 계좌가 확인되었습니다.");
        System.out.println("위 계좌를 가지고 어떤 거래를 수행할까요?");
        System.out.println("1: 입금");
        System.out.println("2: 출금");
        System.out.println("3: 계좌 이체");
        System.out.println("4: 잔고 확인");
        System.out.println("0: 거래 종료");
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            return choice;
        } catch (NumberFormatException e){
            System.out.println("숫자를 입력해 주세요.");
            return printAccount(account);
        }
    }

    public int getAmount(){
        System.out.println("본인 계좌에서 입금/출금/계좌이체할 금액을 입력하세요.");
        try {
            int amount = Integer.parseInt(scanner.nextLine());
            if (amount<0){
                System.out.println("금액은 양수로 입력해 주세요.");
                return getAmount();
            }
            return amount;
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해 주세요.");
            return getAmount();
        }
    }

    public String checkTransferAccount(){
        System.out.println("계좌이체할 계좌번호를 입력해주세요.");
        String accountNumber = scanner.nextLine();
        return accountNumber;
    }



}
