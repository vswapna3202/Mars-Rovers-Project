package com.techreturners;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserInterfaceTest {
    UserInterface userInterface;
    MarsRoverTestData marsRoverTestData;

    @Before
    public void setUp() {
        marsRoverTestData = new MarsRoverTestData();
        userInterface = new UserInterface();
    }

    @Rule
    public final TextFromStandardInputStream systemInputMock =
            TextFromStandardInputStream.emptyStandardInputStream();

    @Test
    public void testGetPlateauSizeValidData(){
        // Simulating userInput and values are for valid Plateau size
        systemInputMock.provideLines(MarsRoverTestData.plateauSizeList.get(0));
        assertEquals(MarsRoverTestData.plateauSizeList.get(0),
                userInterface.getPlateauSize());
    }


    @Test
    public void testGetPlateauSizeInvalidData(){
        // Simulating userInput and all invalid values for Plateau size are tested
        systemInputMock.provideLines(MarsRoverTestData.plateauInvalidData);
        assertEquals("1  2", userInterface.getPlateauSize());
        // Simulating userInput and null value for Plateau size is tested
        systemInputMock.provideLines(null,MarsRoverTestData.plateauSizeList.get(0));
        assertEquals(MarsRoverTestData.plateauSizeList.get(0),
                userInterface.getPlateauSize());
    }

    @Test
    public void testGetRoverPositionValidData() {
        // Simulating userInput and valid value for Rover Position is tested
        systemInputMock.provideLines(MarsRoverTestData.roverPositions.get(0));
        assertEquals(MarsRoverTestData.roverPositions.get(0),
                userInterface.getRoverPosition());
    }

    @Test
    public void testGetRoverPositionInvalidData() {
        // Simulating userInput and all invalid values for Rover Position are tested
        systemInputMock.provideLines(MarsRoverTestData.roverPositionsInvalidData);
        assertEquals(MarsRoverTestData.roverPositions.get(0),
                userInterface.getRoverPosition());
        //Null value for rover position is tested
        systemInputMock.provideLines(null, MarsRoverTestData.roverPositions.get(0));
        assertEquals(MarsRoverTestData.roverPositions.get(0),
                userInterface.getRoverPosition());
    }

    @Test
    public void testGetRoverInstructionValidData() {
        // Simulating userInput and valid value for Rover Instruction is tested
        systemInputMock.provideLines(MarsRoverTestData.roverInstructions.get(0));
        assertEquals(MarsRoverTestData.roverInstructions.get(0), userInterface.getRoverInstruction());
    }

    @Test
    public void testGetRoverInstructionInvalidData() {
        // Simulating userInput and all invalid values for Rover Instructions are tested
        systemInputMock.provideLines(MarsRoverTestData.roverInstructionsInvalidData);
        assertEquals(MarsRoverTestData.roverInstructions.get(5),
                userInterface.getRoverInstruction());
        // Null value for roverInstruction is tested
        systemInputMock.provideLines(null,
                MarsRoverTestData.roverInstructions.get(0));
        assertEquals(MarsRoverTestData.roverInstructions.get(0),
                userInterface.getRoverInstruction());
    }

    @Test
    public void testCollectRoverDataForValidSingleRover(){
        // Simulating userInput and all invalid values for collectRoverData with
        // user inputting a single Rover valid data is tested
        RoverDataBO marsRoverDataTestBO = marsRoverTestData.initialiseRoverDataBOObject(false);
        systemInputMock.provideLines(MarsRoverTestData.plateauSizeList.get(0),
                MarsRoverTestData.roverPositions.get(0),
                MarsRoverTestData.roverInstructions.get(0),
                MarsRoverTestData.userYesNo.get(1));
        RoverDataBO roverDataBOFromMethod = userInterface.collectRoverData();
        assertRoverDataBO(marsRoverDataTestBO, roverDataBOFromMethod);
    }

    /*  This method asserts Plateau Size, rover position and rover instruction
        populated in roverDataBO object by collectRoverData method. In case of
        multiple rover data all positions and instructions are checked if populated
        in BO object
     */
    private static void assertRoverDataBO(RoverDataBO marsRoverDataTestBO, RoverDataBO roverDataBOFromMethod) {

        assertEquals(marsRoverDataTestBO.getPlateauSize(),
                roverDataBOFromMethod.getPlateauSize());
        for (int i = 0; i < roverDataBOFromMethod.getRoverPositionsList().size(); i++) {
            assertEquals(marsRoverDataTestBO.getRoverPositionsList().get(0),
                    roverDataBOFromMethod.getRoverPositionsList().get(0));
            assertEquals(marsRoverDataTestBO.getRoverInstructionsList().get(0),
                    roverDataBOFromMethod.getRoverInstructionsList().get(0));
        }
    }

    @Test
    public void testCollectRoverDataForMultipleRovers(){
        // Simulating userInput and all invalid values for collectRoverData with
        // user inputting valid data for multiple rovers
        RoverDataBO marsRoverDataTestBO = marsRoverTestData.initialiseRoverDataBOObject(true);
        systemInputMock.provideLines(MarsRoverTestData.plateauSizeList.get(0),
                MarsRoverTestData.roverPositions.get(0),
                MarsRoverTestData.roverInstructions.get(0),
                MarsRoverTestData.userYesNo.get(0),
                MarsRoverTestData.roverPositions.get(1),
                MarsRoverTestData.roverInstructions.get(1),
                MarsRoverTestData.userYesNo.get(1));
        RoverDataBO roverDataBOFromMethod = userInterface.collectRoverData();
        assertRoverDataBO(marsRoverDataTestBO, roverDataBOFromMethod);
    }
}
