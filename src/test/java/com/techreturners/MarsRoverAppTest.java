package com.techreturners;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    public void testMain(){
        originalOut = System.out;
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        String simulatedInput = MarsRoverTestData.plateauSize+"\n"
                +MarsRoverTestData.roverOnePosition+"\n"
                +MarsRoverTestData.roverOneInstruction+"\n"
                +MarsRoverTestData.userNo+"\n";

        System.setIn(new java.io.ByteArrayInputStream(simulatedInput.getBytes()));

        MarsRoverApp.main(new String[0]);
        // Capture and verify console output
        String consoleOutput = outputStream.toString();

        // Define a regex pattern to extract the relevant part of the output
        Pattern pattern = Pattern.compile("Rover 1 is now at new position (.+)");

        // Use a Matcher to find the matching substring
        Matcher matcher = pattern.matcher(consoleOutput);

        assertTrue(matcher.find());
        String actualOutput = matcher.group(1);

        assertEquals(MarsRoverTestData.finalPositionRoverOne, actualOutput);

    }

}
