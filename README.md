# PrimeSolver
A program that uses multithreading to find prime numbers.

## Problem 
Your non-technical manager assigns you the task to find all primes between 1 and 108.  The assumption is that your company is going to use a parallel machine that supports eight concurrent threads. Thus, in your design you should plan to spawn 8 threads that will perform the necessary computation. Your boss does not have a strong technical background but she is a reasonable person. Therefore, she expects to see that the work is distributed such that the computational execution time is approximately equivalent among the threads. Finally, you need to provide a brief summary of your approach and an informal statement reasoning about the correctness and efficiency of your design. Provide a summary of the experimental evaluation of your approach. Remember, that your company cannot afford a supercomputer and rents a machine by the minute, so the longer your program takes, the more it costs. Feel free to use any programming language of your choice that supports multi-threading as long as you provide a ReadMe file with instructions for your manager explaining how to compile and run your program from the command prompt.  

Please print the following output to a file named primes.txt:
- <execution time> <total number of primes found> <sum of all primes found>
- <top ten maximum primes, listed in order from lowest to highest>

## Solution & Efficiency Increase with Parallelism
1. Efficient Prime Solving Algorithm 
The first step to creating a more efficient program is by implementing a faster algorithm than brute force.
I used the Sieve of Eratosthenes, which has a O(N log (log N)) runtime.

2. Multithreading 
The second step to reducing runtime is with parallelism. The use of eight threads for this assignment drastically 
decreased the runtime. Before using multithreading, the program took about ~6 seconds to execute. After implementing parallelism with 8 different threads, the program now takes ~0.5 seconds to execute. I specifically used multithreading in the creation of the sieve, spawning
8 threads to simultaneously calculate and mark all multiples of i to create the sieve.  

## Experimental Evaluation
When testing the program, I made sure to test with values both larger and smaller than 10^8. 

## How to Run: 
1. To compile the program, run this command in a terminal in the appropriate directory. 
```bash
    javac PrimeSolver.java
    java PrimeSolver
```
2. To see the program output, open the primeSummary.txt file. 

## Resources used
1. Sieve of Eratosthenes[https://www.geeksforgeeks.org/sieve-of-eratosthenes/]
2. Algorithmic Explanation[https://www.massey.ac.nz/~mjjohnso/notes/59735/seminars/01077635.pdf]