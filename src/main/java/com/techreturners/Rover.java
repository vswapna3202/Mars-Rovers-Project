package com.techreturners;

import java.util.ArrayList;
import java.util.Arrays;

public class Rover extends SpaceVehicles {
    private final Position position;
    private Direction direction;
    private final Instruction instruction;

    private ArrayList<String> finalCoordinates;
    public Rover(Position position, Direction direction, Instruction instruction){
        this.position = position;
        this.direction = direction;
        this.instruction = instruction;
    }

    public void setFinalCoordinates(ArrayList<String> finalCoordinates) {
        this.finalCoordinates = finalCoordinates;
    }

    @Override
    protected void rotateLeft(){
        int numDirections = Direction.values().length;
        Arrays.stream(Direction.values())
                .filter(d -> d.ordinal() == (direction.ordinal()
                        + numDirections - 1) % numDirections)
                .findFirst()
                .ifPresent(newDirection -> direction = newDirection);
    }

    @Override
    protected void rotateRight(){
        int numDirections = Direction.values().length;
        Arrays.stream(Direction.values())
                .filter(d -> d.ordinal() == (direction.ordinal()
                        + numDirections + 1) % numDirections)
                .findFirst()
                .ifPresent(newDirection -> direction = newDirection);
    }

    @Override
    protected boolean moveForward(Plateau plateau)
            throws CustomRoverException {
        int currentXCoordinate = position.getxCoordinate();
        int currentYCoordinate = position.getyCoordinate();
        switch (direction) {
            case N -> currentYCoordinate++;
            case W -> currentXCoordinate--;
            case E -> currentXCoordinate++;
            case S -> currentYCoordinate--;
        }

        if(!plateau.isWithinBounds(currentXCoordinate, currentYCoordinate))
            throw new CustomRoverException("Rover will cross the plateau boundary specified with current instructions. ");
        position.setxCoordinate(currentXCoordinate);
        position.setyCoordinate(currentYCoordinate);
        //If user does not choose move forward still the obstacles are checked
        return(ObstacleDetector.detectsObstacle(this.finalCoordinates,
                currentXCoordinate,
                currentYCoordinate));
    }


    public String calculateNewCoordinates(Plateau plateau)
            throws CustomRoverException{
        boolean obstacleFlag = false;
        if (!this.finalCoordinates.isEmpty() && this.finalCoordinates.size() >= 1){
             obstacleFlag = ObstacleDetector.detectsObstacle(this.finalCoordinates,
                    position.getxCoordinate(), position.getyCoordinate());
             if (obstacleFlag) return "";
        }
        for(char instructionChar : instruction.getInstruction().toCharArray()){
            switch (instructionChar) {
                case 'L' -> rotateLeft();
                case 'R' -> rotateRight();
                case 'M' -> obstacleFlag = moveForward(plateau);
                default -> throw new IllegalArgumentException("Invalid instruction!");
            }
            // No need to do further instructions as an obstacle found in path
            // rover cannot be moved
            if (obstacleFlag) break;
        }
        //If obstacles do not exist return new coordinates with direction
        //else return an empty string
        if (!obstacleFlag)
            return position.getxCoordinate()+" "+position.getyCoordinate()+" "+direction;
        else
            return "";
    }
}
