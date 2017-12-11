package com.revature;

import java.lang.StringBuilder;

public class Command{
  private static final String[] validCommands = {"new", "deposit", "withdraw", "balance", "save", "load"};
  private String firstWord;
  private String secondWord;
  private String thirdWord;

  public Command() {}

  public Command(String firstWord, String secondWord, String thirdWord) {
    this.firstWord = firstWord;
    this.secondWord = secondWord;
    this.thirdWord = thirdWord;
  }

  public String getFirst() {
    return this.firstWord;
  }

  public String getSecond() {
    return this.secondWord;
  }

  public String getThird() {
    return this.thirdWord;
  }

  public String listCommands() {
    StringBuilder sb = new StringBuilder();
    for(String s : validCommands) {
      sb.append(s + " ");
    }
    return sb.toString();
  }

  public static boolean validCommand(String attemptedCommand) {
    for(String s : validCommands) {
      if (s.equals(attemptedCommand)) {
        return true;
      }
    }
    return false;
  }

  public boolean hasSecondWord() {
    return (this.secondWord != null);
  }

  public boolean hasThirdWord() {
    return (this.thirdWord != null);
  }
}
