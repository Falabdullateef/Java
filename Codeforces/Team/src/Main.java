import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int lines = scanner.nextInt();
        int count = 0;

        while (lines != 0) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            if (a + b + c >= 2) {
                count++;
            }
            lines--;
        }
        System.out.println(count);
    }
}