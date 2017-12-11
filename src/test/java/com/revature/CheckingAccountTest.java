package com.revature;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.*;

public class CheckingAccountTest {

  @Test
  public void accountInfoReturnsBalance() {
    CheckingAccount account = new CheckingAccount(250);
    assertEquals("Checking Account. Balance: 250.0", account.getAccountInfo());
  }

  @Test
  public void cantWithdrawPastZero() {
    CheckingAccount account = new CheckingAccount(0);
    assertEquals(0, account.withdraw(500), 0.02);
  }
}
