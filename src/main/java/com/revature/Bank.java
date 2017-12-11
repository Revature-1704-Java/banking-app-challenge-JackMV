package com.revature;

import java.io.*;
import java.util.*;

public class Bank {
  private HashMap<Integer, Account> accounts;
  private String accountsPath;
  private static Bank BANK;
  private Command current = new Command();
  private boolean running;

  public Bank() {
    accounts = new HashMap<Integer, Account>();
    running = true;
  }

  public Bank(String pathToAccounts) {
    this.accountsPath = pathToAccounts;
    accounts = readAccountInfo(pathToAccounts);
    running = true;
  }

  private int getNewId() {
    int id = (int) (Math.random() * 100);
    if (accounts.containsKey(id)) {
      return getNewId();
    } else {
      return id;
    }
  }

  public static Bank getInstance() {
    if (BANK == null) {
      BANK = new Bank();
    }
    return BANK;
  }

  public static Bank getInstance(String path) {
    if (BANK == null) {
      BANK = new Bank(path);
    }
    return BANK;
  }

  public static void main(String[] args) {
    getInstance();
    BANK.greet();
    BANK.runBank();
  }

  private void runBank() {
    Parser p = new Parser();
    Command c;
    while(running) {
      c = p.getCommand();
      if(c != null) {
        BANK.executeCommand(c);
        c = null;
      } else {
        System.out.println("Please enter a valid command. Valid commands are: " + current.listCommands());
      }
    }
  }

  private void executeCommand(Command command) {
    String nextCommand = command.getFirst();
    String secondArg = command.getSecond();
    String thirdArg = command.getThird();
    if (nextCommand.equals("new")) {
      if (secondArg!=null) {
        int id = getNewId();
        if (secondArg.equals("savings")) {
          accounts.put(id,new SavingsAccount());
        } else if (command.getSecond().equals("checking")) {
          accounts.put(id,new CheckingAccount());
        }
        System.out.println("Account created. Account ID is: " + id);
      } else {
        System.out.println("Proper usage: new <checking/savings>");
      }
    } else if (nextCommand.equals("deposit")) {
        if (secondArg != null && thirdArg != null) {
          double amount = Double.parseDouble(secondArg);
          int accountId = Integer.parseInt(thirdArg);
          Account account = accounts.get(accountId);
          if (account != null) {
            account.deposit(amount);
            System.out.println("Deposited " + amount + " into account " + accountId);
          } else {
            System.out.println("Account not found. Please ensure account ID is correct and you are following the proper format: deposit <amount> <accountId>");
          }
        } else {
          System.out.println("Proper usage: deposit <amount> <accountId>");
      }
    } else if (nextCommand.equals("withdraw")) {
        if (secondArg != null && thirdArg != null) {
          double amount = Double.parseDouble(secondArg);
          int accountId = Integer.parseInt(thirdArg);
          Account account = accounts.get(accountId);
          if (account != null) {
            account.withdraw(amount);
            System.out.println("Withdrew " + amount + " from account " + accountId);
          } else {
            System.out.println("Account not found. Please ensure account ID is correct and you are following the proper format: deposit <amount> <accountId>");
          }
        } else {
          System.out.println("Proper usage: deposit <amount> <accountId>");
        }
    } else if (nextCommand.equals("balance")) {
      if (secondArg != null) {
        int accountId = Integer.parseInt(secondArg);
        Account account = accounts.get(accountId);
        if (account != null) {
          System.out.println("Account " + accountId + ": " + account.getAccountInfo());
        } else {
          System.out.println("Account not found. Proper usage is balance <accountId>");
        }
      } else {
        System.out.println("Please enter an account ID. Proper usage is balance <accountId>");
      }
    } else if (nextCommand.equals("save")) {
      BANK.writeAccountInfo("./data/accounts");
    } else if (nextCommand.equals("load")) {
      accounts = BANK.readAccountInfo("./data/accounts");
    }
  }

  private boolean accountExists(int accId) {
    return accounts.containsKey(accId);
  }

  private void greet(){
    System.out.println("Welcome to RevBank.");
    System.out.println("What would you like to do?");
    System.out.println("Options are: " + current.listCommands());
  }

  public HashMap<Integer, Account> readAccountInfo(String path) {
    try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
      HashMap<Integer, Account> accounts = (HashMap<Integer, Account>) ois.readObject();
      return accounts;
    } catch (FileNotFoundException ex) { //problem arises if file does not exist
      ex.printStackTrace();
    } catch (IOException ex) { //problem occurs while reading from file
      ex.printStackTrace();
    } catch (ClassNotFoundException ex) { //file isn't what you expected it to be, no object can be read
      ex.printStackTrace();
    }
    return null;
  }

  public void writeAccountInfo(String path) {
    try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
			oos.writeObject(accounts);
		} catch (IOException ex) { //only issue is if problem occurs while writing
			ex.printStackTrace();
		}
  }
}
