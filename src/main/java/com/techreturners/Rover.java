package com.techreturners;

import java.util.ArrayList;

public class Rover {
    Position position;
    Direction direction;
    Instruction instruction;
    public Rover(Position position, Direction direction, Instruction instruction){
        this.position = position;
        this.direction = direction;
        this.instruction = instruction;
    }

    public String calculateNewCoordinates(Plateau plateau){
        //method not coded yet for testing other methods using this to prevent
        //compilation error
        return "1 3 N";
    }
}
