package com.techreturners;

import java.util.ArrayList;

public class MarsRoverTestData {
    public static String plateauSize = "5 5";
    public static String roverOnePosition = "1 2 N";
    public static String roverOneInstruction = "LMLMLMLMM";

    public static String roverTwoPosition = "3 3 E";
    public static String roverTwoInstruction = "MMRMMRMRRM";
    public static String plateauSizeWithSpaces = "1  2";
    public RoverDataBO marsRoverDataBOTest;

    public static String userNo = "N";
    public static String userYes = "Y";

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
}
