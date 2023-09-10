package com.techreturners;

import java.util.ArrayList;

public class ObstacleDetector {

    public void detectsObstacle(ArrayList<String> finalCoordinates){
        if (finalCoordinates.size() != 1) {
            for (int k = 0; k < finalCoordinates.size()-1; k++) {
                for (int j = k+1; j < finalCoordinates.size(); j++) {
                    if (finalCoordinates.get(k).equals(finalCoordinates.get(j))) {
                        finalCoordinates.remove(j);
                        finalCoordinates.add(j,"Rover Collision for Rover "+(j+1)+
                                " and Rover "+(k+1)+" not deploying Rover "+(j+1)+" to location");
                    }
                }
            }
        }
    }
}
