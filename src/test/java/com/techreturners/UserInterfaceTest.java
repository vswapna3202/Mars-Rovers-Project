package com.techreturners;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
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
        systemInputMock.provideLines(marsRoverTestData.plateauSize);
        assertEquals(marsRoverTestData.plateauSize, userInterface.getPlateauSize());
    }

    @Test
    public void testGetPlateauSizeInvalidData(){
        systemInputMock.provideLines(null,"","5, 5","A B","% $","1  2  ");
        assertEquals(marsRoverTestData.plateauSizeWithSpaces, userInterface.getPlateauSize());
    }

    @Test
    public void testGetRoverPositionValidData() {
        systemInputMock.provideLines(marsRoverTestData.roverPosition);
        assertEquals(marsRoverTestData.roverPosition, userInterface.getRoverPosition());
    }

    @Test
    public void testGetRoverPositionInvalidData() {
        systemInputMock.provideLines(null,"","1, 2 N","A B 2","MME","£$%","1 3 A","1 2  s");
        assertEquals("1 2  S", userInterface.getRoverPosition());
    }

    @Test
    public void testGetRoverInstructionValidData() {
        systemInputMock.provideLines(marsRoverTestData.roverInstruction);
        assertEquals(marsRoverTestData.roverInstruction, userInterface.getRoverInstruction());
    }

    @Test
    public void testGetRoverInstructionInvalidData() {
        systemInputMock.provideLines(null,"","ABABABCCC","121212333","%$%$%$£££","L M L M  M ","lml");
        assertEquals("LML", userInterface.getRoverInstruction());
    }


}
