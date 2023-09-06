# **  Mars Rover Challenge -  Release Version 1.0 ** 

## **Installation**
- Clone this repository using
  git clone https://github.com/vswapna3202/Mars-Rover-Challenge.git
- Navigate to project directory Mars-Rover-Challenge
- Run 'mvn test' to build and test the project

## **Project Brief**
This application enables the Mars Rover to navigate about the Martian terrain. The
Rover can perform actions such as rotating left, rotating right and moving forward 
by one step. Rover's movements are restricted by certain conditions:
    - If an obstacle obstructs its path, the Rover cannot proceed
    - The rover must stay within its designated boundary in the plateau

## **Inputs:**
- PLATEAU SIZE: Specified as 5 5 where 5 5 is the maximum boundary of the plateau. Minimum 
  boundary is always 0 0
- ROVER POSITION: Specified as 1 2 N where 1 2 are position where Rover currently is and
  N is the direction the rover is facing. It can face N, E, S, W
- ROVER INSTRUCTIONS: LMLMLMLMM specifies the rotations and movements the rover needs to 
  do. It takes any three instructions L(rotate left), M(move forward), R(rotate right)
- DO YOU WANT TO ENTER MORE ROVERS (Y/N): Input Y if more rovers data needs to be entered
  N if only this single rover needs to rotate and/or move

## **Outputs:**
The output is its final position (final co-ordinates where it is facing) example 1 3 N

## **Example:**
- Enter the plateau maximum co-ordinates in format x y : 5 5
- Enter the current Rover position co-ordinates in format x y direction e.g. 1 2 N : 1 2 N
- Enter the instruction for rover movement(L,R or M): LMLMLMLMM
- Do you have more Rovers? (Y/N): Y
- Enter the current Rover position co-ordinates in format x y direction e.g. 1 2 N : 3 3 E
- Enter the instruction for rover movement(L,R or M): MMRMMRMRRM
- Do you have more Rovers? (Y/N): N
- Rover 1 is now at new position 1 3 N
- Rover 2 is now at new position 5 1 E

## **Assumptions:**
- Mars contains only Plateau for this release
- Plateau is rectangular for this release
- Rovers move sequentially that is one rover would finish its movement before next
  rover moves
- Plateau boundary min position is always 0 0 max is specified by user
- The obstacles are input as part of obstacles.txt file stored in resources folder

## **Future Considerations:**
- Rover can not only have plateaus it can have other criteria like mounds- 
- Rover can have additional functionality of 
    - Check safe Terrain
    - Collect sample 
    - Taking photos 
    - Taking videos
    - Perform self diagnostic schedules
    - Get Telemetry data
- Plateaus can not only be rectangular can be other shapes like square, circle etc
- Other vehicles can also roam on the Mars terrain
  