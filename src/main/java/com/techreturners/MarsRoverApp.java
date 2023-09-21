package com.techreturners;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class MarsRoverApp {

    private RoverDataBO roverDataBO;
    public MarsRoverApp(){}

    public Plateau assignPlateauSize(RoverDataBO roverDataBO){
        String plateauSize = roverDataBO.getPlateauSize();
        StringTokenizer tokenizer = new StringTokenizer(plateauSize);
        int xCoordinate = Integer.parseInt(tokenizer.nextToken());
        int yCoordinate = Integer.parseInt(tokenizer.nextToken());
        return new RectanglePlateau(xCoordinate, yCoordinate);
    }

    public ArrayList<String> assignRoverDataAndMoveRover(Plateau plateau)
            throws CustomRoverException{
        ArrayList<String> finalCoordinates = new ArrayList<>();
        ArrayList<String> roverPositionsList = roverDataBO.getRoverPositionsList();
        ArrayList<String> roverInstructionList = roverDataBO.getRoverInstructionsList();
        for(String roverPositionString : roverPositionsList) {
            String[] roverPositionParts = roverPositionString.split("\\s");
            validateRoverPositionParts(roverPositionParts);
            Position roverPosition = createRoverPosition(roverPositionParts);
            Direction roverDirection = createRoverDirection(roverPositionParts);
            Instruction roverInstruction = new Instruction(roverInstructionList.get(
                    roverPositionsList.indexOf(roverPositionString)));

            Rover marsRover = new Rover(roverPosition,
                    roverDirection, roverInstruction);
            marsRover.setFinalCoordinates(finalCoordinates);
            String coordinateString = marsRover.calculateNewCoordinates(plateau);
            addCoordinateIfNotEmpty(finalCoordinates, coordinateString);
        }
        detectAndHandleObstacles(finalCoordinates);
        return finalCoordinates;
    }

    private void detectAndHandleObstacles(ArrayList<String> finalCoordinates){
        ObstacleDetector.detectsObstacle(finalCoordinates);
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

    private Position createRoverPosition(String[] roverPositionParts){
        int newXCoordinate = Integer.parseInt(roverPositionParts[0]);
        int newYCoordinate = Integer.parseInt(roverPositionParts[1]);
        return new Position(newXCoordinate, newYCoordinate);
    }

    private void validateRoverPositionParts(String[] roverPositionParts) throws
            IllegalArgumentException {
        if (roverPositionParts.length != 3) {
            throw new IllegalArgumentException("Invalid Input positons!");
        }
    }

    public ArrayList<String> processRoverData() throws CustomRoverException{
        Plateau plateau = assignPlateauSize(roverDataBO);
        return assignRoverDataAndMoveRover(plateau);

    }

    public void startApplication(){
        boolean isValidScenario = false;
        UserInterface userInterface;
        while(!isValidScenario) {
            try {
                userInterface = new UserInterface();
                RoverDataBO roverDataBO = userInterface.collectRoverData();
                setRoverDataBO(roverDataBO);

                userInterface.displayRoverFinalCoordinates(processRoverData());
                isValidScenario = true;
                userInterface.getScanner().close();
            } catch (CustomRoverException cre) {
                System.out.println(" Please Re-enter Rover data! " + cre.getMessage());
            }
        }
    }
    public void setRoverDataBO(RoverDataBO roverDataBO) {
        this.roverDataBO = roverDataBO;
    }

    public static void main (String[] args){
        MarsRoverApp marsRoverApp = new MarsRoverApp();
        marsRoverApp.startApplication();
    }
}
