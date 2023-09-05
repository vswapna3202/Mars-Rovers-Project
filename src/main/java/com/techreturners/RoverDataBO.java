package com.techreturners;

import java.util.ArrayList;

public class RoverDataBO {
    private String plateauSize;
    private ArrayList<String> roverPositionsList;
    private ArrayList<String> roverInstructionsList;

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
