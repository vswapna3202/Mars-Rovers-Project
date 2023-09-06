package com.techreturners;

import java.util.Arrays;

public class Rover extends SpaceVehicles {
    Position position;
    Direction direction;
    Instruction instruction;
    public Rover(Position position, Direction direction, Instruction instruction){
        this.position = position;
        this.direction = direction;
        this.instruction = instruction;
    }

    protected void rotateLeft(){
        int numDirections = Direction.values().length;
        direction = Arrays.stream(Direction.values())
                .filter(d -> d.ordinal() == (direction.ordinal()
                        + numDirections - 1) % numDirections)
                .findFirst()
                .get();
    }

    protected void rotateRight(){
        int numDirections = Direction.values().length;
        direction = Arrays.stream(Direction.values())
                .filter(d -> d.ordinal() == (direction.ordinal()
                        + numDirections + 1) % numDirections)
                .findFirst()
                .get();
    }

    protected void moveForward(Plateau plateau) throws CustomRoverException {
        int currentXCoordinate = position.getxCoordinate();
        int currentYCoordinate = position.getyCoordinate();
        switch (direction) {
            case N -> currentYCoordinate++;
            case W -> currentXCoordinate--;
            case E -> currentXCoordinate++;
            case S -> currentYCoordinate--;
        }
        ObstacleDetector obstacleDetector = new ObstacleDetector();
        if(!plateau.isWithinBounds(currentXCoordinate, currentYCoordinate))
            throw new CustomRoverException("Rover will cross the plateau boundary specified with current instructions. ");
        if (obstacleDetector.detectsObstacle(currentXCoordinate, currentYCoordinate)){
            throw new CustomRoverException("Rover will collide with an obstacle with current instructions.");
        }
        position.setxCoordinate(currentXCoordinate);
        position.setyCoordinate(currentYCoordinate);
    }


    public String calculateNewCoordinates(Plateau plateau) throws CustomRoverException{
        for(char instructionChar : instruction.getInstruction().toCharArray()){
            switch (instructionChar) {
                case 'L' -> rotateLeft();
                case 'R' -> rotateRight();
                case 'M' -> moveForward(plateau);
                default -> throw new IllegalArgumentException("Invalid instruction!");
            }
        }
        return position.getxCoordinate()+" "+position.getyCoordinate()+" "+direction;
    }
}