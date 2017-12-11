package com.revature;

import java.io.Serializable;

public abstract class Account implements Serializable {
  protected double balance;

  public void deposit(double amount) {
    this.balance += amount;
  }

  public double withdraw(double amount) {
    double afterTransaction = this.balance - amount;
    if(afterTransaction > 0) {
      return afterTransaction;
    }
    //print error message "insufficient funds" or something like that
    return 0;
  }

  public abstract String getAccountInfo();
}
