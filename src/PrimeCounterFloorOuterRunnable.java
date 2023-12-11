public class PrimeCounterFloorOuterRunnable implements Runnable {

    private double start;
    private double end;
    private double stepSize;
    private double error = 0.00000000001;

    private int size;
    private double[][] data;

    public PrimeCounterFloorOuterRunnable(double start, double end, double stepSize) {
        this.start = start;
        this.end = end;
        this.stepSize = stepSize;
        if (stepSize == 1) {
            size = (int)(Math.ceil((end - start + 1) / stepSize));
        }
        else {
            size = (int)(Math.ceil((end - start) / stepSize)+1);
        }
        this.data = new double[size][2];
    }

    public void run() {
        int count = 0;
        for (double j = start; j <= end + error; j += stepSize) { 
            data[count][0] = j;
            // data[count][1] = functions.primeCounterFloorOuter((int)j); //much faster for some reason, multithreading must not be done correctly
            data[count][1] = multithreadTest.primeCounterFloorOuter((int)j);
            count++;
        }
    }

    public double[][] getData() {
        return data;
    }
}
