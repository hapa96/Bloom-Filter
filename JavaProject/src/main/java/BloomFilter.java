import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class BloomFilter {
    private HashSet<String> Words = new HashSet<>();     //stores all the words
    private List<HashFunction> HashList = new ArrayList<>();
    private String path;                                //path for words.txt
    private byte[] mBitArray;                          // Initial Bitset with length 100
    private int k = 0;

    // ****************************CREATE A NEW BLOOM FILTER WITH WORDS FROM A EXTERNAL SOURCE ***********************************************************
    public BloomFilter(String path) throws IOException {
        this.path = path;
        FileReader reader = new FileReader(path);
        BufferedReader inBuffer = new BufferedReader(reader);
        String line = inBuffer.readLine();
        while (line != null) {
            Words.add(line);
            line = inBuffer.readLine();
        }
        System.out.println("File eingelesen: " + path);
        System.out.println("Eingelesene Wörter: " + Words.size());
        System.out.println("Bitte Fehlerwahtscheinlichkeit eingeben:     ");
        Scanner in = new Scanner(System.in);
        setmBitArray(in.nextDouble(), Words.size());
        k = Calculations.kOptimumNumberOfHashFunctions(mBitArray.length, Words.size());
        System.out.println("k: " + k);
        createHashes(k);

    }

    public void setmBitArray(double p, int n) {
        mBitArray = new byte[Calculations.mSizeOfBitArray(p, n)];
    }

    //Hash all the words in List
    public void HashAllWords() {


        for (String s : Words) {
            int code = 0;
            for (int i = 0; i < k; i++) {
                code = HashList.get(i).hashUnencodedChars(s).asInt();
                code = countModulo(code);
                FillIndex(code);
            }

        }
        for (int i = 0; i < mBitArray.length; i++) {
            System.out.print(mBitArray[i]);
        }
        System.out.println();
    }

    public int countModulo(int input) {
        input = input % mBitArray.length;
        if (input < 0) input *= -1;
        return input;
    }

    private void createHashes(int k) {
        for (int i = 0; i < k; i++) {
            HashList.add(Hashing.murmur3_128((int) ((Math.random() * Integer.MAX_VALUE))));
        }
    }

    public void FillIndex(int one) {
        mBitArray[one] = 1;

    }

    //Helper function to print all Words in List Words
    public void print() {
        for (String s : Words) {
            System.out.println(s);
        }
    }

    public boolean MayContain(String s) {
        int code;
        for (int i = 0; i < k; i++) {
            code = HashList.get(i).hashUnencodedChars(s).asInt();
            code = countModulo(code);
            if (mBitArray[code] == 0) return false;
        }

        return true;
    }


    public double testProbability(int amountOfRandomWords) {
        double falsePositive = 0;
        HashSet<String> test = new HashSet<>();
        // Generate Random Words
        for (int i = 0; i < amountOfRandomWords; i++) {
            test.add(RandomStringUtils.random((int) (Math.random() * 10) + 3, true, false));
        }
        System.out.println("Es wurden: " + (int) amountOfRandomWords + " Zufällige Wörter generiert");
        for (String s : test) {
            if (MayContain(s) == true && (!Words.contains(s))) falsePositive++;
        }
        System.out.println("False Positive: " + (int) falsePositive);
        return (falsePositive / amountOfRandomWords) ;
    }


}
