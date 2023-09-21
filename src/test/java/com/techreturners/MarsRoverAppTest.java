package com.techreturners;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class MarsRoverAppTest {

    MarsRoverApp marsRoverApp;
    MarsRoverTestData marsRoverTestData;
    RoverDataBO roverOneDataTestBO;
    RoverDataBO roverTwoDataTestBO;
    Plateau plateau;
    ByteArrayOutputStream outputStream;
    PrintStream originalOut;

    @Before
    public void setUp(){
        marsRoverTestData = new MarsRoverTestData();
        plateau = marsRoverTestData.initialisePlateauObject();
        roverOneDataTestBO = marsRoverTestData.initialiseRoverDataBOObject(false);
        roverTwoDataTestBO = marsRoverTestData.initialiseRoverDataBOObject(true);
        marsRoverApp = new MarsRoverApp();
        marsRoverApp.setRoverDataBO(roverOneDataTestBO);
    }

    @After
    public void tearDown() {
        // Restore the original System.out after the test is complete
        System.setOut(originalOut);
    }

    @Test
    public void testAssignPlateauSizeValid() throws CustomRoverException{
        RectanglePlateau rectanglePlateau =
                (RectanglePlateau) marsRoverApp.assignPlateauSize(roverOneDataTestBO);
        assertEquals(MarsRoverTestData.xyValues.get(0), rectanglePlateau.getMaxX());
        assertEquals(MarsRoverTestData.xyValues.get(1), rectanglePlateau.getMaxY());
    }

    @Test
    public void testAssignRoverDataAndMoveRoverSingleRover()
            throws CustomRoverException{
        ArrayList<String> finalCoordinates =
                marsRoverApp.assignRoverDataAndMoveRover(plateau);
        assertFinalCoordinates(finalCoordinates);
    }

    @Test
    public void testAssignRoverDataAndMoveRoverMultiRovers()
            throws CustomRoverException{
        marsRoverApp = new MarsRoverApp();
        marsRoverApp.setRoverDataBO(roverTwoDataTestBO);
        ArrayList<String> finalCoordinates =
                marsRoverApp.assignRoverDataAndMoveRover(plateau);
        assertFinalCoordinates(finalCoordinates);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssignRoverDataAndMoveRoverCheckInvalidInput()
            throws CustomRoverException{
        roverOneDataTestBO = marsRoverTestData.initialiseRoverDataForInvalidInput();
        marsRoverApp = new MarsRoverApp();
        marsRoverApp.setRoverDataBO(roverOneDataTestBO);

        marsRoverApp.assignRoverDataAndMoveRover(
                new RectanglePlateau(MarsRoverTestData.xyValues.get(10),
                        MarsRoverTestData.xyValues.get(11)));
    }

    @Test(expected = CustomRoverException.class)
    public void testAssignRoverDataAndMoveRoverCheckExceptionInvalidBoundary()
            throws CustomRoverException{
        roverOneDataTestBO = marsRoverTestData.initialiseRoverDataForBoundary();
        marsRoverApp = new MarsRoverApp();
        marsRoverApp.setRoverDataBO(roverOneDataTestBO);

        marsRoverApp.assignRoverDataAndMoveRover(
                new RectanglePlateau(MarsRoverTestData.xyValues.get(10),
                MarsRoverTestData.xyValues.get(11)));
    }

    @Test
    public void testAssignRoverDataAndMoveRoverCheckExceptionObstacleDetected()
            throws CustomRoverException{
        roverOneDataTestBO = marsRoverTestData.initialiseRoverDataForObstacleDetection();
        marsRoverApp = new MarsRoverApp();
        marsRoverApp.setRoverDataBO(roverOneDataTestBO);

        ArrayList<String> result = marsRoverApp.assignRoverDataAndMoveRover(
                new RectanglePlateau(MarsRoverTestData.xyValues.get(0),
                        MarsRoverTestData.xyValues.get(1)));
        assertEquals(MarsRoverTestData.obstacleErrorMessage, result.get(1));
    }

    private static void assertFinalCoordinates(
            ArrayList<String> finalCoordinates) {
        for (int i=0; i < finalCoordinates.size(); i++) {
            assertEquals(MarsRoverTestData.finalRoverPositions.get(i),
                    finalCoordinates.get(i));
        }
    }

    @Test
    public void testProcessRoverDataSingleRover()
            throws CustomRoverException{
        ArrayList<String> finalCoordinates = marsRoverApp.processRoverData();
        assertFinalCoordinates(finalCoordinates);
    }

    @Test(expected = CustomRoverException.class )
    public void testProcessRoverDataRoverCheckExceptionThrownIfBoundaryCrossed()
            throws CustomRoverException{
        roverOneDataTestBO = marsRoverTestData.initialiseRoverDataForBoundary();
        marsRoverApp = new MarsRoverApp();
        marsRoverApp.setRoverDataBO(roverOneDataTestBO);
        marsRoverApp.processRoverData();
    }

    @Test
    public void testProcessRoverDataRoverCheckExceptionThrownIfObstacleDetected()
            throws CustomRoverException{
        roverOneDataTestBO =
                marsRoverTestData.initialiseRoverDataForObstacleDetection();
        marsRoverApp = new MarsRoverApp();
        marsRoverApp.setRoverDataBO(roverOneDataTestBO);
        ArrayList<String> result = marsRoverApp.processRoverData();
        assertEquals(MarsRoverTestData.obstacleErrorMessage, result.get(1));
    }

    @Test
    public void testProcessRoverDataMultiRovers() throws CustomRoverException{
        marsRoverApp = new MarsRoverApp();
        marsRoverApp.setRoverDataBO(roverTwoDataTestBO);
        ArrayList<String> finalCoordinates = marsRoverApp.processRoverData();
        assertFinalCoordinates(finalCoordinates);
    }

    @Test
    public void testMainValidData(){
        originalOut = System.out;
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        //Simulating input for testing main method
        String simulatedInput = String.join("\n",
                MarsRoverTestData.plateauSizeList.get(0),
                MarsRoverTestData.roverPositions.get(0),
                MarsRoverTestData.roverInstructions.get(0),
                MarsRoverTestData.userYesNo.get(1));

        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        MarsRoverApp.main(new String[0]);
        String consoleOutput = outputStream.toString();

        Pattern pattern = Pattern.compile(MarsRoverTestData.mainMethodOutputPattern);
        Matcher matcher = pattern.matcher(consoleOutput);
        assertTrue(matcher.find());
        String actualOutput = matcher.group(1);

        assertEquals(MarsRoverTestData.finalRoverPositions.get(0), actualOutput);

    }
}
