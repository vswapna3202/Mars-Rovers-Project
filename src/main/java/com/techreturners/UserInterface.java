package com.techreturners;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;
    public UserInterface(){
        scanner = new Scanner(System.in);
    }

    public Scanner getScanner() {
        return scanner;
    }

    public String getPlateauSize(){
        String pattern = "\\d+\\s+\\d+\\s*";
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

    public RoverDataBO collectRoverData(){
        String plateauSize = getPlateauSize();
        ArrayList<String> roverPositionList = new ArrayList<>();
        ArrayList<String> roverInstructionList = new ArrayList<>();
        while(true){
            roverPositionList.add(getRoverPosition());
            roverInstructionList.add(getRoverInstruction());
            String inputStr;
            while(true) {
                System.out.print("Do you have more Rovers? (Y/N): ");
                inputStr = scanner.nextLine().trim().toUpperCase();
                if (inputStr.equals("Y") || inputStr.equals("N")) {
                    break;
                }
            }
            if (inputStr.equals("N")) {
                break;
            }
        }
        return new RoverDataBO(plateauSize,
                roverPositionList, roverInstructionList);
    }

    public void displayRoverFinalCoordinates(ArrayList<String> finalCoordinates){
        int index = 1;
        for (String coordinate : finalCoordinates){
            if (coordinate.length() == 5)
                System.out.println("Rover "+index+" is now deployed at new position "
                        +coordinate);
            else
                System.out.println(coordinate);
            index += 1;
        }
    }
}