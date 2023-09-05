package com.techreturners;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarsRoverAppTest {

    MarsRoverApp marsRoverApp;
    MarsRoverTestData marsRoverTestData;
    RoverDataBO roverOneDataTestBO;
    RoverDataBO roverTwoDataTestBO;
    Plateau plateau;

    @Before
    public void setUp(){
        marsRoverTestData = new MarsRoverTestData();
        roverOneDataTestBO = marsRoverTestData.initialiseRoverDataBOObject(false);
        roverTwoDataTestBO = marsRoverTestData.initialiseRoverDataBOObject(false);
        marsRoverApp = new MarsRoverApp(roverOneDataTestBO);
        plateau = marsRoverTestData.initialisePlateauObject();
    }

    @Test
    public void testAssignPlateauSize(){
        RectanglePlateau rectanglePlateau =
                (RectanglePlateau) marsRoverApp.assignPlateauSize(roverOneDataTestBO);
        assertEquals(MarsRoverTestData.x, rectanglePlateau.getMaxX());
        assertEquals(MarsRoverTestData.y, rectanglePlateau.getMaxY());
    }

    @Test
    public void testAssignRoverDataAndMoveRover() throws CustomRoverException{
        ArrayList<String> finalCoordinates =
                marsRoverApp.assignRoverDataAndMoveRover(plateau);
        assertEquals(MarsRoverTestData.finalPositionRoverOne,
                finalCoordinates.get(0));
    }

    @Test
    public void testProcessRoverData() throws CustomRoverException{
        ArrayList<String> finalCoordinates = marsRoverApp.processRoverData();
        assertEquals(MarsRoverTestData.finalPositionRoverOne,
                finalCoordinates.get(0));

    }

}
