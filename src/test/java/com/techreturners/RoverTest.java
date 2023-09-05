package com.techreturners;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoverTest {

    Rover marsRover;
    Plateau plateau;
    MarsRoverTestData marsRoverTestData;
    @Before
    public void setUp(){
        marsRoverTestData = new MarsRoverTestData();
        marsRover = new Rover(marsRoverTestData.initialisePositionObject(),
                MarsRoverTestData.direction,marsRoverTestData.initialiseInstruction());
        plateau = new RectanglePlateau(5, 5);
    }

    @Test
    public void testCalculateNewCoordinates(){
        assertEquals(MarsRoverTestData.finalPositionRoverOne,
                marsRover.calculateNewCoordinates(plateau));
    }
}
