import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

public class BloomFilter {
    List<String> strings;
    String path;
    //Read in Files
    Scanner in = new Scanner(System.in);
    path = String.valueOf(in.nextLine());
    FileReader reader = new FileReader();
    BufferedReader inBuffer = new BufferedReader(reader);

    String line = inBuffer.readLine();

   while (line != null)
    {
        System.out.println(line);
        line = inBuffer.readLine();
    }
}


}
