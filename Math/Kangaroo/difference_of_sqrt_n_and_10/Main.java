package difference_of_sqrt_n_and_10;

// Paper: 2009
// Year: 9-10 Junior
// Question: 18

public class Main{

    public static void main(String[] args) {
        long count = 0;
        
        // Starting from 1 as n should be a positive whole number
        for (long n = 1; n <= 1000000000; n++) {  // Assuming an upper limit of 1000 for n
            if ((long) Math.abs(Math.sqrt(n) - 10) < 1) {
                count++;
                System.out.println("Valid n: " + n);
            }
        }
        
        System.out.println("Total whole numbers with the characteristic: " + count);
    }
}
