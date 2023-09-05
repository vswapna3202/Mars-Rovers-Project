package com.techreturners;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
    PrintStream printStream;
    PrintStream originalOut;

    @Before
    public void setUp(){
        marsRoverTestData = new MarsRoverTestData();
        plateau = marsRoverTestData.initialisePlateauObject();
        roverOneDataTestBO = marsRoverTestData.initialiseRoverDataBOObject(false);
        roverTwoDataTestBO = marsRoverTestData.initialiseRoverDataBOObject(true);
        marsRoverApp = new MarsRoverApp(roverOneDataTestBO);
    }

    @Rule
    public ExpectedException exception =
            ExpectedException.none();

    @Test
    public void testAssignPlateauSizeValid(){
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
        marsRoverApp = new MarsRoverApp(roverTwoDataTestBO);
        ArrayList<String> finalCoordinates =
                marsRoverApp.assignRoverDataAndMoveRover(plateau);
        assertFinalCoordinates(finalCoordinates);
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

    @Test
    public void testProcessRoverDataMultiRovers() throws CustomRoverException{
        marsRoverApp = new MarsRoverApp(roverTwoDataTestBO);
        ArrayList<String> finalCoordinates = marsRoverApp.processRoverData();
        assertFinalCoordinates(finalCoordinates);
    }

    @Test
    public void testMainValidData(){
        originalOut = System.out;
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        String simulatedInput = MarsRoverTestData.plateauSizeList.get(0)+"\n"
                +MarsRoverTestData.roverPositions.get(0)+"\n"
                +MarsRoverTestData.roverInstructions.get(0)+"\n"
                +MarsRoverTestData.userYesNo.get(1)+"\n";

        System.setIn(new java.io.ByteArrayInputStream(simulatedInput.getBytes()));

        MarsRoverApp.main(new String[0]);
        String consoleOutput = outputStream.toString();

        Pattern pattern = Pattern.compile("Rover 1 is now at new position (.+)");

        Matcher matcher = pattern.matcher(consoleOutput);

        assertTrue(matcher.find());
        String actualOutput = matcher.group(1);

        assertEquals(MarsRoverTestData.finalRoverPositions.get(0), actualOutput);

    }
}
