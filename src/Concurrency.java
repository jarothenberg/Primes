import java.io.IOException;

class Concurrency extends Thread {

    static double start = 0;
    static double end = 999999;
    static double step = 1;
    static int size = (int)(Math.ceil((end - start) / step) + 1);
    static double[][] data = new double[size][2];

    private int startNum;
    private int endNum;
    private double error = 0.0000000001;
    private int count;

    Concurrency(int startNum, int endNum) {
        this.startNum = startNum;
        this.endNum = endNum;
        this.count = startNum - 1;
    }

    @Override
    public void run() {

        for (double i = this.startNum; i <= this.endNum + error; i += step) {
            count++;
            data[count][0] = i;
            data[count][1] = functions.primeCounterFloor((int)i);
            //System.out.println(count);
        }
    }

    public static void main(String[] args) throws IOException {

        String filename = "testing.csv";
        String[] header = {"x","y"};
        System.out.println("Started");

        int threadNums = 4;
        Concurrency[] threads = new Concurrency[threadNums];
        int[] runsPerThread = new int[threadNums];

        int startNum = 0;
        int endNum = 9;

        int remainder = (endNum - startNum + 1) % threadNums;
        for (int i = 0; i < threadNums; i++) {
            runsPerThread[i] = (endNum - startNum + 1) / threadNums;
            if (remainder > 0) {
                runsPerThread[i]++;
                remainder--;
            }
        }

        int start = startNum;
        for (int i = 0; i < threads.length; i++) {
            int end = start + runsPerThread[i] - 1;
            threads[i] = new Concurrency(start, end);
            System.out.println("Start: " + threads[i].startNum + " End: " + threads[i].endNum);
            threads[i].start();
            start = end + 1;
        }

        boolean done = false;
        while (!done) {
            done = true;
            for (Concurrency c : threads) {
                if (c.isAlive()) {
                    done = false;
                }
            }
        }

        System.out.println("Ended");
        csv.write(header,data,filename);
    }
}

