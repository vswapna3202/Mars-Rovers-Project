package com.techreturners;

public abstract class SpaceVehicles {
    protected String color;
    protected String engineSize;
    protected String tyreSize;
    protected String batteryLevel;
    protected abstract void rotateLeft();
    protected abstract void rotateRight();
    protected abstract boolean moveForward(Plateau plateau)
            throws CustomRoverException;
}
