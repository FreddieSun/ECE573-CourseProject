Quick Start:
====
javac *.java

java NBody 5000 >> result1.txt
java NBodyDemo 1000 collision1.txt

java NBodyBrute 5000 >> result2.txt
java NBodyBruteDemo 1000 collision1.txt
====


To compile,
====
javac *.java
====
and ignore all the warnings.


To run the brute force algorithm,
====
java NBodyBrute <step>
java NBodyBruteDemo <step> <dataset>
====

To run the Barnes Hut algorithm,
====
java NBody <step>
java NBodyDemo <step> <dataset>
====

Parameters:
step: required, an int that determines the program will run how many steps. No animation.
dateset: required, name of file under the /dataset. Enter the filename directly. With animation.

Example:
====
java NBody 1000 >> result1.txt
====
The program will run the algorithm 1000 steps without animation, and the output will be stored in result1.txt

====
java NBodyDemo 1000 collision1.txt
====
The program will run 1000 steps with animation, using the dataset from collision1.txt
