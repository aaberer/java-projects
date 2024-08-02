To run Memory.java first start by compiling it with javac Memory.java in the command line.
Then the program expectes 3 input arguments the first being size, second number of experiments, and lastly the randomized seed. 
IF 3 INPUTS ARE NOT PROVIDED THE PROGRAM WILL NOT RUN.
The first task will take the arguments and compares regular (cached) access time compared to volatile information which is stored in main memory and has to be sent to main memory when it is changed or modified. 
The second task takes an array of a specified size and computes the average time to access a known element compared to the time to access a random index element.
Lastly the third task compares the access time to find a random element in two different data structures. A TreeSet and a LinkedList.