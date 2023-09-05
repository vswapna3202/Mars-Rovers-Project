package com.techreturners;

import java.util.ArrayList;

public class MarsRoverTestData {
    public static String plateauSize = "5 5";
    public static String roverOnePosition = "1 2 N";
    public static String roverOneInstruction = "LMLMLMLMM";

    public static String roverTwoPosition = "3 3 E";
    public static String roverTwoInstruction = "MMRMMRMRRM";
    public static String plateauSizeWithSpaces = "1  2";
    public static String userNo = "N";
    public static String userYes = "Y";
    public static int x = 5;
    public static int y = 5;

    public static String finalPositionRoverOne = "1 3 N";
    public static String finalPositionRoverTwo = "5 1 E";

    public static Direction directionNorth = Direction.N;
    public static Direction directionEast = Direction.E;


    public RoverDataBO initialiseRoverDataBOObject(boolean multipleFlag){
        ArrayList<String> roverPositionList = new ArrayList<String>();
        ArrayList<String> roverInstructionList = new ArrayList<String>();
        roverPositionList.add(roverOnePosition);
        roverInstructionList.add(roverOneInstruction);
        if (multipleFlag){
            roverPositionList.add(roverTwoPosition);
            roverInstructionList.add(roverTwoInstruction);
        }
        return new RoverDataBO(plateauSize,
                roverPositionList, roverInstructionList);
    }

    public Plateau initialisePlateauObject(){
        return new RectanglePlateau(x, y);
    }

    public Position initialisePositionObjectForRoverOne(){
        return new Position(1, 2);
    }

    public Instruction initialiseInstructionForRoverOne(){
        return new Instruction(roverOneInstruction);
    }
    public Position initialisePositionObjectForRoverTwo(){
        return new Position(3, 3);
    }

    public Instruction initialiseInstructionForRoverTwo(){
        return new Instruction(roverTwoInstruction);
    }
}
