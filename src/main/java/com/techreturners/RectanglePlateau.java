package com.techreturners;

public class RectanglePlateau extends Plateau{
    private static final int minX = 0;
    private static final int minY = 0;
    private final int maxX;
    private final int maxY;

    public RectanglePlateau(int maxX, int maxY){
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public boolean isWithinBounds(int newX, int newY){
        if (newX < minX || newY < minY)
            return false;
        else if (newX > maxX || newY > maxY)
            return false;
        return true;
    }

}
