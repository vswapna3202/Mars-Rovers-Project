package com.techreturners;

import java.util.ArrayList;
import java.util.Arrays;

public class MarsRoverTestData {
    public static ArrayList<String> plateauSizeList =
            new ArrayList<>(Arrays.asList("5 5","1 1","2 2"));
    public static ArrayList<String> roverPositions =
            new ArrayList<>(Arrays.asList("1 2 N", "3 3 E", "0 0 N", "2 2 S", "3 2 W","3 3 3 N","1 2 N","1 4 N"));
    public static ArrayList<String> roverInstructions =
            new ArrayList<>(Arrays.asList("LMLMLMLMM","MMRMMRMRRM","MM","LR","RM","LML","RMM"));
    public static ArrayList<Integer> xyValues =
            new ArrayList<>(Arrays.asList(5,5,1,2,3,3,2,2,3,2,1,1,0,0,3,4,1,2,1,4));
    public static ArrayList<String> userYesNo =
            new ArrayList<>(Arrays.asList("Y","N"));
    public static ArrayList<String> finalRoverPositions =
            new ArrayList<>(Arrays.asList("1 3 N","5 1 E","2 2 S","3 3 N","1 4 N","1 5 N"));
    public static String[] plateauInvalidData = {
            "5, 5","A B","% $","","1  2  "};
    public static String[] roverPositionsInvalidData = {
            "","1, 2 N","A B 2","MME","£$%","1 3 A","1","1 2 n  "};

    public static String[] roverInstructionsInvalidData = {
            "","ABABABCCC","121212333","%$%$%$£££","L M L M  M ","lml"};

    public static String mainMethodOutputPattern = "Rover 1 is now deployed at new position (.+)";
    public static String obstacleErrorMessage = "Collision intercepted at co-ordinates " +
            "1 4 Rover not deployed as a rover already is already positioned here";

    /*
    Uncomment when testing ObstacleDetectorTest.testReadObstaclePositionsInvalidData
    method
    public static String exceptionMessageObstacle = "Invalid obstacle data in file";
    */
    public RoverDataBO initialiseRoverDataBOObject(boolean multipleFlag){
        ArrayList<String> roverPositionList = new ArrayList<>();
        ArrayList<String> roverInstructionList = new ArrayList<>();
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

    public RoverDataBO initialiseRoverDataForInvalidInput(){
        ArrayList<String> positionList = new ArrayList<>();
        ArrayList<String> instructionList = new ArrayList<>();
        positionList.add(roverPositions.get(5));
        instructionList.add(roverInstructions.get(2));
        return new RoverDataBO(
                plateauSizeList.get(0),
                positionList,
                instructionList);
    }
    public RoverDataBO initialiseRoverDataForBoundary(){
        ArrayList<String> positionList = new ArrayList<>();
        ArrayList<String> instructionList = new ArrayList<>();
        positionList.add(roverPositions.get(2));
        instructionList.add(roverInstructions.get(2));
        return new RoverDataBO(
                plateauSizeList.get(1),
                positionList,
                instructionList);
    }

    public RoverDataBO initialiseRoverDataForObstacleDetection(){
        ArrayList<String> positionList = new ArrayList<>();
        ArrayList<String> instructionList = new ArrayList<>();
        positionList.add(roverPositions.get(6));
        positionList.add(roverPositions.get(7));
        instructionList.add(roverInstructions.get(2));
        instructionList.add(roverInstructions.get(3));
        return new RoverDataBO(
                plateauSizeList.get(0),
                positionList,
                instructionList);
    }


}
