
public class SortMain {
    public static void main(String[] args) throws UnsortedException {
        int[] sizes = new int[]{4,8,32,128,512,1024,2048,4096,8192,16384};

        for (int i = 0; i < 250; i++) {
            long startTime = System.nanoTime();
            BenchmarkSorts jvmWarmup = new BenchmarkSorts(sizes);
            jvmWarmup.runSorts();
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            System.out.println("JVM Warmup: " + i + "    \tTime (nanoseconds): " + duration);
        }

        BenchmarkSorts benchmark = new BenchmarkSorts(sizes);
        benchmark.runSorts();
        benchmark.displayReport();
    }
}
