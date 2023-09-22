package com.techreturners;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class MarsRoverApp {

    public static final String COLLISION_STRING = "Collision";
    private RoverDataBO roverDataBO;
    public MarsRoverApp(){}

    public Plateau assignPlateauSize(RoverDataBO roverDataBO)
            throws CustomRoverException{
        int xCoordinate;
        int yCoordinate;
        String plateauSize = roverDataBO.getPlateauSize();
        StringTokenizer tokenizer = new StringTokenizer(plateauSize);
        try {
            xCoordinate = Integer.parseInt(tokenizer.nextToken());
            yCoordinate = Integer.parseInt(tokenizer.nextToken());
        }catch(NumberFormatException nfe){
            throw new CustomRoverException("Invalid inputs entered for Plateau"+
            " co-ordinates. Please enter integers only");
        }
        return new RectanglePlateau(xCoordinate, yCoordinate);
    }

    public ArrayList<String> assignRoverDataAndMoveRover(Plateau plateau)
            throws CustomRoverException{
        ArrayList<String> finalCoordinates = new ArrayList<>();
        ArrayList<String> roverPositionsList = roverDataBO.getRoverPositionsList();
        ArrayList<String> roverInstructionList = roverDataBO.getRoverInstructionsList();
        for(String roverPositionString : roverPositionsList) {
            String[] roverPositionParts = roverPositionString.split("\\s");
            Instruction roverInstruction = new Instruction(roverInstructionList.get(
                    roverPositionsList.indexOf(roverPositionString)));
            assignNewRoverCoordinates(plateau, roverPositionParts, roverInstruction, finalCoordinates);
        }
        return finalCoordinates;
    }

    private void assignNewRoverCoordinates(Plateau plateau,
                                           String[] roverPositionParts,
                                           Instruction roverInstruction,
                                           ArrayList<String> finalCoordinates)
            throws CustomRoverException {
        validateRoverPositionParts(roverPositionParts);
        Position roverPosition = createRoverPosition(roverPositionParts);
        Direction roverDirection = createRoverDirection(roverPositionParts);

        Rover marsRover = new Rover(roverPosition,
                roverDirection, roverInstruction);
        marsRover.setFinalCoordinates(finalCoordinates);
        String coordinateString = marsRover.calculateNewCoordinates(plateau);
        addCoordinateIfNotEmpty(finalCoordinates, coordinateString);
    }

    private void addCoordinateIfNotEmpty(ArrayList<String> finalCoordinates,
                                         String coordinateString){
        if(!coordinateString.isEmpty()){
            finalCoordinates.add(coordinateString);
        }
    }

    private Direction createRoverDirection(String[] roverPositionParts){
        return Direction.valueOf(roverPositionParts[2]);
    }

    private Position createRoverPosition(String[] roverPositionParts) throws
            CustomRoverException{
        int newXCoordinate;
        int newYCoordinate;
        try {
            newXCoordinate = Integer.parseInt(roverPositionParts[0]);
            newYCoordinate = Integer.parseInt(roverPositionParts[1]);
        }catch(NumberFormatException nfe){
            throw new CustomRoverException("Rover position co-ordinates entered are invalid. Please" +
                    " enter valid integers only");
        }
        return new Position(newXCoordinate, newYCoordinate);
    }

    private void validateRoverPositionParts(String[] roverPositionParts) throws
            IllegalArgumentException {
        if (roverPositionParts.length != 3) {
            throw new IllegalArgumentException("Invalid Input positions!");
        }
    }

    public ArrayList<String> processRoverData() throws CustomRoverException{
        Plateau plateau = assignPlateauSize(roverDataBO);
        return assignRoverDataAndMoveRover(plateau);

    }

    public void startApplication(){
        boolean isValidScenario = false;
        UserInterface userInterface;
        ArrayList<String> finalCoordinates;
        while(!isValidScenario) {
            try {
                userInterface = new UserInterface();
                RoverDataBO roverDataBO = userInterface.collectRoverData();
                setRoverDataBO(roverDataBO);
                finalCoordinates = processRoverData();
                userInterface.displayRoverFinalCoordinates(finalCoordinates);
                isValidScenario = true;
                continueRoverMovement(userInterface, finalCoordinates);
            } catch (CustomRoverException cre) {
                System.out.println(" Please Re-enter Rover data as invalid data has been entered! "
                        + cre.getMessage());
            }
        }
    }

    public void continueRoverMovement(UserInterface userInterface,
                                      ArrayList<String> roverCoordinates)
        throws CustomRoverException{
        boolean continueRoverMovement =
                userInterface.continueRoverMovementUserInput(3, -1);

        Plateau plateau = assignPlateauSize(this.roverDataBO);
        while(continueRoverMovement){
            ArrayList<String> newCoordinates = new ArrayList<>();
            int j = 0;
            for(int i = 0; i < roverCoordinates.size(); i++ ) {
                if (!roverCoordinates.get(i).contains(COLLISION_STRING)) {
                    boolean instructionYes =
                            userInterface.continueRoverMovementUserInput(
                                    4, (i + 1));
                    if (instructionYes) {
                        String[] roverPositionParts =
                                roverCoordinates.get(i).split("\\s");
                        Instruction roverInstruction =
                                new Instruction(userInterface.getRoverInstruction());
                        assignNewRoverCoordinates(plateau, roverPositionParts, roverInstruction, newCoordinates);
                    } else {
                        newCoordinates.add(roverCoordinates.get(i));
                    }
                    roverCoordinates.set(i, newCoordinates.get(j));
                    j++;
                    userInterface.displayRoverFinalCoordinates(newCoordinates);
                }
            }
            continueRoverMovement = userInterface.continueRoverMovementUserInput(3, -1);
        }
        userInterface.getScanner().close();
    }

    public void setRoverDataBO(RoverDataBO roverDataBO) {
        this.roverDataBO = roverDataBO;
    }

    public static void main (String[] args){
        MarsRoverApp marsRoverApp = new MarsRoverApp();
        marsRoverApp.startApplication();
    }
}
