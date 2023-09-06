package com.techreturners;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class ObstacleDetectorTest {
    ObstacleDetector obstacleDetector;
    @BeforeEach
    public void setUp(){
        obstacleDetector = new ObstacleDetector();
    }

    @Test
    public void testDetectObstaclesTrue(){
        obstacleDetector.readObstaclePositionsFromFile();
        assertTrue(obstacleDetector.detectsObstacle(
                MarsRoverTestData.xyValues.get(14),
                MarsRoverTestData.xyValues.get(15)));
    }

    @Test
    public void testDetectObstaclesFalse(){
        obstacleDetector.readObstaclePositionsFromFile();
        assertFalse(obstacleDetector.detectsObstacle(
                MarsRoverTestData.xyValues.get(0),
                MarsRoverTestData.xyValues.get(1)));
    }

    @Test
    public void testReadObstaclePositions(){
        obstacleDetector.readObstaclePositionsFromFile();
        assertEquals(obstacleDetector.getObstaclePositions().size(),
                MarsRoverTestData.obstacleFileRowSize);
    }

    /*
    This is to test if obstacles file has invalid data for rover positions
    for example alphabets. To test this comment out the other test cases as
    they would fail and then change obstacles.txt file to have invalid data
    @Test
    public void testReadObstaclePositionsInvalidData() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class,
                        () ->{obstacleDetector.readObstaclePositionsFromFile();});
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(
                MarsRoverTestData.exceptionMessageObstacle));
    }
    */
}
