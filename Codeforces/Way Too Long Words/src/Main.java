import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nlines = scanner.nextInt();

        while (nlines-- > 0){
            String word = scanner.next();
            int len = word.length();
            if (len > 10) {
                System.out.println(word.charAt(0) + "" + (len - 2) + "" + word.charAt(len - 1));
            } else {
                System.out.println(word);
            }
        }

    }
}