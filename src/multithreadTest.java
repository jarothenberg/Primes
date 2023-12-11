import java.io.IOException;

public class multithreadTest {

    public static int primeCounterFloorOuter(int num) {
        int threadNums = 1;
        Thread[] threads = new Thread[threadNums];
        int[] runsPerThread = new int[threadNums];
        PrimeCounterFloorInnerRunnable[] runnables = new PrimeCounterFloorInnerRunnable[threadNums];
        double startNum = 1;
        double endNum = (int)(Math.log(num)/Math.log(2));
        double stepSize = 1;
        double start = startNum;
        double end = 0;

        if (stepSize == 1) {
            endNum += 1;
        }

        int remainder = (int)(Math.ceil(((endNum - startNum) / stepSize) % threadNums));

        for (int i = 0; i < threads.length; i++) {
            runsPerThread[i] = (int)(Math.floor(((endNum - startNum) / stepSize) / threadNums));
            if (remainder > 0) {
                runsPerThread[i]++;
                remainder--;
            }
            end += runsPerThread[i]*stepSize;
            runnables[i] = new PrimeCounterFloorInnerRunnable(start,end,stepSize,num);

            threads[i] = new Thread(runnables[i]);
            threads[i].start();
            start = end + stepSize;
        }

        boolean done = false;
        while (!done) {
            done = true;
            for (Thread t : threads) {
                if (t.isAlive()) {
                    done = false;
                }
            }
        }

        int sum = 0;
        for (PrimeCounterFloorInnerRunnable r : runnables) {
            double[] rData = r.getData();
            for (int i = 0; i < rData.length; i++) {
                sum += rData[i];
            }
        }
	    return -sum;
	}

    public static int primeCounterFloorInner(int num, int i) {
        int sum = 0;
        for (int j = 2; j <= (int)(Math.pow(num, 1.0 / i)); j++) {
            sum += (int)(Math.pow(num, 1.0 / i) / j) * functions.mobiusFunction(j) * functions.primeOmegaFunctionOmega(j);
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {

        String filename = "testing.csv";
        String[] header = {"x","y"};
        System.out.println("Started");

        int threadNums = 64;
        Thread[] threads = new Thread[threadNums];
        int[] runsPerThread = new int[threadNums];
        PrimeCounterFloorOuterRunnable[] runnables = new PrimeCounterFloorOuterRunnable[threadNums];
        double startNum = 1;
        double endNum = 15000;
        double stepSize = 1;
        double start = startNum;
        double end = 0;

        if (stepSize == 1) {
            endNum += 1;
            end -= 1;
        }

        int remainder = (int)(Math.ceil(((endNum - startNum) / stepSize) % threadNums));

        for (int i = 0; i < threads.length; i++) {
            runsPerThread[i] = (int)(Math.floor(((endNum - startNum) / stepSize) / threadNums));
            if (remainder > 0) {
                runsPerThread[i]++;
                remainder--;
            }
            end += runsPerThread[i]*stepSize;
            runnables[i] = new PrimeCounterFloorOuterRunnable(start,end,stepSize);
            threads[i] = new Thread(runnables[i]);
            threads[i].start();
            start = end + stepSize;
        }

        boolean done = false;
        while (!done) {
            done = true;
            for (Thread t : threads) {
                if (t.isAlive()) {
                    done = false;
                }
            }
        }


        int size = 0;
        for (PrimeCounterFloorOuterRunnable r : runnables) {
            size += r.getData().length;
        }

        double[][] data = new double[size][2];

        int preLength = 0;
        for (PrimeCounterFloorOuterRunnable r : runnables) {
            double[][] rData = r.getData();
            for (int col = 0; col < 2; col++) {
                for (int row = 0; row < rData.length; row++) {
                    data[row+preLength][col] = rData[row][col];
                }
            }
            preLength += rData.length;
        }

        System.out.println("Ended");
        csv.write(header,data,filename);
    }
}

//0 - 15000
//1,10:45
//2,9:18
//8,3:38
//16,2:16
//32,1:42
//64,1:30
//128,1:26
//1000,1:25
//15000,1:26