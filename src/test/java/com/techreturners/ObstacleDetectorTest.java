package com.techreturners;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ObstacleDetectorTest {
    ObstacleDetector obstacleDetector;
    @BeforeEach
    public void setUp(){
        obstacleDetector = new ObstacleDetector();
    }
    @Test
    public void testDetectObstacles(){
        ArrayList<String> finalCoordinates = new ArrayList<>();
        //When final co-ordinates are same for multi rovers
        finalCoordinates.add(MarsRoverTestData.finalRoverPositions.get(4));
        finalCoordinates.add(MarsRoverTestData.finalRoverPositions.get(4));
        obstacleDetector.detectsObstacle(finalCoordinates);
        assertEquals(MarsRoverTestData.obstacleErrorMessage,
                finalCoordinates.get(1));
    }
}
