package com.techreturners;

import java.io.*;
import java.util.ArrayList;

public class ObstacleDetector {

    private ArrayList<Position> obstaclePositions;
    private static final String OBSTACLE_FILE_NAME = "obstacles.txt";
    public ObstacleDetector(){
        obstaclePositions = new ArrayList<>();
    }

    public ArrayList<Position> getObstaclePositions() {
        return obstaclePositions;
    }

    public void readObstaclePositionsFromFile(){
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(OBSTACLE_FILE_NAME);
            if (inputStream == null) {
                throw new FileNotFoundException("File " + OBSTACLE_FILE_NAME + " is not found");
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                Position position = parseObstaclePosition(line);
                obstaclePositions.add(position);
            }
            reader.close();
            inputStream.close();
        }catch(FileNotFoundException fileException){
            System.out.println("File " + OBSTACLE_FILE_NAME + " is not found");
        }catch(IOException ioException){
            System.out.println("Obstacle data in file " + OBSTACLE_FILE_NAME + "has errors!");
        }
    }

    private Position parseObstaclePosition(String line){
        String[] coordinates = line.split(" ");
        if (coordinates.length != 2){
            throw new IllegalArgumentException("Invalid obstacle data in file"+line);
        }
        try{
            int xCoordinate = Integer.parseInt(coordinates[0]);
            int yCoordinate = Integer.parseInt(coordinates[1]);
            return new Position(xCoordinate, yCoordinate);
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

    public boolean detectsObstacle(ArrayList<String> finalCoordinates){
        if (finalCoordinates.size() != 1) {
            for (int k = 0; k < finalCoordinates.size()-1; k++) {
                for (int j = k+1; j < finalCoordinates.size(); j++) {
                    if (finalCoordinates.get(k).equals(finalCoordinates.get(j))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
