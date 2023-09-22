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
            "Enter the instruction for rover movement(L,R or M): ",
            "Do you want to continue moving rover(s) further?(Y/N): ",
            "Do you want to give instruction for Rover: ",
            " (Y/N): "
    };
    public static final String INPUT_INSTRUCTIONS_TO_USER = "Invalid input entered. The inputs should be as follows:" +
            "\nPlateau Co-ordinates: x y [where x and y should be any valid integers]" +
            "\nRover Co-ordinates: x y Direction [where x and y are any valid integers," +
            " Direction can be any of the following N,S,E,W,n,s,e,w]" +
            "\nRover Instructions: instructions [where instructions can be L,R,M,l,r,m entered one after other" +
            " without any spaces like lrlrlrr]";
    public static final String MORE_ROVERS_Y_N = "Do you have more Rovers? (Y/N): ";
    public static final String ENTER_EITHER_Y_OR_N = "Please enter either Y or N: ";
    public static final String INPUT_YES = "Y";
    public static final String INPUT_NO = "N";
    public static final String COLLISION_STRING = "Collision";

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
            if(!isValid){
                System.out.println(INPUT_INSTRUCTIONS_TO_USER);
            }
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
                System.out.print(MORE_ROVERS_Y_N);
                inputStr = scanner.nextLine().trim().toUpperCase();
                if (inputStr.equals(INPUT_YES) || inputStr.equals(INPUT_NO)) {
                    break;
                }else{
                    System.out.print(ENTER_EITHER_Y_OR_N);
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
            if (!coordinate.contains(COLLISION_STRING))
                System.out.println("Rover "+index+" is now deployed at new position "
                        +coordinate);
            else
                System.out.println(coordinate);
            index += 1;
        }
    }

    public boolean continueRoverMovementUserInput(int index, int currentRoverNo){
        if (currentRoverNo == -1) {
            System.out.print(PROMPTS[index]);
        }else{
            System.out.print(PROMPTS[index]+currentRoverNo+PROMPTS[5]);
        }
        String inputStr = scanner.nextLine().trim().toUpperCase();
        while(!inputStr.equals(INPUT_YES) && !inputStr.equals(INPUT_NO)) {
            System.out.print("Please enter Y or N as inputs: ");
            inputStr = scanner.nextLine().trim().toUpperCase();
        }
        return inputStr.equals(INPUT_YES);
    }
}