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
        Plateau plateau = new RectanglePlateau(xCoordinate, yCoordinate);
        return plateau;
    }

    public static void main (String[] args){
        UserInterface userInterface = new UserInterface();
        RoverDataBO roverDataBO = userInterface.collectRoverData();
        //MarsRoverApp marsRoverApp = new MarsRoverApp(roverDataBO);
        //marsRoverApp.processRoverData();
    }
}
