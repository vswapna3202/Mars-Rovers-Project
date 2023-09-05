package com.techreturners;

public class RectanglePlateau extends Plateau{
    private static final int minX = 0;
    private static final int minY = 0;
    private int maxX;
    private int maxY;

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
}
