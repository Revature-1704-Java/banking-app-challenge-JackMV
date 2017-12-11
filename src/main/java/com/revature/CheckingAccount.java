package com.revature;

public class CheckingAccount extends Account {

  public CheckingAccount(){}

  public CheckingAccount(double initialBalance) {
    this.balance = initialBalance;
  }


  public String getAccountInfo() {
    String account = "Checking Account. Balance: " + this.balance;
    return account;
  }
}
