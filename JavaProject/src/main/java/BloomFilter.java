import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BloomFilter {
    private List<String> Words = new ArrayList<>();     //stores all the words
    private String path;                                //path for words.txt
    private Byte[] mBitArray = new Byte[100];           // Initial Byte Array with length 100
    private int seed = 1996;                            //Seed for HashFunction

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
        HashFunction hf = Hashing.murmur3_32(seed);
        for(String s : Words){
            HashCode code = hf.hashUnencodedChars(s);
            int cod = code.asInt();
            System.out.println(cod);
        }

    }
//Helper function to print all Words in List Words
    public void print() {
        for (String s : Words) {
            System.out.println(s);
        }
    }


}
