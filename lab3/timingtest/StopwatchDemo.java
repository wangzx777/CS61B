package timingtest;

import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class StopwatchDemo {
    /** Computes the nth Fibonacci number using a slow naive recursive strategy.*/
    private static int fib(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {
        Stopwatch swall = new Stopwatch();
//        int fib41 = fib(41);
        double timeInSeconds = swall.elapsedTime();
//        double fib43 = fib(43);
        for (int i = 0; i < 44; i++) {
            Stopwatch sw = new Stopwatch();
            double fib = fib(i);
            double t = sw.elapsedTime();
            System.out.println(i+","+t);
        }
        double all_t = swall.elapsedTime();
//        System.out.println("Time taken to compute 41st fibonacci number: " + timeInSeconds + " seconds.");
        System.out.println("all"+all_t);
    }
}
