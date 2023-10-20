package Star;

// Paper: 2009
// Year: 9-10 Junior
// Question: 21


public class BruteForceStars {

    public static void main(String[] args) {
        double lowerBound = 20008.0 / 20009.0;
        double upperBound = 2008.0 / 2009.0;

        int numberOfZeros = 0; // Start with replacing * with 0 zeros.

        while (true) {
            String numberAsString = "1." + repeatZeros(numberOfZeros) + "1";
            double number = Double.parseDouble(numberAsString);
            
            if (number > lowerBound && number < upperBound) {
                break; // We found our number
            }
            numberOfZeros++;
        }

        System.out.println("Number of zeros required: " + numberOfZeros);
    }

    public static String repeatZeros(int count) {
        StringBuilder zeros = new StringBuilder();
        for (int i = 0; i < count; i++) {
            zeros.append("0");
        }
        return zeros.toString();
    }
}
