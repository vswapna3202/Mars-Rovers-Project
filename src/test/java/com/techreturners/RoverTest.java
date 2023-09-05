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
        marsRover = new Rover(new Position(MarsRoverTestData.xyValues.get(2),
                MarsRoverTestData.xyValues.get(3)),
                Direction.N,new Instruction(MarsRoverTestData.roverInstructions.get(0)));
        plateau = new RectanglePlateau(
                MarsRoverTestData.xyValues.get(0),
                MarsRoverTestData.xyValues.get(1));
    }

    @Test
    public void testCalculateNewCoordinates() throws CustomRoverException{
        assertEquals(MarsRoverTestData.finalRoverPositions.get(0),
                marsRover.calculateNewCoordinates(plateau));
        marsRover = new Rover(new Position(MarsRoverTestData.xyValues.get(4),
                MarsRoverTestData.xyValues.get(5)),
                Direction.E,new Instruction(MarsRoverTestData.roverInstructions.get(1)));
        assertEquals(MarsRoverTestData.finalRoverPositions.get(1),
                marsRover.calculateNewCoordinates(plateau));
        marsRover = new Rover(new Position(MarsRoverTestData.xyValues.get(6),
                MarsRoverTestData.xyValues.get(7)),
                Direction.S, new Instruction(MarsRoverTestData.roverInstructions.get(3)));
        assertEquals(MarsRoverTestData.finalRoverPositions.get(2),
                marsRover.calculateNewCoordinates(plateau));
        marsRover = new Rover(new Position(MarsRoverTestData.xyValues.get(8),
                MarsRoverTestData.xyValues.get(9)),
                Direction.W, new Instruction(MarsRoverTestData.roverInstructions.get(4)));
        assertEquals(MarsRoverTestData.finalRoverPositions.get(3),
                marsRover.calculateNewCoordinates(plateau));
    }
}
