public class Calculations {
    public static double pProbabilityOfFalsePositive(int mSizeBitArray, int kNumberOfHashFunctions, int nExpectedElementsToBeInserted) {
        return Math.pow(1 - Math.pow(1 - (1.0/mSizeBitArray), kNumberOfHashFunctions*nExpectedElementsToBeInserted), kNumberOfHashFunctions);
    }

    public static int mSizeOfBitArray(double probability, int nExpectedElementsToBeInserted) {
        int mSizeOfBitArray = ((int) -((nExpectedElementsToBeInserted*Math.log(probability)) / Math.pow(Math.log(2), 2)));
        //Print all the Values to console
        System.out.println("======================================AUSWERTUNG=====================================");
        System.out.println("Fehlertoleranz p = " + probability);
        System.out.println("Anzahl erwarteter Elemente = " + nExpectedElementsToBeInserted);
        System.out.println("Grösse des Bitarray m = " + (mSizeOfBitArray != 0 ? mSizeOfBitArray : 1));
        return mSizeOfBitArray;
    }

    public static int kOptimumNumberOfHashFunctions(int mSizeBitArray, int nExpectedElementsToBeInserted) {
        return (int)((mSizeBitArray/nExpectedElementsToBeInserted) * Math.log(2));
    }
}
