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
    }

    @Test
    public void testCalculateNewCoordinates() throws CustomRoverException{
        /* Testing for valid rover data within boundary and no obstacle
           in direction N and movements Left and Forward
         */
        marsRover = new Rover(new Position(MarsRoverTestData.xyValues.get(2),
                MarsRoverTestData.xyValues.get(3)),
                Direction.N,new Instruction(MarsRoverTestData.roverInstructions.get(0)));
        plateau = new RectanglePlateau(
                MarsRoverTestData.xyValues.get(0),
                MarsRoverTestData.xyValues.get(1));
        assertEquals(MarsRoverTestData.finalRoverPositions.get(0),
                marsRover.calculateNewCoordinates(plateau));
        /* Testing for valid rover data within boundary and no obstacle in
           direction E and movements forward and right
         */
        marsRover = new Rover(new Position(MarsRoverTestData.xyValues.get(4),
                MarsRoverTestData.xyValues.get(5)),
                Direction.E,new Instruction(MarsRoverTestData.roverInstructions.get(1)));
        assertEquals(MarsRoverTestData.finalRoverPositions.get(1),
                marsRover.calculateNewCoordinates(plateau));
        /* Testing for valid rover data within boundary and no obstacle in
           direction S and movements left and right
         */
        marsRover = new Rover(new Position(MarsRoverTestData.xyValues.get(6),
                MarsRoverTestData.xyValues.get(7)),
                Direction.S, new Instruction(MarsRoverTestData.roverInstructions.get(3)));
        assertEquals(MarsRoverTestData.finalRoverPositions.get(2),
                marsRover.calculateNewCoordinates(plateau));
        /* Testing for valid rover data within boundary and no obstacle in
           direction W and movements forward and right
         */
        marsRover = new Rover(new Position(MarsRoverTestData.xyValues.get(8),
                MarsRoverTestData.xyValues.get(9)),
                Direction.W, new Instruction(MarsRoverTestData.roverInstructions.get(4)));
        assertEquals(MarsRoverTestData.finalRoverPositions.get(3),
                marsRover.calculateNewCoordinates(plateau));
    }

    @Test(expected = CustomRoverException.class)
    public void testCalculateNewCoordinatesForBoundaryCrossed()
            throws CustomRoverException{
        marsRover = new Rover(new Position(MarsRoverTestData.xyValues.get(12),
                MarsRoverTestData.xyValues.get(13)),
                Direction.N,
                new Instruction(MarsRoverTestData.roverInstructions.get(2)));
        marsRover.calculateNewCoordinates(
                new RectanglePlateau(MarsRoverTestData.xyValues.get(10),
                        MarsRoverTestData.xyValues.get(10)));
    }

    @Test(expected = CustomRoverException.class)
    public void testCalculateNewCoordinatesForObstacleDetected()
            throws CustomRoverException{
        marsRover = new Rover(new Position(MarsRoverTestData.xyValues.get(8),
                MarsRoverTestData.xyValues.get(9)),
                Direction.W,
                new Instruction(MarsRoverTestData.roverInstructions.get(6)));
        marsRover.calculateNewCoordinates(
                new RectanglePlateau(MarsRoverTestData.xyValues.get(0),
                        MarsRoverTestData.xyValues.get(0)));
    }

}
