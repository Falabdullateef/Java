import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int n,k;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        k = scanner.nextInt();
        int[] a = new int[n];
        int c =0;

        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        //begin from i = 0
        for (int i = 0; i < n; i++) {
            if (a[i] >= a[k-1] && a[i] > 0) {
                c++;
            }
        }
        System.out.println(c);
    }
    public static void checker(){
        try {
            System.setIn(new FileInputStream("input.txt"));
            System.setOut(new PrintStream( new PrintStream("output.txt")));
        } catch (Exception e) {
            System.err.println("Error");
        }
    }
}