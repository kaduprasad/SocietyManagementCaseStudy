package com.society.input;
import java.util.Scanner;

public abstract class AbstractUserInput<T> {

    Scanner scan = new Scanner(System.in); // default
    void exit(){
        System.exit(0);
    }
}
