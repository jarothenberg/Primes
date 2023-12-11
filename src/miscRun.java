import java.io.*;
import java.util.ArrayList;

public class miscRun {
    public static void main(String[] args) {
        String readFile = "ReimmanZeroes.txt";
        String writeFile = "zeroes.txt";
        ArrayList<String> data = new ArrayList<String>();
        try(BufferedReader br = new BufferedReader(new FileReader(readFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line);
            }
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(writeFile))) {
            String line;
            int count = 0;
            while ((line = data.get(0)) != null) {
                bw.write(line);
                count++;
                bw.write(", ");
                if (count == 10) {
                    bw.write("\n");
                    count = 0;
                }
                data.remove(0);
            }
        }
        catch (IOException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }
}