import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner answer = new Scanner(System.in);
        int watermelon = answer.nextInt();
        // handles 1 and 2 as NO
        if (watermelon == 1 || watermelon == 2) {
            System.out.println("NO");
        }
        else{
            // handles even numbers
            if (watermelon % 2 == 0) {
                System.out.println("YES");
            }
            // handles odd numbers
            else {
                System.out.println("NO");
            }
        }
    }
}