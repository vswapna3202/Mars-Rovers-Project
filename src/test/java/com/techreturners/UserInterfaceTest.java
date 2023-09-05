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
        systemInputMock.provideLines(MarsRoverTestData.plateauSizeList.get(0));
        assertEquals(MarsRoverTestData.plateauSizeList.get(0),
                userInterface.getPlateauSize());
    }

    @Test
    public void testGetPlateauSizeInvalidData(){
        systemInputMock.provideLines(null,"","5, 5","A B","% $","1  2  ");
        assertEquals("1  2", userInterface.getPlateauSize());
    }

    @Test
    public void testGetRoverPositionValidData() {
        systemInputMock.provideLines(MarsRoverTestData.roverPositions.get(0));
        assertEquals(MarsRoverTestData.roverPositions.get(0),
                userInterface.getRoverPosition());
    }

    @Test
    public void testGetRoverPositionInvalidData() {
        systemInputMock.provideLines(null,"","1, 2 N","A B 2","MME","£$%","1 3 A","1 2  s");
        assertEquals("1 2  S", userInterface.getRoverPosition());
    }

    @Test
    public void testGetRoverInstructionValidData() {
        systemInputMock.provideLines(MarsRoverTestData.roverInstructions.get(0));
        assertEquals(MarsRoverTestData.roverInstructions.get(0), userInterface.getRoverInstruction());
    }

    @Test
    public void testGetRoverInstructionInvalidData() {
        systemInputMock.provideLines(null,"","ABABABCCC","121212333","%$%$%$£££","L M L M  M ","lml");
        assertEquals("LML", userInterface.getRoverInstruction());
    }

    @Test
    public void testCollectRoverDataForSingleRover(){
        RoverDataBO marsRoverDataTestBO = marsRoverTestData.initialiseRoverDataBOObject(false);
        systemInputMock.provideLines(MarsRoverTestData.plateauSizeList.get(0),
                MarsRoverTestData.roverPositions.get(0),
                MarsRoverTestData.roverInstructions.get(0),
                MarsRoverTestData.userYesNo.get(1));
        RoverDataBO roverDataBOFromMethod = userInterface.collectRoverData();
        assertRoverDataBO(marsRoverDataTestBO, roverDataBOFromMethod);
    }

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
