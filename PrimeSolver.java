// Ashley Voglewede
// COP 4520 - Spring 2022
import java.util.*;
import java.io.*;

public class PrimeSolver extends Thread {
    public static final int max = 100_000_000; 
    public static final int numThreads = 8;
    public int threadID;

    // This boolean array is used to construct the Sieve of Eratosthenes for prime numbers. 
    public static boolean sieve[] = new boolean[max + 1]; 
    public static ArrayList<PrimeSolver> threads = new ArrayList<>();

    PrimeSolver(int threadID) {
        this.threadID = threadID;
    }
    
    // This method creates the Sieve of Eratosthenes. 
    public void run() {
        sieve[0] = true;
        sieve[1] = true;

        for (int i = 2 + this.threadID; i <= Math.sqrt(max); i += numThreads)
            if (sieve[i] == false)
                for (int j = i * i; j <= max; j += i)
                    sieve[j] = (true);
    }
    
    public static void countPrimes(long executionTime) throws IOException {
        long sumPrimes = 0;
        long numPrimes = 0;

        for (int i = 1; i <= max; i++) {
            if (sieve[i] == false) {
                sumPrimes += i;
                numPrimes += 1;
            }
        }

        int largestPrimes[] = new int[10];
        int end = 9;

        for (int i = max; i >= 0; i--) {
            if (sieve[i] == false)
                largestPrimes[end--] = i;

            if (end < 0)
                break;
        }

        outputToFile(executionTime, numPrimes, sumPrimes, largestPrimes);
    }

    // Output:
    // <execution time> <total number of primes found> <sum of all primes found>
    // <top ten maximum primes, listed in order from lowest to highest>      
    public static void outputToFile(long executionTime, long numPrimes, long sumPrimes, int[] largestPrimes) throws IOException {
        File f = new File("primeSummary.txt");
        f.createNewFile();
     
        FileWriter myWriter = new FileWriter("primeSummary.txt");
        myWriter.write("Execution Time: " + executionTime + "ms \n" + "Total Number of Primes Found: " + numPrimes + "\n" + "Sum of all Primes Found: " + sumPrimes + "\n");
        myWriter.write("Top 10 Maximum Primes: " + Arrays.toString(largestPrimes));

        myWriter.close();
    }
    public static void main(String args[]) throws InterruptedException, IOException {
        final long startTime = System.currentTimeMillis();

        for (int i = 0; i < numThreads; i++) {
            threads.add(new PrimeSolver(i)); 
            threads.get(i).start(); 
        }

        for (int i = 0; i < numThreads; i++) {
            threads.get(i).join(); 
        }

        final long endTime = System.currentTimeMillis();
        final long executionTime = endTime - startTime;

        countPrimes(executionTime);  
    }
}


