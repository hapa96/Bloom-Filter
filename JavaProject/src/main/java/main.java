import java.io.IOException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws IOException {
        BloomFilter bloom = new BloomFilter("TestData/words.txt");
        bloom.HashAllWords();
        System.out.println(bloom.testProbability(100000));
    }
}

