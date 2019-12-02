public class Calculations {
    public static double pProbabilityOfFalsePositive(int mSizeBitArray, int kNumberOfHashFunctions, int nExpectedElementsToBeInserted) {
        return Math.pow(1 - Math.pow(1 - (1.0/mSizeBitArray), kNumberOfHashFunctions*nExpectedElementsToBeInserted), kNumberOfHashFunctions);
    }

    public static double mSizeOfBitArray(double p, int n) {
        return -((n*Math.log(p)) / Math.pow(Math.log(2), 2));
    }

    public static double kOptimumNumberOfHashFunctions(int m, int n) {
        return (m/n) * Math.log(2);
    }
}
