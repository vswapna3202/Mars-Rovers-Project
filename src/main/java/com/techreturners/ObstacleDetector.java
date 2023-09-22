package com.techreturners;

import java.util.ArrayList;


public class ObstacleDetector {
    /*
    For every forward movement of the rover a check is done to find out if there
    are any other rovers present at the location. If found a value true is returned
    and also finalCoordinates is updated with relevant message instead of coordinates
     */
    public static boolean detectsObstacle(ArrayList<String> finalCoordinates,
                                       int currentXCoordinate,
                                       int currentYCoordinate){
        if (finalCoordinates == null){
            return false;
        }
        int finalCoordinatesSize = finalCoordinates.size();
        for(int i = 0; i < finalCoordinatesSize; i++) {
            String[] parts = finalCoordinates.get(i).split("\\s");
            if (parts[0].matches("\\d+") && parts[1].matches("\\d+")) {
                int obstacleRoverXCoordinate = Integer.parseInt(parts[0]);
                int obstacleRoverYCoordinate = Integer.parseInt(parts[1]);
                if (obstacleRoverXCoordinate == currentXCoordinate &&
                        obstacleRoverYCoordinate == currentYCoordinate) {

                    finalCoordinates.add(i+1,
                            "Collision intercepted at co-ordinates "+obstacleRoverXCoordinate+
                            " "+obstacleRoverYCoordinate+" Rover not deployed as " +
                                    "a rover already is already positioned here");
                    return true;
                }
            }
        }
        return false;
    }
}
