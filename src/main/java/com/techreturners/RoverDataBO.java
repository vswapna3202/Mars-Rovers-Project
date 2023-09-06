package com.techreturners;

import java.util.ArrayList;

public class RoverDataBO {
    private final String plateauSize;
    private final ArrayList<String> roverPositionsList;
    private final ArrayList<String> roverInstructionsList;

    public RoverDataBO(String plateauSize,
                       ArrayList<String > roverPositionsList,
                       ArrayList<String> roverInstructionList){
        this.plateauSize = plateauSize;
        this.roverPositionsList = roverPositionsList;
        this.roverInstructionsList = roverInstructionList;
    }

    public String getPlateauSize() {
        return plateauSize;
    }

    public ArrayList<String> getRoverPositionsList() {
        return roverPositionsList;
    }

    public ArrayList<String> getRoverInstructionsList() {
        return roverInstructionsList;
    }
}
