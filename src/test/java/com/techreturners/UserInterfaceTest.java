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
        systemInputMock.provideLines(MarsRoverTestData.plateauSize);
        assertEquals(MarsRoverTestData.plateauSize, userInterface.getPlateauSize());
    }

    @Test
    public void testGetPlateauSizeInvalidData(){
        systemInputMock.provideLines(null,"","5, 5","A B","% $","1  2  ");
        assertEquals(MarsRoverTestData.plateauSizeWithSpaces, userInterface.getPlateauSize());
    }

    @Test
    public void testGetRoverPositionValidData() {
        systemInputMock.provideLines(MarsRoverTestData.roverOnePosition);
        assertEquals(MarsRoverTestData.roverOnePosition, userInterface.getRoverPosition());
    }

    @Test
    public void testGetRoverPositionInvalidData() {
        systemInputMock.provideLines(null,"","1, 2 N","A B 2","MME","£$%","1 3 A","1 2  s");
        assertEquals("1 2  S", userInterface.getRoverPosition());
    }

    @Test
    public void testGetRoverInstructionValidData() {
        systemInputMock.provideLines(MarsRoverTestData.roverOneInstruction);
        assertEquals(MarsRoverTestData.roverOneInstruction, userInterface.getRoverInstruction());
    }

    @Test
    public void testGetRoverInstructionInvalidData() {
        systemInputMock.provideLines(null,"","ABABABCCC","121212333","%$%$%$£££","L M L M  M ","lml");
        assertEquals("LML", userInterface.getRoverInstruction());
    }

    @Test
    public void testCollectRoverDataForSingleRover(){
        RoverDataBO marsRoverDataTestBO = marsRoverTestData.initialiseRoverDataBOObject(false);
        systemInputMock.provideLines(MarsRoverTestData.plateauSize,
                MarsRoverTestData.roverOnePosition,
                MarsRoverTestData.roverOneInstruction,
                MarsRoverTestData.userNo);
        RoverDataBO roverDataBOFromMethod = userInterface.collectRoverData();
        assertEquals(marsRoverDataTestBO.getPlateauSize(),
                roverDataBOFromMethod.getPlateauSize());
        assertEquals(marsRoverDataTestBO.getRoverPositionsList().get(0),
                    roverDataBOFromMethod.getRoverPositionsList().get(0));
        assertEquals(marsRoverDataTestBO.getRoverInstructionsList().get(0),
                    roverDataBOFromMethod.getRoverInstructionsList().get(0));
    }

    @Test
    public void testCollectRoverDataForMultipleRovers(){
        RoverDataBO marsRoverDataTestBO = marsRoverTestData.initialiseRoverDataBOObject(true);
        systemInputMock.provideLines(MarsRoverTestData.plateauSize,
                MarsRoverTestData.roverOnePosition,
                MarsRoverTestData.roverOneInstruction,
                MarsRoverTestData.userYes,
                MarsRoverTestData.roverTwoPosition,
                MarsRoverTestData.roverTwoInstruction,
                MarsRoverTestData.userNo);
        RoverDataBO roverDataBOFromMethod = userInterface.collectRoverData();
        assertEquals(marsRoverDataTestBO.getPlateauSize(),
                roverDataBOFromMethod.getPlateauSize());
        for (int i = 0; i < roverDataBOFromMethod.getRoverPositionsList().size(); i++) {
            assertEquals(marsRoverDataTestBO.getRoverPositionsList().get(i),
                    roverDataBOFromMethod.getRoverPositionsList().get(i));
            assertEquals(marsRoverDataTestBO.getRoverInstructionsList().get(i),
                    roverDataBOFromMethod.getRoverInstructionsList().get(i));
        }
    }


}
