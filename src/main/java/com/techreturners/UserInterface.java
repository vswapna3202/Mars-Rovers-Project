package com.techreturners;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private static final String COMMON_PREFIX = "\\d+\\s+\\d+\\s*";
    private static final String COORDINATE_PATTERN = COMMON_PREFIX +".*";
    private static final String ROVER_POSITION_PATTERN = COMMON_PREFIX+"[NSEWnsew]";
    private static final String ROVER_INSTRUCTION_PATTERN = "^[LRMlrm]+$";
    private static final String[] PROMPTS = {
            "Enter the plateau maximum co-ordinates in format x y : ",
            "Enter the current Rover position co-ordinates in"
                    +" format x y direction e.g. 1 2 N : ",
            "Enter the instruction for rover movement(L,R or M): "
    };

    private final Scanner scanner;
    public UserInterface(){
        scanner = new Scanner(System.in);
    }

    public Scanner getScanner() {
        return scanner;
    }

    public String getPlateauSize(){
        return getInput(0, COORDINATE_PATTERN);
    }

    public String getRoverPosition(){
        return getInput(1,ROVER_POSITION_PATTERN);
    }

    public String getRoverInstruction(){
        return getInput(2, ROVER_INSTRUCTION_PATTERN);
    }

    private String getInput(int index, String pattern){
        boolean isValid = false;
        String inputString = "";
        while(!isValid) {
            System.out.print(PROMPTS[index]);
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