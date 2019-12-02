import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class BloomFilter {
    private List<String> Words = new ArrayList<>();     //stores all the words
    private String path;                                //path for words.txt
    private byte [] mBitArray = new byte [1000000];  // Initial Bitset with length 100
    private int seedOne = 1996;                            //Seed for HashFunction
    private int seedTwo = 14;                            //Seed for HashFunction
    private int sedThree = 4;                            //Seed for HashFunction
    private int size = 1000000;
    HashFunction hf1 = Hashing.murmur3_128(seedOne);
    HashFunction hf2 = Hashing.murmur3_128(seedTwo);
    HashFunction hf3 = Hashing.murmur3_128(sedThree);


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
    }
    //Hash all the words in List
    public void HashAllWords(){

        for(String s : Words){
            int codeOne = hf1.hashUnencodedChars(s).asInt();
            int codeTwo = hf2.hashUnencodedChars(s).asInt();
            int codeThree = hf3.hashUnencodedChars(s).asInt();

            codeOne = countModulo(codeOne);
            codeTwo = countModulo(codeTwo);
            codeThree = countModulo(codeThree);

            FillIndex(codeOne, codeTwo, codeThree);
        }
        for(int i = 0; i<mBitArray.length;i++){
            System.out.print(mBitArray[i]);
        }
        System.out.println();
    }
    public int countModulo(int input){
        input = input % size;
        if (input < 0) input *= -1;
        return input;
    }

    public void FillIndex (int one, int two, int three){
        mBitArray[one] = 1;
        mBitArray[two] = 1;
        mBitArray[three] = 1;
    }
//Helper function to print all Words in List Words
    public void print() {
        for (String s : Words) {
            System.out.println(s);
        }
    }
    public boolean MayContain(String s){
        int codeOne = hf1.hashUnencodedChars(s).asInt();
        int codeTwo = hf2.hashUnencodedChars(s).asInt();
        int codeThree = hf3.hashUnencodedChars(s).asInt();

        codeOne = countModulo(codeOne);
        codeTwo = countModulo(codeTwo);
        codeThree = countModulo(codeThree);
        if(mBitArray[codeOne] == 1 && mBitArray[codeTwo] == 1 && mBitArray[codeThree] == 1) return true;
        return false;
    }


}
