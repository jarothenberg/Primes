import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class data {
    public static double getLineFromFile(int lineNum, String fileName) throws IOException {
		try (Stream<String> file = Files.lines(Paths.get(fileName))) {
            return Double.valueOf(file.skip(lineNum).findFirst().get());
        }
	}
}
