import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int n, m;
        Scanner scanner = new Scanner(System.in);
        m = scanner.nextInt();
        n = scanner.nextInt();

        int result = m*n/2;
        System.out.println(result);
    }
}
