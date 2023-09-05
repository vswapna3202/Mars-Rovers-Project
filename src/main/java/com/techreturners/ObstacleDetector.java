package com.techreturners;

import java.util.ArrayList;

public class ObstacleDetector {
    private ArrayList<Position> obstaclePositions;
    public ObstacleDetector(){
        //hardcoding to be removed later
        Position position = new Position(1, 3);
        this.obstaclePositions = new ArrayList<Position>();
        this.obstaclePositions.add(position);
    }

    public boolean detectsObstacle(int newX
            , int newY){
        for(Position obstaclePosition : obstaclePositions){
            int obstacleX = obstaclePosition.getxCoordinate();
            int obstacleY = obstaclePosition.getyCoordinate();
            if (obstacleX == newX && obstacleY == newY)
                return true;
        }
        return false;
    }
}
