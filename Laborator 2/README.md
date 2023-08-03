# Lab 2

## Objects and Classes

An instance of the "Best Route Problem" consists of *locations* and *roads*. Locations may be cities, airports, gas stations, etc.  
Two locations may be connected by a road, or not. Roads may be highways, express, country, etc.

* Each location has a name, type and x, y coordinates (assume that the locations are placed in a cartesian coordinate system).
* Each road has a type, length and a speed limit. The length of a road should not be less than the euclidian distance between the location coordinates.

We consider the problem of determining the "best" route from one location to another.