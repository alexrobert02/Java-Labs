# Lab 7

## Concurrency

Consider a map represented as a n x n square matrix, each cell being an individual location of the map.  
A number of robots (agents) must explore all the cells of the matrix. They are initially located at random positions and are allowed to move in any direction inside the map. Two robots cannot be in the same cell at once.  
The robots can access a shared memory containing tokens (for example, numbers from 1 to n<sup>3</sup>, shuffled). Once a robot reaches an unvisited cell it must extract n tokens and store them in the matrix cell.  
A supervisor can start/pause the robots (all of them or only a specific one).