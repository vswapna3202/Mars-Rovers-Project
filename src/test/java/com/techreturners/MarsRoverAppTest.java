package com.techreturners;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarsRoverAppTest {

    MarsRoverApp marsRoverApp;
    MarsRoverTestData marsRoverTestData;
    RoverDataBO roverDataTestBO;

    @Before
    public void setUp(){
        marsRoverTestData = new MarsRoverTestData();
        roverDataTestBO = marsRoverTestData.initialiseRoverDataBOObject(false);
        marsRoverApp = new MarsRoverApp(roverDataTestBO);
    }

    @Test
    public void testAssignPlateauSize(){
        RectanglePlateau rectanglePlateau =
                (RectanglePlateau) marsRoverApp.assignPlateauSize(roverDataTestBO);
        assertEquals(MarsRoverTestData.x, rectanglePlateau.getMaxX());
        assertEquals(MarsRoverTestData.y, rectanglePlateau.getMaxY());
    }
}
