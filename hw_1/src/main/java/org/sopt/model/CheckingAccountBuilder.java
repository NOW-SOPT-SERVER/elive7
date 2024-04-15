package org.sopt.model;

public class CheckingAccountBuilder {
    private String accountNumber;
    private Person owner;

    public CheckingAccountBuilder accountBuilder(String accountNumber){
        this.accountNumber = accountNumber;
        return  this;
    }

    public CheckingAccountBuilder owner(Person owner){
        this.owner = owner;
        return this;
    }


    public CheckingAccountBuilder accountNumber(String accountNumber){
        this.accountNumber = accountNumber;
        return this;
    }

    public CheckingAccount build(){
        return new CheckingAccount(accountNumber, owner);
    }
}
