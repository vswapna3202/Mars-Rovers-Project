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
        marsRover = new Rover(marsRoverTestData.initialisePositionObjectForRoverOne(),
                Direction.N,marsRoverTestData.initialiseInstructionForRoverOne());
        plateau = new RectanglePlateau(5, 5);
    }

    @Test
    public void testCalculateNewCoordinates() throws CustomRoverException{
        assertEquals(MarsRoverTestData.finalPositionRoverOne,
                marsRover.calculateNewCoordinates(plateau));
        marsRover = new Rover(marsRoverTestData.initialisePositionObjectForRoverTwo(),
                Direction.E,marsRoverTestData.initialiseInstructionForRoverTwo());
        assertEquals(MarsRoverTestData.finalPositionRoverTwo,
                marsRover.calculateNewCoordinates(plateau));
    }
}
