package com.revature;

import java.util.Scanner;

public class Parser {
  Scanner sc;

  Parser(){
    this.sc = new Scanner(System.in);
  }

  public Command getCommand() {
    String line;
    String wordOne;
    String wordTwo = null;
    String wordThree = null;

    line = sc.nextLine();

    Scanner split = new Scanner(line);
    wordOne = split.next();
    if (split.hasNext()) {
      wordTwo = split.next();
      if (split.hasNext()) {
        wordThree = split.next();
      }
    }

    if(Command.validCommand(wordOne)) {
      return new Command(wordOne, wordTwo, wordThree);
    }
    return null;
  }
}
