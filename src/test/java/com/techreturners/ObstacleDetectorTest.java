package com.techreturners;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ObstacleDetectorTest {
    @Test
    public void testDetectObstacles(){
        ArrayList<String> finalCoordinates = new ArrayList<>();
        //When final co-ordinates are same for multi rovers
        finalCoordinates.add(MarsRoverTestData.finalRoverPositions.get(4));
        finalCoordinates.add(MarsRoverTestData.finalRoverPositions.get(4));
        ObstacleDetector.detectsObstacle(finalCoordinates);
        assertEquals(MarsRoverTestData.obstacleErrorMessage,
                finalCoordinates.get(1));
    }

    @Test
    public void testDetectObstaclesWithCoordinates(){
        ArrayList<String> finalCoordinates = new ArrayList<>();
        //When final co-ordinates are same for multi rovers
        finalCoordinates.add(MarsRoverTestData.finalRoverPositions.get(4));
        int currentXCoordinate = 1;
        int currentYCoordinate = 4;
        ObstacleDetector.detectsObstacle(finalCoordinates,
                currentXCoordinate,
                currentYCoordinate);
        assertEquals(MarsRoverTestData.obstacleErrorMessageWhenRoverInWay,
                finalCoordinates.get(1));
    }
}
