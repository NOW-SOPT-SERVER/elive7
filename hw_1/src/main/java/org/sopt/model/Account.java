package org.sopt.model;

import org.sopt.exceptions.InappropriateValueException;

public abstract class Account {
    private final String accountNumber;
    private final Person owner;
    private double value;
    public Account(String accountNumber, Person owner){
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.value = 0;
    }

    public abstract void deposit(double amount);
    public abstract void withdraw(double amount) throws InappropriateValueException;

    public String getAccountNumber() {
        return accountNumber;
    }

    public Person getOwner() {
        return owner;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
