package timingtest;
import edu.princeton.cs.algs4.Stopwatch;
import org.checkerframework.checker.units.qual.A;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        AList<Integer> ns = new AList<>();
        AList<Integer> ops = new AList<>();
        for (int i = 0; i < 8; i++) {
            ns.addLast( (int)(1000 * Math.pow(2,i)));
            ops.addLast(10000);
        }
        AList<Double>times = new AList<>();

        /**创建sslist*/
        for (int i = 0; i < ns.size(); i++) {
            double N = ns.get(i);
            SLList<Integer> test = new SLList<>();
            for (int j = 0; j < N; j++) {
                test.addLast(j);
            }
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < 1000; j++) {
                test.getLast();
            }
            double time_element = sw.elapsedTime();
            times.addLast(time_element);
        }



        printTimingTable(ns,times,ops);

    }

}
