package com.techreturners;

import java.util.ArrayList;

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
        switch(direction){
            case N:
                direction = Direction.W;
                break;
            case W:
                direction = Direction.S;
                break;
            case S:
                direction = Direction.E;
                break;
            case E:
                direction = Direction.N;
                break;
        }
    }

    protected void rotateRight(){
        switch(direction){
            case N:
                direction = Direction.E;
                break;
            case E:
                direction = Direction.S;
                break;
            case S:
                direction = Direction.W;
                break;
            case W:
                direction = Direction.N;
                break;
        }
    }

    protected void moveForward(Plateau plateau) throws CustomRoverException {
        int currentXCoordinate = position.getxCoordinate();
        int currentYCoordinate = position.getyCoordinate();
        switch(direction){
            case N:
                currentYCoordinate++;
                break;
            case W:
                currentXCoordinate--;
                break;
            case E:
                currentXCoordinate++;
                break;
            case S:
                currentYCoordinate--;
                break;
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
            switch(instructionChar){
                case 'L':
                    rotateLeft();
                    break;
                case 'R':
                    rotateRight();
                    break;
                case 'M':
                    moveForward(plateau);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid instruction!");
            }
        }
        String newCoordinates = position.getxCoordinate()+" "+
                position.getyCoordinate()+" "+direction;
        return newCoordinates;
    }
}
