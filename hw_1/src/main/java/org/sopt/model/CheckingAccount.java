package org.sopt.model;

import org.sopt.exceptions.InappropriateValueException;

public class CheckingAccount extends Account{
    public CheckingAccount(String accountNumber, Person owner){
        super(accountNumber, owner);
    }

    @Override
    public void deposit(double amount) {
        this.setValue(amount+this.getValue());
    }

    @Override
    public void withdraw(double amount) throws InappropriateValueException {
        if (amount > this.getValue()){
            throw new InappropriateValueException("잔액이 부족합니다. 다시 시도해주세요.");
        }
        this.setValue(this.getValue()-amount);
    }
}
