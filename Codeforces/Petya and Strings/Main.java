import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String v1 = scanner.nextLine().toLowerCase();
        String v2 = scanner.nextLine().toLowerCase();

        for (int i=0; i< v1.length(); i++){
            char c1 = v1.charAt(i);
            char c2 = v2.charAt(i);
            if (c1 < c2){
                System.out.println(-1);
                return;
            } else if (c1 > c2){
                System.out.println(1);
                return;
            }
        }
        System.out.println(0);
    }
}
