package timingtest;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stopwatch;
import org.checkerframework.checker.units.qual.A;

/**
 * Created by hug.
 */
public class TimeAList {
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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        AList<Integer> N = new AList<>();
        // 数据的大小
        /**
         * 1000 2^0 * 1000 = 1000
         * 2000 2^1 * 1000 = 2000
         * 4000 2^2 * 1000 = 4000
         * 8000 2^3 * 1000 = 8000
         * ...
         */
        AList<Integer> Ns = new AList<>();
        // ops计时实验期间对addLast的调用次数
        AList<Double> times = new AList<>();
        // 完成addLast平均花费时间
        AList<Integer> opCounts = new AList<>();

        Stopwatch stopwatch = new Stopwatch();
        int ops = 0;
        int tick = 0;
        for (int i = 0; i < 1024000; i++) {
            N.addLast(i);
            ops += 1;
            if (N.size() == Math.pow(2, tick) * 1000) {
                Ns.addLast(N.size());
                times.addLast(stopwatch.elapsedTime());
                opCounts.addLast(ops);
                tick += 1;
            }
        }

        printTimingTable(Ns, times, opCounts);
    }
}
