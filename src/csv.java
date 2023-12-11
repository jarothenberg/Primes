import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class csv {
    // Our example data
    public static void write(String[] header, double[][] data, String filename) throws IOException {

        File csv = new File(filename);
        csv.delete();

        FileWriter csvWriter = new FileWriter(filename);
        
        csvWriter.append(String.join(",", header));
        csvWriter.append("\n");

        String[][] dataString = new String[data.length][data[0].length];

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                dataString[i][j] = String.valueOf(data[i][j]);
            }
            csvWriter.append(String.join(",", dataString[i]));
            csvWriter.append("\n");
        }

        csvWriter.flush();
        csvWriter.close();
    }  
    
    public static void write(String[] header, int[][] data, String filename) throws IOException {

        File csv = new File(filename);
        csv.delete();

        FileWriter csvWriter = new FileWriter(filename);
        
        csvWriter.append(String.join(",", header));
        csvWriter.append("\n");

        String[][] dataString = new String[data.length][data[0].length];

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                dataString[i][j] = String.valueOf(data[i][j]);
            }
            csvWriter.append(String.join(",", dataString[i]));
            csvWriter.append("\n");
        }

        csvWriter.flush();
        csvWriter.close();
    }  
}