package com.techreturners;

import java.io.*;
import java.util.ArrayList;

public class ObstacleDetector {
    private ArrayList<Position> obstaclePositions;
    private static final String obstacleFileName = "obstacles.txt";
    public ObstacleDetector(){
        this.obstaclePositions = readObstaclePositionsFromFile(obstacleFileName);
    }

    private ArrayList<Position> readObstaclePositionsFromFile(String obstacleFilePath){
        ArrayList<Position> obstaclePositions = new ArrayList<>();
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(obstacleFileName);
            if (inputStream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    Position position = parseObstaclePosition(line);
                    if (position != null) {
                        obstaclePositions.add(position);
                    }
                }
            }
        }catch(FileNotFoundException fileException){
            System.out.println("File " + obstacleFilePath + " is not found");
        }catch(IOException ioException){
            System.out.println("Obstacle data in file " + obstacleFilePath + "has errors!");
        }
        return obstaclePositions;
    }

    private Position parseObstaclePosition(String line){
        String[] coordinates = line.split(" ");
        if (coordinates.length != 2){
            throw new IllegalArgumentException("Invalid obstacle data in file"+line);
        }
        try{
            int xCoordiante = Integer.parseInt(coordinates[0]);
            int yCoordiante = Integer.parseInt(coordinates[1]);
            return new Position(xCoordiante, yCoordiante);
        }catch(NumberFormatException nfe){
            throw new IllegalArgumentException("Invalid obstacle data in file"+line, nfe);
        }
    }

    public boolean detectsObstacle(int newX
            , int newY){
        for(Position obstaclePosition : obstaclePositions){
            int obstacleX = obstaclePosition.getxCoordinate();
            int obstacleY = obstaclePosition.getyCoordinate();
            if (obstacleX == newX && obstacleY == newY)
                return true;
        }
        return false;
    }
}
