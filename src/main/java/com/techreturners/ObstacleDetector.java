package com.techreturners;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ObstacleDetector {
    /*
        This method checks if two rovers are going to be deployed at same location.
        This method is primarily called when user chooses LR movement and does not choose
        MoveForward movement.
         */
    public static void detectsObstacle(ArrayList<String> finalCoordinates) {
        if (finalCoordinates.size() > 1) {
            Set<String> uniqueCoordinates = new HashSet<>();
            for(int i = 0; i < finalCoordinates.size() ; i++) {
                String coordinate = finalCoordinates.get(i);
                if (!uniqueCoordinates.add(coordinate)) {
                    finalCoordinates.remove(i);
                    /*finalCoordinates.add(i, "Rover Collision for Rover " +
                            (i + 1) + " and Rover " + (uniqueCoordinates.size() ) +
                            " not deploying Rover " + (i + 1) + " to location");*/
                    finalCoordinates.add(i ,"Collision intercepted at " +
                            "co-ordinates "+coordinate+" Rover not deployed as a rover "+
                            "is already positioned here");
                }
            }
        }
    }
    /*
            for (int k = 0; k < finalCoordinates.size()-1; k++) {
                for (int j = k+1; j < finalCoordinates.size(); j++) {
                    if (finalCoordinates.get(k).equals(finalCoordinates.get(j))) {
                        //If two co-ordinates with same values exists in finalCoordinates
                        //remove the second one and update a message about collision
                        // instead
                        finalCoordinates.remove(j);
                        finalCoordinates.add(j,"Rover Collision for Rover "+(j+1)+
                                " and Rover "+(k+1)+" not deploying Rover "+(j+1)+" to location");
                    }
                }
            }
        }
    }*/

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
                    //finalCoordinates.add(i+1, "Rover Collision with Rover "+(i+1)+" Another Rover on "+
                      //      "rover route! Not deploying Rover to location");
                    finalCoordinates.add(i+1,
                            "Collision intercepted at co-ordinates "+obstacleRoverXCoordinate+
                            " "+obstacleRoverYCoordinate+" Rover not deployed as " +
                                    " a rover already is already positioned here");
                    return true;
                }
            }
        }
        return false;
    }
}
