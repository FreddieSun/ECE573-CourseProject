Quick Start:
====
java NBody 5000 false >> result1.txt
java NBodyBrute1 5000 false >> result2.txt
====


To compile,
====
javac *.java
====
and ignore all the warnings.


To run the brute force algorithm,
====
java NBodyBrute1 <step> <isDemo>
====

To run the Barnes Hut algorithm,
====
java NBody <step> <isDemo>
====

Parameters:
step: an int that determines the program will run how many steps.
isDemo: a boolean, if true, the algorithm will show you the animation, or it won't.

Example:
====
java NBody 1000 true
====
The program will run the algorithm 1000 steps with animation.

====
java NBody 1000 false >> result1.txt
====
The program will run 1000 steps without animation, and the output will be stored in result1.txt.
