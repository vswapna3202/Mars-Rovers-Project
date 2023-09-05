package com.techreturners;

import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    public UserInterface(){
        scanner = new Scanner(System.in);
    }

    public String getPlateauSize(){

        boolean isValid = false;
        String inputString = "";
        while(!isValid) {
            System.out.print("Enter the plateau coordinates in format x y : ");
            String pattern = "\\d\\s*\\d\\s*";
            inputString = scanner.nextLine().trim().toUpperCase();
            isValid = inputString.matches(pattern);
        }
        return inputString;
    }

    public static void main(String[] args){
        UserInterface userInterface = new UserInterface();
        System.out.println(userInterface.getPlateauSize());

    }
}