import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // lines of code
        int i = 0;
        int x = 0;
        while (i < n) { // i=0, n=3 loops 3 times
            String s = scanner.next();
            if (s.charAt(1) == '+') // s = "++X", char 0 = '+', char 1 = '+', char 2 = 'X'
                ++x;
            else
                x--;
            i++;
        }
        System.out.println(x);

    }
}