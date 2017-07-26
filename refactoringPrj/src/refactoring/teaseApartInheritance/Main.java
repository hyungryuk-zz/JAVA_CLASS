package refactoring.teaseApartInheritance;
import java.io.*;

public class Main {
    private static final String SAMPLE_CSV_STRING =
          "좋은아침,Good morning.\n"
        + "안녕하세요,Hello.\n"
        + "좋은저녁,Good evening.\n"
        + "좋은밤,Good night.\n";

    private static final String SAMPLE_CSV_FILE = "file.csv";

    public static void main(String[] args) throws IOException {
        new CSVStringTablePrinter(SAMPLE_CSV_STRING).print();
        new CSVFileTreePrinter(SAMPLE_CSV_FILE).print();
    }

}
