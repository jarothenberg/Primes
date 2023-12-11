import java.io.IOException;
import java.util.ArrayList;

public class main {    
    public static void main(String[] args) throws IOException {
        String filename = "pi0(x).csv";
        double start = 1;
        double end = 100;
        double step = 0.1;
        int size = (int)(Math.ceil((end - start) / step) + 1);
        int count = -1;
        double error = 0.00000000001;
        String[] header = {"x","y"};
        double[][] data = new double[size][2];
        System.out.println("Started");
        for (double i = start; i <= end + error; i += step){
            //System.out.println(i);
            count++;
            data[count][0] = i;
            data[count][1] = functions.pi0(i);
        }
        if (count < size-1 && (Math.abs(data[count][0] + step - end) < error)) {
            data[count+1][0] = data[count][0];
            data[count+1][1] = data[count][1];
        }
        else if (count < size-1 && !(Math.abs(data[count][0] + step - end) < error)){
            data[count+1][0] = data[count][0] + step;
            data[count+1][1] = functions.pi0((data[count][0] + step));
        }
        csv.write(header,data,filename);
        System.out.println("Ended");
    }
}
