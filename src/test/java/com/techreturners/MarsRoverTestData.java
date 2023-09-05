package com.techreturners;

import java.util.ArrayList;
import java.util.Arrays;

public class MarsRoverTestData {
    public static ArrayList<String> plateauSizeList =
            new ArrayList<String>(Arrays.asList("5 5","1 1","2 2"));
    public static String plateauSize = "5 5";
    public static String roverOnePosition = "1 2 N";
    public static String roverOneInstruction = "LMLMLMLMM";

    public static String roverTwoPosition = "3 3 E";
    public static String roverTwoInstruction = "MMRMMRMRRM";
    public static String userNo = "N";
    public static String userYes = "Y";
    public static String plateauSizeWithSpaces = "1  2";

    public static ArrayList<String> roverPositions =
            new ArrayList<String>(Arrays.asList("1 2 N","3 3 E","0 0 N"));

    public static ArrayList<String> roverInstructions =
            new ArrayList<String>(Arrays.asList("LMLMLMLMM","MMRMMRMRRM","MM"));
    public static ArrayList<Integer> xyValues =
            new ArrayList<Integer>(Arrays.asList(5,5,1,2,3,3));
    public static ArrayList<String> userYesNo =
            new ArrayList<String>(Arrays.asList("Y","N"));
    public static int x = 5;
    public static int y = 5;

    public static ArrayList<String> finalRoverPositions =
            new ArrayList<String>(Arrays.asList("1 3 N","5 1 E"));
    public static String finalPositionRoverOne = "1 3 N";
    public static String finalPositionRoverTwo = "5 1 E";

    public RoverDataBO initialiseRoverDataBOObject(boolean multipleFlag){
        ArrayList<String> roverPositionList = new ArrayList<String>();
        ArrayList<String> roverInstructionList = new ArrayList<String>();
        roverPositionList.add(roverPositions.get(0));
        roverInstructionList.add(roverInstructions.get(0));
        if (multipleFlag){
            roverPositionList.add(roverPositions.get(1));
            roverInstructionList.add(roverInstructions.get(1));
        }
        return new RoverDataBO(plateauSizeList.get(0),
                roverPositionList, roverInstructionList);
    }

    public Plateau initialisePlateauObject(){
        return new RectanglePlateau(xyValues.get(0),xyValues.get(1));
    }

    public Position initialisePositionObjectForRoverOne(){
        return new Position(xyValues.get(2), xyValues.get(3));
    }

    public Instruction initialiseInstructionForRoverOne(){

        return new Instruction(roverInstructions.get(0));
    }
    public Position initialisePositionObjectForRoverTwo(){
        return new Position(xyValues.get(4), xyValues.get(5));
    }

    public Instruction initialiseInstructionForRoverTwo(){

        return new Instruction(roverInstructions.get(1));
    }
}
