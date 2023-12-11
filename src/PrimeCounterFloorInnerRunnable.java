public class PrimeCounterFloorInnerRunnable implements Runnable {

    private double start;
    private double end;
    private double stepSize;
    private int size;
    private double[] data;
    private double error = 0.00000000001;
    private double x;

    public PrimeCounterFloorInnerRunnable(double start, double end, double stepSize, double x) {
        this.start = start;
        this.end = end;
        this.stepSize = stepSize;
        this.x = x;
        if (stepSize == 1) {
            size = (int)(Math.ceil((end - start + 1) / stepSize));
        }
        else {
            size = (int)(Math.ceil((end - start) / stepSize)+1);
        }
        this.data = new double[size];

    }

    public void run() {
        int count = 0;
        for (double k = start; k <= end + error; k += stepSize) { 
            data[count] = multithreadTest.primeCounterFloorInner((int)x, (int)k);
            data[count] *= functions.mobiusFunction((int)k);
            count++;
        }
    }

    public double[] getData() {
        return data;
    }
}
