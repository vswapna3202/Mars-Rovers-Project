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


}
