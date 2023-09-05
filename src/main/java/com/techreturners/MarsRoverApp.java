package com.techreturners;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class MarsRoverApp {
    RoverDataBO roverDataBO;
    public MarsRoverApp(RoverDataBO roverDataBO){
        this.roverDataBO = roverDataBO;
    }

    public Plateau assignPlateauSize(RoverDataBO roverDataBO){
        String plateauSize = roverDataBO.getPlateauSize();
        StringTokenizer tokenizer = new StringTokenizer(plateauSize);
        int xCoordinate = Integer.parseInt(tokenizer.nextToken());
        int yCoordinate = Integer.parseInt(tokenizer.nextToken());
        return new RectanglePlateau(xCoordinate, yCoordinate);
    }

    public ArrayList<String> assignRoverDataAndMoveRover(Plateau plateau){
        ArrayList<String> finalCoordinates = new ArrayList<String>();
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
        return finalCoordinates;
    }

    public ArrayList<String> processRoverData(){
        Plateau plateau = assignPlateauSize(roverDataBO);
        return assignRoverDataAndMoveRover(plateau);
    }

    private void displayRoverFinalCoordinates(ArrayList<String> finalCoordinates){
        int index = 1;
        for (String coordinate : finalCoordinates){
            System.out.println("Rover "+index+" is now at new position "+coordinate);
            index += 1;
        }
    }

    public static void main (String[] args){
        UserInterface userInterface = new UserInterface();
        RoverDataBO roverDataBO = userInterface.collectRoverData();
        MarsRoverApp marsRoverApp = new MarsRoverApp(roverDataBO);
        marsRoverApp.displayRoverFinalCoordinates(marsRoverApp.processRoverData());
    }
}
