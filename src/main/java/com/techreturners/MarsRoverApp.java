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
        for(int i=0; i< roverPositionsList.size(); i++){
            String[] parts = roverPositionsList.get(i).split("\\s");
            if (parts.length == 3){
                int newXCoordinate = Integer.parseInt(parts[0]);
                int newYCoordinate = Integer.parseInt(parts[1]);
                Position roverPosition = new Position(newXCoordinate,
                        newYCoordinate);
                Direction roverDirection = Direction.valueOf(parts[2]);
                Instruction roverInstruction = new Instruction(roverInstructionList.get(i));
                Rover marsRover = new Rover(roverPosition,
                        roverDirection,roverInstruction);
                finalCoordinates.add(marsRover.calculateNewCoordinates(plateau));
            }else{
                throw new IllegalArgumentException("Invalid Input of Positions!");
            }
        }
        ObstacleDetector obstacleDetector = new ObstacleDetector();
        if (obstacleDetector.detectsObstacle(finalCoordinates)){
            throw new CustomRoverException
                    ("Rover will collide with an obstacle with current instructions.");
        }
        return finalCoordinates;
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
