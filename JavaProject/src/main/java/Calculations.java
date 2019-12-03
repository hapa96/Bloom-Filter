public class Calculations {
    public static double pProbabilityOfFalsePositive(int mSizeBitArray, int kNumberOfHashFunctions, int nExpectedElementsToBeInserted) {
        return Math.pow(1 - Math.pow(1 - (1.0/mSizeBitArray), kNumberOfHashFunctions*nExpectedElementsToBeInserted), kNumberOfHashFunctions);
    }

    public static int mSizeOfBitArray(double p, int n) {
        int mSizeOfBitArray = ((int) -((n*Math.log(p)) / Math.pow(Math.log(2), 2)));
        //Print all the Values to console
        System.out.println("======================================AUSWERTUNG=====================================");
        System.out.println("Fehlertoleranz p = " + p);
        System.out.println("Anzahl erwarteter Elemente = " + n);
        System.out.println("Grösse des Bitarray m = " + mSizeOfBitArray);
        return mSizeOfBitArray;
    }

    public static int kOptimumNumberOfHashFunctions(int m, int n) {
        return (int)((m/n) * Math.log(2));
    }
}
