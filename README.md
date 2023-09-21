# **Mars Rover Challenge** 
## ***Version 1.0*** 

### **Installation**
- Clone this repository using
  git clone https://github.com/vswapna3202/Mars-Rover-Challenge.git
- Navigate to project directory Mars-Rover-Challenge
- Run 'mvn test' to build and test the project

### **Application start**
- You can start the application by executing main method from com.techreturners.MarsRoverApp

### **Project Brief**
This application enables the Mars Rover to navigate about the Martian terrain. The
Rover can perform actions such as rotating left and rotating right by 90 degrees and moving forward by one step. Rover's movements are restricted by certain conditions:
    - If an obstacle obstructs its path, the Rover cannot proceed
    - The rover must stay within its designated boundary in the plateau

### **Inputs:**
- <em>PLATEAU SIZE</em>: Specified as 5 5 where 5 5 is the maximum boundary of the plateau. Minimum 
  boundary is always 0 0
- <em>ROVER POSITION</em>: Specified as 1 2 N where 1 2 are position where Rover currently is and
  N is the direction the rover is facing. It can face N, E, S, W
- <em>ROVER INSTRUCTIONS</em>: LMLMLMLMM specifies the rotations and movements the rover needs to 
  do. It takes any three instructions L(rotate left), M(move forward), R(rotate right)
- <em>DO YOU WANT TO ENTER MORE ROVERS (Y/N)</em>: Input Y if more rovers data needs to be entered
  N if only this single rover needs to rotate and/or move

### **Outputs:**
The output is its final position (final co-ordinates where it is facing) example
Rover 1 is now at new position 1 3 N

### **Example:**
- Enter the plateau maximum co-ordinates in format x y : 5 5
- Enter the current Rover position co-ordinates in format x y direction e.g. 1 2 N : 1 2 N
- Enter the instruction for rover movement(L,R or M): LMLMLMLMM
- Do you have more Rovers? (Y/N): Y
- Enter the current Rover position co-ordinates in format x y direction e.g. 1 2 N : 3 3 E
- Enter the instruction for rover movement(L,R or M): MMRMMRMRRM
- Do you have more Rovers? (Y/N): N
- Rover 1 is now at new position 1 3 N
- Rover 2 is now at new position 5 1 E

### **Assumptions:**
- Mars contains only Plateau for this release
- Plateau is rectangular for this release
- Rovers move sequentially that is one rover would finish its movement before next
  rover moves
- Plateau boundary min position is always 0 0 and max is specified by user
- Rover will not be deployed or move forward as per instructions if an obstacle exists 
  in its path

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
  