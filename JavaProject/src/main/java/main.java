import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        BloomFilter bloom = new BloomFilter("TestData/words.txt");
        bloom.print();
        bloom.HashAllWords();
        System.out.println("Start Tests");
        System.out.println(bloom.MayContain("Hello"));
    }
}

