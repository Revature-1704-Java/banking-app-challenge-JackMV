package com.revature;

public class SavingsAccount extends Account {

  private double interestRate;

  public SavingsAccount(){}

  public SavingsAccount(double initialValue, double interestRate) {
    this.balance = initialValue;
    this.interestRate = interestRate;
  }

  public String getAccountInfo() {
    String account = "Savings Account. Balance: " + this.balance;
    return account;
  }

  public void addInterest() {
    this.balance *= this.interestRate;
  }
}
