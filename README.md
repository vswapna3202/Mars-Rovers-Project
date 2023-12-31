# **Mars Rover Challenge** 
## ***Version 1.0*** 

### **Installation**
```
- Clone this repository using
  git clone https://github.com/vswapna3202/Mars-Rover-Challenge.git
- Navigate to project directory Mars-Rover-Challenge
- Run 'mvn test' to build and test the project
```

### **Application start**
- You can start the application by executing main method from com.techreturners.MarsRoverApp

### **Project Brief**
This application enables the Mars Rover to navigate about the Martian terrain. The
Rover can perform actions such as rotating left and rotating right by 90 degrees and moving forward by one step. Rover's movements are restricted by certain conditions:
    - If an obstacle obstructs its path, the Rover cannot proceed
    - The rover must stay within its designated boundary in the plateau

### **Inputs:**
```
- PLATEAU SIZE: Specified as 5 5 where 5 5 is the maximum boundary of the plateau. Minimum 
  boundary is always 0 0
- ROVER POSITION: Specified as 1 2 N where 1 2 are position where Rover currently is and
  N is the direction the rover is facing. It can face N, E, S, W
- ROVER INSTRUCTIONS: LMLMLMLMM specifies the rotations and movements the rover needs to 
  do. It takes any three instructions L(rotate left), M(move forward), R(rotate right)
- DO YOU WANT TO ENTER MORE ROVERS (Y/N): Input Y if more rovers data needs to be entered
  N if only this single rover needs to rotate and/or move
- DO YOU WANT TO CONTINUE MOVING ROVER(S) FURTHER (Y/N): Input Y if you want rover(s) to
  contiue moving over the plateau and N if you want to stop rovers from moving
- DO YOU WANT TO GIVE INSTRUCTION FOR ROVER: 1 (Y/N): Input Y if you want Rover 1 to
  continue moving to a new location and N if you do not want the rover to move
- ENTER THE INSTRUCTION FOR ROVER MOVEMENT(L,R OR M): LMM specifies the rotations and 
  movements the rover needs to do to continue moving to its new position. It is same value
  as ROVER INSTRUCTIONS L(rotate left), M(move forward), R(rotate right)
  ```


### **Outputs:**
The output is its final position (final co-ordinates where it is facing) example
```
Rover 1 is now at new position 1 3 N
```

### **Example:**
```
Enter the plateau maximum co-ordinates in format x y : 5 5
Enter the current Rover position co-ordinates in format x y direction e.g. 1 2 N : 1 2 N
Enter the instruction for rover movement(L,R or M): LMLMLMLMM
Do you have more Rovers? (Y/N): Y
Enter the current Rover position co-ordinates in format x y direction e.g. 1 2 N : 3 3 E
Enter the instruction for rover movement(L,R or M): MMRMMRMRRM
Do you have more Rovers? (Y/N): N
Rover 1 is now deployed at new position 1 3 N
Rover 2 is now deployed at new position 5 1 E
Do you want to continue moving rover(s) further?(Y/N): Y
Do you want to give instruction for Rover: 1 (Y/N): Y
Enter the instruction for rover movement(L,R or M): MM
Rover 1 is now deployed at new position 1 5 N
Do you want to give instruction for Rover: 2 (Y/N): Y
Enter the instruction for rover movement(L,R or M): LM
Rover 1 is now deployed at new position 1 5 N
Rover 2 is now deployed at new position 5 2 N
Do you want to continue moving rover(s) further?(Y/N): N
```

### **Assumptions:**
- Mars contains only Plateau for this release
- Plateau is rectangular for this release
- Rovers move sequentially that is one rover would finish its movement before next
  rover moves
- Plateau boundary minimum position is always 0 0 and maximum is specified by user
- Rover will not be deployed or move forward as per instructions if an obstacle exists 
  in its path and a message will be displayed to user
- Rovers which are deployed successfully only can continue their movements after user
  specifies the further movement instructions
- Rovers whose further instructions are input by user alone will continue their movements 
  further and switching between rovers functionality is not implemented in this release 

### **Future Considerations:**
- Rover can not only have plateaus it can have other criteria like mounds 
- Rover can have additional functionality of 
    - Check safe Terrain
    - Collect sample 
    - Taking photos 
    - Taking videos
    - Perform self diagnostic schedules
    - Get Telemetry data
- Plateaus can not only be rectangular can be other shapes like circle, hexagon etc
- Other vehicles can also roam on the Mars terrain
- Rover positions can be stored in a file and recorded
- Rover movements are continued if instructions provided by user, in future the user
  should be able to go back to a standing rover and give instructions at a later point.
  Switching between rovers for instructions could be implemented.

  