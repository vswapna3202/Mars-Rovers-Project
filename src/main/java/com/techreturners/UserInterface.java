package com.techreturners;

import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    public UserInterface(){
        scanner = new Scanner(System.in);
    }

    public String getPlateauSize(){
        String pattern = "\\d\\s*\\d\\s*";
        String prompt = "Enter the plateau maximum co-ordinates in format x y : ";
        return getInput(prompt, pattern);

    }

    public String getRoverPosition(){
        String pattern = "\\d+\\s+\\d+\\s+[NSEWnsew]";
        String prompt = "Enter the current Rover position co-ordinates in"
                +" format x y direction e.g. 1 2 N : ";
        return getInput(prompt, pattern);
    }

    public String getRoverInstruction(){
        String pattern = "^[LRMlrm]+$";
        String prompt = "Enter the instruction for rover movement(L,R or M): ";
        return getInput(prompt, pattern);
    }

    private String getInput(String prompt, String pattern){
        boolean isValid = false;
        String inputString = "";
        while(!isValid) {
            System.out.print(prompt);
            inputString = scanner.nextLine().trim().toUpperCase();
            isValid = inputString.matches(pattern);
        }
        return inputString;
    }
}